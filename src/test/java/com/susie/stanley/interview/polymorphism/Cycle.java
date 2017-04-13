package com.susie.stanley.interview.polymorphism;

public class Cycle {
	
	int wheelsNum;
	
	public Cycle(){
		
	}

	public Cycle(int num){
		this.wheelsNum = num;
	}
	
	public void getWheels(){
		System.out.println("我在骑"+this.wheelsNum+"轮自行车");
	}
}
