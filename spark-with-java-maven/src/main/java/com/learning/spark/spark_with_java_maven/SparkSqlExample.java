package com.learning.spark.spark_with_java_maven;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class SparkSqlExample {

	public static void main(String[] args) {
		SparkConf config = new SparkConf();
		config.setAppName("Spark SQL demonstration");
		config.setMaster("local");
		
		JavaSparkContext sc = new JavaSparkContext(config);
		
		SQLContext sqlCotext = new SQLContext(sc);
		ArrayList<Author> authors = new ArrayList<Author>();
		authors.add(new Author("Kipling",32));
		authors.add(new Author("Me",1));
		JavaRDD<Author> rdd = sc.parallelize(authors);
		
		//This createDataFrame works only for the bean classes i.e. class with Setters and getters
		//Otherwise we dont get any error, but the data from rdd doesnt load into data frame
		Dataset<Row> df = sqlCotext.createDataFrame(rdd, Author.class);
		df.printSchema();
		df.show();
	}

}
