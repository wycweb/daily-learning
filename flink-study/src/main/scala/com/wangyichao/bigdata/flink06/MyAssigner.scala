//package com.wangyichao.bigdata.flink06
//
//import org.apache.flink.streaming.api.functions.TimestampAssigner
//
//class MyAssigner extends TimestampAssigner[Network] {
//
//  override def extractTimestamp(element: Network, previousElementTimestamp: Long): Long = {
//
//    val timeStamp = TimeFormatUtil.UTCToTimeStamp(element.time.)
//
//    timeStamp
//  }
//}
