package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.streaming._

object SimpleSparkStreaming {
  def main(args: Array[String]): Unit={
    var sc = new SparkContext("local[2]", "spark streaming example")
    val ssc = new StreamingContext(sc, Seconds(2))   //passing spark context and BatchTimeWindow as arguments
    //TODO
    //val stream = ssc.textFileStream("C:\\spark-2.4.4-bin-hadoop2.7\\data\\streaming")
    val stream = ssc.fileStream("C:\\spark-2.4.4-bin-hadoop2.7\\data\\streaming")
    //this is simple stream tranformation
    stream.map(line => (line, 1)).print()

    //RDD tranformations and windows
   /* stream.flatMap(_.split(","))
        .map((_, 1))
        .reduceByKeyAndWindow(_ , Seconds(30), Seconds(10)) //if using only reduceByKey - it only reduces for streamContext Batch time window period
        //3rd argument above is Slide duration - specifies sliding window
        .print() */

    ssc.start()
    ssc.awaitTermination()

  }
}
