package com.learnings.scala

import org.apache.spark.{SparkConf, SparkContext}

object SparkReadDatFile {
  def main(args:Array[String]): Unit={
    //Creating a SparkConfig and SparkContext objects for initialising spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Read dat file from Chapter 3")

    val sc = new SparkContext(conf)

    //Go through README file in chapter 3 for knowing structure
    val data = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 3\\users.dat")
    println(data.first())

    val data1 = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 3\\users1.dat")

    case class Users(id: Long, gender: String, age: Int, occupation: String, zipCode: String)
    val users = data.map(_.split("::")).map(col => new Users(col(0).toLong, col(1), col(2).toInt,
      col(3), col(4)))
    println(users.first())
    println("count: " + users.count())

    val users1 = data1.map(_.split("::")).map(col => new Users(col(0).toLong, col(1), col(2).toInt,
      col(3), col(4)))

    println("users class: " + users.getClass)
    println("data class: " + data.getClass)

    val diff = data1.subtract(data)
    println(diff.count())

  }
}


//df1['id'], df2['id']
//x = df1[~df1['id'].isin(df2['id'])]