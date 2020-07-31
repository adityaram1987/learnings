package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD

object KMeansMLLib {
  def main(args: Array[String]): Unit= {
    val sc = new SparkContext("local","K Mean MLLib unsupervised example")

    val path = "C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for the Developer " +
      "Certification for Apache Spark - Working Files\\Chapter 6\\iris.data"

    //This is called as "Feature Engineering". But this is very simple here in this case
    val data: RDD[Vector]= sc.textFile(path).map(line =>
                Vectors.dense(line.split(",").slice(0,3).map(_.toDouble)))

    val computeCenters: KMeansModel = KMeans.train(data, 3, 200)
    val WSSE = computeCenters.computeCost(data)
    println(s"Within Sum Set Error $WSSE")
  }
}
