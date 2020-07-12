package com.learnings.scala

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object CachingAndPersistence {
  def main(args:(Array[String])): Unit={
    val conf = new SparkConf();
    conf.setMaster("local")
    conf.setAppName("Caching and Persistence demo")

    val sc = new SparkContext(conf)
    val data = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for the " +
      "Developer Certification for Apache Spark - Working Files\\Chapter 4\\movielens\\large\\tags.dat")
          .map(_.split("::"))
    println(data.first())

    //Caching data after performing some transformations
    //can be applied to small as well as large data
    val movie15 = data.filter(_.contains("15"))
    //movie15.cache()
    //Cannot change storage level of an RDD after it was already assigned a level. So the below cant be used along with
    //.cache()
    movie15.persist(StorageLevel.MEMORY_AND_DISK)
    println(movie15.count())
    println(movie15.collect())
  }
}
