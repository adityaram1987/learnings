package com.learnings.scala

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.SQLContext

object SparkMLLibExample {
    def main(args : Array[String]): Unit={
      val sc = new SparkContext("local", "Spark MLLib demo")
      val sqlContext = new SQLContext(sc)

      val path = "C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for the Developer " +
        "Certification for Apache Spark - Working Files\\Chapter 6\\data_titanic.json"
      val df = sqlContext.load(path, "json")

      val persons = df.select("name", "age", "sex", "survived").rdd
      val features = persons.map(row =>
        LabeledPoint(row.getLong(3).toDouble,
          Vectors.dense(
            row.getDouble(1),
            if(row.getString(2) == "M") 0 else 1
          )
        )
      )

      val splits = features.randomSplit(Array(0.6,0.4))
      val trainingSet = splits(0)
      val validationSet = splits(1)

      val model = SVMWithSGD.train(trainingSet, 50)    //train expects LabeledPoint and number of iterations

      val scoreAndLabels = validationSet.map { point =>
          val score = model.predict(point.features)
          (score, point.label)
      }

      val metrics = new BinaryClassificationMetrics(scoreAndLabels)
      val auROC = metrics.areaUnderROC()

      println("Area under ROC : " + auROC)
    }
}
