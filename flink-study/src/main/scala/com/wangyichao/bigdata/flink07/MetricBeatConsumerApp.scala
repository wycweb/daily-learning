package com.wangyichao.bigdata.flink07

import java.lang
import java.util.Properties

import com.google.gson.{Gson, JsonObject}
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer


object MetricBeatConsumerApp {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val inputStream = env.addSource(this.getKafkaConsumer)

    val flattenStream = inputStream.map(x => {
      MetricBeatFormatUtil.getFlattenObject(x)
    })

    val splitDataStream = flattenStream.split(new OutputSelector[JsonObject] {
      override def select(value: JsonObject): lang.Iterable[String] = {
        import java.util
        val tags = new util.ArrayList[String]()

        if (value.get("metricset_name").getAsString() == "network") {
          tags.add("network")
        }

        tags
      }
    })

    val networkStream: DataStream[SystemNetworkBean] = splitDataStream.select("network").map(value => {
      val network = new Gson().fromJson(value, classOf[SystemNetworkBean])

      network
    })

    networkStream.print("network原始数据")

    val reduceDS = networkStream
      .keyBy("host_name")
      .window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(1)))
      .reduce((x, y) => {
        val networkOutBytes = y.getSystem_network_out_bytes + x.getSystem_network_out_bytes
        val networkOutErrors = y.getSystem_network_out_errors + x.getSystem_network_out_errors
        val networkOutPackets = y.getSystem_network_out_packets + x.getSystem_network_out_packets
        val networkOutDropped = y.getSystem_network_out_dropped + x.getSystem_network_out_dropped
        val networkInBytes = y.getSystem_network_in_bytes + x.getSystem_network_in_bytes
        val networkInErrors = y.getSystem_network_in_errors + x.getSystem_network_in_errors
        val networkInPackets = y.getSystem_network_in_packets + x.getSystem_network_in_packets
        val networkInDropped = y.getSystem_network_in_dropped + x.getSystem_network_in_dropped

        val networkBean = new SystemNetworkBean(x.getEvent_time, networkOutBytes, networkOutErrors, networkOutPackets, networkOutDropped, "", networkInBytes, networkInErrors, networkInPackets, networkInDropped, x.getHost_name);

        networkBean
      })

    reduceDS.print()


    env.execute(this.getClass.getSimpleName)
  }


  class AverageAggregate extends AggregateFunction[Network, (Long, Long), Float] {
    override def createAccumulator(): (Long, Long) = (0L, 0L)

    override def add(value: Network, accumulator: (Long, Long)): (Long, Long) = {
      value.event_time

      (1, 1)
    }

    override def getResult(accumulator: (Long, Long)): Float = {

      1 / 2
    }

    override def merge(a: (Long, Long), b: (Long, Long)): (Long, Long) = {
      (1, 1)
    }
  }


  /**
   * 获取kafka sink
   *
   * @return
   */
  def getKafkaConsumer: FlinkKafkaConsumer[String] = {

    val topic = "METRICBEAT_LOG01"

    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("group.id", "test");

    val kafkaConsumer = new FlinkKafkaConsumer(topic, new SimpleStringSchema(), properties)
    kafkaConsumer.setStartFromEarliest()


    kafkaConsumer
  }

}