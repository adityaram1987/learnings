package com.learning.spark.spark_with_java_maven;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

@SuppressWarnings("deprecation")
public class AccumulatorSample {

	private static JavaSparkContext sc;

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("Sample spark application with Java");
		conf.setMaster("local");  //local[n] to use n cpu cores. local[*] for using entire cpu cores;  spark://... for setting remote spark master
		sc = new JavaSparkContext(conf);
		
		//accumulator is used by worker nodes as write-only variables - and driver node can access this shared variable at
	    //the end
		Accumulator<Integer> accum = sc.accumulator(0); //accumulator is given an initial value of 0
		List<Integer> numbers = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
		JavaRDD<Integer> data = sc.parallelize(numbers);
		JavaRDD<Integer> doubles = data.map(num -> num*2);
		
		//accumulator being written by worker nodes in shared manner
		doubles.foreach(value -> accum.add(value));
		
		//accumulator value is accessed as accum.value()
		System.out.println(accum.value());
	}

}
