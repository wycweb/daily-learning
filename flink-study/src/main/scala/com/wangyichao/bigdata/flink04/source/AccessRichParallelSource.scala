package com.wangyichao.bigdata.flink04.source

import com.wangyichao.bigdata.flink02.bean.Access
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

import scala.util.Random

class AccessRichParallelSource extends RichParallelSourceFunction[Access] {

  var running = true

  override def run(ctx: SourceFunction.SourceContext[Access]): Unit = {
    val random = new Random()


    val domains = Array("baidu.com", "taobao.com", "jd.com")

    while (running) {
      val timestamp = System.currentTimeMillis()

      val domain = domains(random.nextInt(domains.length))
      1.to(10).foreach(x => {
        ctx.collect(Access(timestamp, domain, random.nextInt(1000)))
      })

      Thread.sleep(5000)
    }
  }

  override def cancel(): Unit = {
    running = false
  }

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
  }

  override def close(): Unit = {
    super.close()
  }
}
