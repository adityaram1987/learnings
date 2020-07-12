package com.learning.spark.spark_with_java_maven;

import java.io.Serializable;

public class Author implements Serializable{
	private String name;
	private int nbBooks;
	
	public Author(String name, int nbBooks) {
		this.name = name;
		this.nbBooks = nbBooks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbBooks() {
		return nbBooks;
	}

	public void setNbBooks(int nbBooks) {
		this.nbBooks = nbBooks;
	}

}
