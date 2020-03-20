package com.wangyichao.bigdata.flink07

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.ValueState
import org.apache.flink.util.Collector
import org.apache.flink.configuration.Configuration
import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class CountWindowAverage extends RichFlatMapFunction[(Long, Double), (Long, Double)] {
  private var sum: ValueState[(Long, Double)] = _
  override def flatMap(input: (Long, Double), out: Collector[(Long, Double)]): Unit = {
    // access the state value
    val tmpCurrentSum = sum.value
    // If it hasn't been used before, it will be null
    val currentSum = if (tmpCurrentSum != null) {
      tmpCurrentSum
    } else {
      (0L, 0d)
    }
    // update the count
    val newSum = (currentSum._1 + 1, currentSum._2 + input._2)
    // update the state
    sum.update(newSum)
    // if the count reaches 2, emit the average and clear the state
    if (newSum._1 >= 2) {
      out.collect((input._1, newSum._2 / newSum._1))
      //将状态清除
      //sum.clear()
    }
  }

  override def open(parameters: Configuration): Unit = {
    sum = getRuntimeContext.getState(
      new ValueStateDescriptor[(Long, Double)]("average", classOf[(Long, Double)])
    )
  }
}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
object ECountWindowAverage {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.fromCollection(List(
      (1L, 3d),
      (1L, 5d),
      (1L, 7d),
      (1L, 4d),
      (1L, 2d)
    )).keyBy(_._1)
      .flatMap(new CountWindowAverage())
      .print()

    /*.keyBy(_._1)
      .flatMap(new CountWindowAverage())
      .print()*/

    // the printed output will be (1,4) and (1,5)
    env.execute("ExampleManagedState")
  }
}