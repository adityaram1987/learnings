package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object LoadFile {
  def main(args: Array[String]): Unit= {
    val sc = new SparkContext("local", "Loading file")
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.load("authors")
    df.show()
    println("entries: " + df.count)
    println("datatype: " + df.getClass)
    df.printSchema()

  }
}
