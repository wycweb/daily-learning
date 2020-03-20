package com.wangyichao.bigdata.flink06

import com.wangyichao.bigdata.flink07.TimeFormatUtil
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, WindowedStream}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import scala.collection.mutable.ListBuffer


/**
 * 增量聚合的方式,底层是来一个算一个
 *
 * 在下面的例子中，每来一条数据就会处理一次，就会执行到reduce函数
 * 直到五秒的时候，会执行print方法
 */
object WindowApp04 {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime) //设置event time，避免数据乱序对计算结构造成影响

    val text = env.socketTextStream("localhost", 7777)

    val input: DataStream[Network] = text.filter(_.nonEmpty)
      .map(x => {
        val split = x.split(",")

        Network(split(0).toInt, split(1), split(2).toInt, split(3))
      })

    input.print()

    val result = input
      .keyBy("hostname")
      .timeWindow(Time.seconds(5))
      .process(new NetworkProcessWindowFunction)

    result.print().setParallelism(1)


    env.execute(this.getClass.getSimpleName)
  }
}

class NetworkProcessWindowFunction extends ProcessWindowFunction[Network, Network, Tuple, TimeWindow] {

  override def process(key: Tuple, context: Context, elements: Iterable[Network], out: Collector[Network]): Unit = {
    var hostname = ""
    var maxTime = Int.MinValue
    var minTime = Int.MaxValue
    var preTraffic = 0
    var nowTraffic = 0

    for (ele <- elements) {
      if (ele.time > maxTime) {
        maxTime = ele.time
      }

      if (ele.time < minTime) {
        minTime = ele.time
      }

      hostname = ele.hostname
    }

    for (ele <- elements) {
      if (ele.time == minTime) {
        preTraffic += ele.traffic
      }

      if (ele.time == maxTime) {
        nowTraffic += ele.traffic
      }
    }

    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    println(s"""机器为：$hostname,当前时间：$maxTime,当前总流量：$nowTraffic""")
    println(s"""机器为：$hostname,前一刻时间：$minTime,前一刻总流量：$preTraffic""")
    println(s"""机器为：$hostname,网速为${(nowTraffic - preTraffic) / (maxTime - minTime)}""")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

    val result = Network(1, "2", 3, hostname)

    out.collect(result)
  }

}

case class Network(time: Int, networkName: String, traffic: Int, hostname: String)