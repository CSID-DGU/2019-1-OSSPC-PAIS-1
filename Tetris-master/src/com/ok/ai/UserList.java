package com.ok.ai;

<<<<<<< HEAD
import com.ok.ai.UserList; 
=======
import com.ok.ai.UserList;
>>>>>>> park-jue

public class UserList implements Comparable<UserList>{
	String id;
	int score;
<<<<<<< HEAD
	public UserList(String name, int sc){ //get user ID & Score, return them
=======
	public UserList(String name, int sc){
>>>>>>> park-jue
		id = name;
		score = sc;
	}
	
	public String getID(){
		return id;
	}
	public int getScore(){
		return score;
	}
	
	public int compareTo(UserList r2){
		return r2.score - this.score;
	}
	
	public String toString(){
		return id + ", " + score;
	}
}
