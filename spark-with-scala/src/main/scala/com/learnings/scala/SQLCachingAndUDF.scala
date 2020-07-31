package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object SQLCachingAndUDF {
  def main(args: Array[String]): Unit={
    val sc = new SparkContext("local","SQL Caching and UDF example")
    val sqlContext = new SQLContext(sc)

    val path = "C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for the Developer " +
      "Certification for Apache Spark - Working Files\\Chapter 5\\data_titanic.json"
    val df = sqlContext.load(path, "json")
    df.printSchema()

    //If we want to get average of any column, we can get it by converting df into RDD and performing some transformations
    //But such things are very easy in SQL
    df.registerTempTable("passengers")

    //it is lazy evaluation. By doing this we can expose these queries through api calls
    val stats = sqlContext.sql(
      """
        |Select avg(age) as mean_age,
        |min(age) as min_age,
        |max(age) as max_age,
        |sex from passengers
        |group by sex
        |""".stripMargin)

    sqlContext.cacheTable("passengers")

    sqlContext.udf.register("first_letter", (input: String) => input.charAt(0).toString)
    sqlContext.sql("select first_letter(name) from passengers").show()

    sqlContext.uncacheTable("passengers")
  }
}
