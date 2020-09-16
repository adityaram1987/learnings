package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object LoadFile {
  def main(args: Array[String]): Unit= {
    val sc = new SparkContext("local", "Loading file")
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.load("authors")
    val filtered = df.filter(df.col("name").equalTo("Kipling"))
    df.show()
    println("entries: " + df.count)
    println("datatype: " + df.getClass)
    df.printSchema()

    println("filtered: ")
    filtered.show()

  }
}
