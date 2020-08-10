package com.splitwise.example.model;


public class User {
	private int userId;
	private String name;
	private String phoneNo;
	private float balance;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void addExpense(float expense) {
		balance -= expense;
	}
	
	public void decreaseExpense(float expense) {
		balance += expense;
	}
}
