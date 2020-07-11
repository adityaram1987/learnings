package com.learning.spark.spark_with_java_maven;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Serializing {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("Serializing data from file");
		conf.setMaster("local");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<String> data = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for "
				+ "the Developer Certification for Apache Spark - Working Files\\Chapter 3\\users.dat");
		
		//We can also use any variable defined above in the class in the spark lambdas. But this will be sent along with Spark
		//context to the workers. So it is not good to use large variables this way.
		JavaRDD<User> users = data.map(line -> Arrays.asList(line.split("::")))
			.map(col -> new User(Long.parseLong(col.get(0)), col.get(1), Integer.parseInt(col.get(2)), col.get(3), col.get(4)));
		System.out.println(users.first());
	}

}
