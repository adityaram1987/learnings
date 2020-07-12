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
    println(data.map(parameter => parameter*2).first()) //without any external variables

    val MULTIPLICATION_FACTOR = 4
    //This is called closure serialisation
    println(data.map(parameter => parameter*MULTIPLICATION_FACTOR).first()) //by using variable defined

    //allows us to define read-only variable that can be used efficiently within a single worker node
    //read only once in a worker node
    val factor = sc.broadcast(MULTIPLICATION_FACTOR)
    println(data.map(parameter => parameter* factor.value).first()) //broadcasted value accessed by variable.value
  }
}
