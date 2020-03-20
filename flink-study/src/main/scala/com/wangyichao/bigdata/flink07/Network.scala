package com.wangyichao.bigdata.flink07

case class Network(event_time: String, system_network_out_bytes: Long, system_network_out_errors: Long, system_network_out_packets: Long, system_network_out_dropped: Long, system_network_name: String, system_network_in_bytes: Long, system_network_in_errors: Int, system_network_in_packets: Long, system_network_in_dropped: Long, host_name: String)