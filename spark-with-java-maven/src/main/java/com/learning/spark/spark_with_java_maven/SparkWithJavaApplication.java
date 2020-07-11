package com.learning.spark.spark_with_java_maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class SparkWithJavaApplication 
{
    private static JavaSparkContext sc;

	public static void main( String[] args )
    {
    	//for simple spark context
    	//	JavaSparkContext sparkContext = new JavaSparkContext();
    		
    		//If we want to give custom conf
    		SparkConf conf = new SparkConf();
    		conf.setAppName("Sample spark application with Java");
    		conf.setMaster("local");  //local[n] to use n cpu cores. local[*] for using entire cpu cores;  spark://... for setting remote spark master
    		sc = new JavaSparkContext(conf);
    		
//    		//for loading a file into JavaRDD
//    		JavaRDD<String> rdd = sc.textFile("/mywork/SparkCourse/ml-100k/u.data");  //sc.textFile("path/*") - to read all files
//    		System.out.println(rdd.first());
    		
    		//TRANSFORMATIONS
    		//Printing the words count
    		ArrayList <String> book = new ArrayList<String>();
    		book.add("This is book 1");
    		book.add("This is book 2");
    		JavaRDD<String> rdd = sc.parallelize(book);
    		
    		JavaRDD<String> words = rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
    		JavaPairRDD<String, Integer> counts = words.mapToPair(word -> new Tuple2<String, Integer>(word, 1))
    								.reduceByKey((current , accumulator) -> current + accumulator);
    		List<Tuple2<String, Integer>> results = counts.collect();   //counts.saveAsTextFile("path")
    		System.out.println(results);
    		
    		//FOR JAVA 7
//    		List<Tuple2<String, Integer>> results = rdd.flatMap(new FlatMapFunction<String, String>() {
//										/**
//										 * 
//										 */
//										private static final long serialVersionUID = 1L;
//										@Override
//										public Iterator<String> call(String line) throws Exception {
//											// TODO Auto-generated method stub
//											return Arrays.asList(line.split(" ")).iterator();
//										}
//							    		}).mapToPair(new PairFunction<String , String, Integer>(){
//											/**
//											 * 
//											 */
//											private static final long serialVersionUID = 1L;
//											@Override
//											public Tuple2<String, Integer> call(String word) throws Exception {
//												return new Tuple2<String, Integer>(word, 1);
//											}    			
//							    		}).reduceByKey(new Function2<Integer, Integer, Integer>() {
//											/**
//											 * 
//											 */
//											private static final long serialVersionUID = 1L;
//											@Override
//											public Integer call(Integer v1, Integer v2) throws Exception {
//												// TODO Auto-generated method stub
//												return (v1+v2);
//											}
//										}).collect();
//    		System.out.println(results);
    		
    }
}
