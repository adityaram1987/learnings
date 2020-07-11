package com.learning.spark.spark_with_java_maven;

public class UserWithoutSerializable {
	public long id;
	public String gender;
	public int age;
	public String occupation;
	public String zipCode;
	
	public UserWithoutSerializable(long id, String gender, int age, String occupation, String zipCode) {
		super();
		this.id = id;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
		this.zipCode = zipCode;
	}
}
