package com.susie.stanley.interview.typeinfo;

public abstract class Shape {
	
	public void draw(){
		System.out.println(this +".draw()");
	}
	
	public abstract String toString();

}
