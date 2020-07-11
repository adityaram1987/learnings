package com.learning.spark.spark_with_java_maven;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id;
	public String gender;
	public int age;
	public String occupation;
	public String zipCode;
	
	public User(long id, String gender, int age, String occupation, String zipCode) {
		super();
		this.id = id;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
		this.zipCode = zipCode;
	}
	
}
