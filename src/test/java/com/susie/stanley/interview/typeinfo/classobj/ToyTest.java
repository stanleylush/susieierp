package com.susie.stanley.interview.typeinfo.classobj;

public class ToyTest {
	
	public static void printInfo(Class c){
		System.out.println("Class name: " + c.getName() + " is interface?[" + c.isInterface() + "]");
		System.out.println("Simple name: " + c.getSimpleName());
		System.out.println("Canonical name: " + c.getCanonicalName());
	}

}
