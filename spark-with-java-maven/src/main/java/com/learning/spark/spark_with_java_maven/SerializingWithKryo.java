package com.learning.spark.spark_with_java_maven;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SerializingWithKryo {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("Serializing data from file");
		conf.setMaster("local");
		//using Kryo is efficient for serializing and  will eliminate the need for classes to implement Serializable interface
		conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		//By setting this true, only those classes which is registered for Kryo can be serialized. This makes spark more efficient
		conf.set("spark.kryo.registrationRequired", "true");
		//registering the required class for KrySerialization
		conf.registerKryoClasses(new Class[] {Object[].class, UserWithoutSerializable.class});
		//if we want to use serializer that can be more than 2mb, we need to set te buffer size
		conf.set("spar.kryoserializer.buffer.mb", "24");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<String> data = sc.textFile("C:\\mywork\\O'reilly-spark-resources\\0636920040880-master\\Study Guide for "
				+ "the Developer Certification for Apache Spark - Working Files\\Chapter 3\\users.dat");
		
		//We can also use any variable defined above in the class in the spark lambdas. But this will be sent along with Spark
		//context to the workers. So it is not good to use large variables this way.
		JavaRDD<UserWithoutSerializable> users = data.map(line -> Arrays.asList(line.split("::")))
			.map(col -> new UserWithoutSerializable(Long.parseLong(col.get(0)), col.get(1), Integer.parseInt(col.get(2)), col.get(3), col.get(4)));
		System.out.println(users.first());
	}

}
