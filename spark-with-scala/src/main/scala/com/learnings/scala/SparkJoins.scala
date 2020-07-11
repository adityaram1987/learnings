package com.learnings.scala

import org.apache.spark.{SparkConf, SparkContext}

object SparkJoins {
  def main(args:Array[String]): Unit={
    //Creating a SparkConfig and SparkContext objects for initialising spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Joins application")

    val sc = new SparkContext(conf)

    val regFile = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 2\\join\\reg.tsv").map(_.split("\t"))

    println("Printing first line")
    println(regFile.first()(0) + " " + regFile.first()(1) + " " + regFile.first()(2) + " " + regFile.first()(3) + " " + regFile.first()(4))

    //Serializing the data for Register object - Chapter-2/joins
    case class Register(uuid: String, date: String, customerId: Int, lat: Double, long: Double)

    /*val register = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 2\\join\\reg.tsv")
              .map(_.split("\t")).map(c => new Register(c(1), c(0), c(2).toInt, c(3).toDouble, c(4).toDouble))
    println(register.first()) */

    //Making a tuple - uuid as key, object itself as value
    val register = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 2\\join\\reg.tsv")
      .map(_.split("\t")).map(c => new Register(c(1), c(0), c(2).toInt, c(3).toDouble, c(4).toDouble))
      .map(r => (r.uuid, r))

    //Serializing the data for Click object - Chapter-2/joins
    case class Click(uuid: String, date: String, pageId: Int)
    //Making a tuple - uuid as key and click object as value
    val click = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide " +
      "for the Developer Certification for Apache Spark - Working Files\\Chapter 2\\join\\clk.tsv")
      .map(_.split("\t")).map(c => (c(1), new Click(c(1), c(0), c(2).toInt)))
    //Checking if click is serialized
    println(click.first())

    //JOINING THE TWO RDDs which are in the form RDD[(String, Object)]
    val joined = click.join(register)
    println("Printing the joined rows")
    joined.foreach(join => println(join))
  }
}
