package com.learnings.scala

import org.apache.spark.{SparkConf, SparkContext}

object Broadcast {
  def main(args:Array[String]): Unit= {
    //Creating a SparkConfig and SparkContext objects for initialising spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Broadcast application for sharing variables")

    val sc = new SparkContext(conf)

    val data = sc.parallelize(1 to 1000)
  }
}
