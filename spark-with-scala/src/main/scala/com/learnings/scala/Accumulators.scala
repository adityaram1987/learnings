package com.learnings.scala

import org.apache.spark.{SparkConf, SparkContext}

object Accumulators {
  def main(args: Array[String]): Unit={
    val conf = new SparkConf()
    conf.setAppName("Accumulators")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val data = sc.parallelize(1 to 1000)
    val doubles = data.map(_ * 2)

    val accum = sc.accumulator(0)
    //accumulator is used by worker nodes as write-only variables - and driver node can access this shared variable at
    //the end
    doubles.foreach(accum += _)
    println(accum.value)
  }
}
