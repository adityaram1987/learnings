package com.splitwise.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.splitwise.example.model.Expense;
import com.splitwise.example.model.User;



@RestController
public class AccountController {

	//List<User> users = new ArrayList<User>();
	List<Integer> usersInGroup = new ArrayList<Integer>();
	HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	@PostMapping(value = "/createuser")
	public ResponseEntity<String> createUser(@RequestBody User user){
		System.out.println("user : "+ user.getName());
		users.putIfAbsent(user.getUserId(), user);
		return new ResponseEntity<>("User created successfully", HttpStatus.OK);
		
//		{
//		    "userId" : 1,
//		    "name" : "aditya",
//		    "phoneNo" : "dfdfwe"
//          "balance" : 0
//		}
	}
	
	@PostMapping(value = "/adduser/{id}")
	public ResponseEntity<String> addUser(@PathVariable("id") int id){
		if(users.containsKey(id)) {
			usersInGroup.add(id);
			return new ResponseEntity<>("User added successfully to the group", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("UserId not existing in the database", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/addexpense")
	public ResponseEntity<String> addExpense(@RequestBody Expense expense){
		int id = expense.getUserId();
		if(users.containsKey(id)) {
			float averageExpense = expense.getExpense()/usersInGroup.size();
			User spentUser = users.get(id);
			System.out.println(averageExpense);
			
			for(int currentId : usersInGroup) {
				User currentUser = users.get(currentId);
				currentUser.addExpense(averageExpense);
			}
			
			spentUser.decreaseExpense(expense.getExpense());
			return new ResponseEntity<>("Expenses successfully updated", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("User doesnt exists", HttpStatus.BAD_REQUEST);
		
//		{
//		    "userId" : 1,
//		    "expense" : 120
//		}
	}
	
	@GetMapping(value = "/getbalance/{id}")
	public ResponseEntity<String> getBalance(@PathVariable("id") int id){
		if(users.containsKey(id)) {
			User currentUser = users.get(id);
			return new ResponseEntity<>("Balance: " + currentUser.getBalance(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("UserId not existing in the database", HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping(value = "/settlebalance")
	public ResponseEntity<String> settleBalance(@RequestBody Expense expense){
		int id = expense.getUserId();
		if(users.containsKey(id)) {
			
			User currentUser = users.get(id);
			currentUser.decreaseExpense(expense.getExpense());
			return new ResponseEntity<>("Balance successfully updated", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("User doesnt exists", HttpStatus.BAD_REQUEST);
		
//		{
//		    "userId" : 1,
//		    "expense" : 120
//		}
	}
	
}
