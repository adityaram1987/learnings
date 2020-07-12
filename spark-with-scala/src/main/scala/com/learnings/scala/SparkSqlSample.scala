package com.learnings.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{types, _}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

case class Author(name: String, nbBooks: Int)

object SparkSqlSample {
  def main(args: Array[String]): Unit={
    val conf = new SparkConf()
    conf.setAppName("Using Spark SQL for structured data")
    conf.setMaster("local")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

 /*   //creating a Dataframe using SQLContext
    val df = sqlContext.createDataFrame(List(("myself",1)))
    df.toDF("name", "count").show() */

    //Uses of SQLContext
 /*   val rdd = sc.parallelize(List(Author("Kipling",32), Author("Me",1)))
    import sqlContext.implicits._         //rdd doesnt have inbuilt method to convert to df. So importing implicits
    rdd.toDF().show()    //considers column names as defined in Author class i.e. name and nbBooks

    val df = rdd.toDF("name","count")        //to rename the columns
    df.printSchema() */

    //APPLYING A SCHEMA
    val rdd: RDD[Row] = sc.parallelize(List(("Kipling",32),("Me",1)))
                  .map{ case(name, count) => Row(name, count)} //This is done because line 38 expects not tuples, expects Rows
    val schema = StructType(List(
      StructField("name", StringType, nullable = false),
      StructField("nbBooks", IntegerType, false)
    ))
    val df = sqlContext.createDataFrame(rdd, schema)
    df.printSchema()
    df.show()

    //Registering dataframe as table
    df.registerTempTable("authors")
    val authorsDF = sqlContext.sql(
      """
        |select * from authors
        |""".stripMargin)
    authorsDF.show()

    //writing data frame to file
    authorsDF.write.parquet("authors")
  }
}
