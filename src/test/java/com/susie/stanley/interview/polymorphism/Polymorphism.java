package com.susie.stanley.interview.polymorphism;

import org.junit.Test;

/**
 * 多态：方法调用动态绑定来实现多态。编写只与基类打交道的代码，来运行子类的行为。
 * 1、简化代码</br>
 * 2、避免修改，易扩展</br>
 * */
public class Polymorphism {

	/**
	 * 测试父类中方法块，在动态加载时的执行顺序
	 * animal静态块static{}
	 * animal非静态块{}
	 * animal构造函数
	 * animal run
	 */
	@Test
	public void supChunkOrder(){
		Animal a = new Animal();
		a.run();
	}
	/**
	 * 测试子类中方法块，在动态加载时的执行顺序
	 * animal静态块static{}
	 * dog 静态块static{}
	 * animal非静态块{}
	 * animal构造函数
	 * dog 非静态块{}
	 * dog 构造函数
	 * dog run
	 */
	@Test
	public void subChunkOrder(){
		Dog d = new Dog();
		d.run();
	}
	/**
	 * 测试父类引用子类对象时，方法块在动态加载时的执行顺序
	 * animal静态块static{}
	 * dog 静态块static{}
	 * animal非静态块{}
	 * animal构造函数
	 * dog 非静态块{}
	 * dog 构造函数
	 * dog run
	 */
	@Test
	public void subChunkOrder2(){
		Animal ad = new Dog();
		ad.run();
	}
	/**
	 * 测试骑几轮自行车
	 */
	@Test
	public void rideCycleTest(){
		rideCycle(new Unicycle());
		rideCycle(new Bicycle());
		rideCycle(new Tricycle());
	}
	
	/**
	 * 骑几轮自行车，参数多态
	 * @param cycle
	 */
	public void rideCycle(Cycle cycle){
		cycle.getWheels();
	}
	
	/**
	 * 制造自行车，返回值多态
	 * @return
	 */
	public Cycle[] cycleFactory(){
		Cycle[] cycles = new Cycle[3];
		cycles[0] = new Unicycle();
		cycles[1] = new Bicycle();
		cycles[2] = new Tricycle();
		return cycles;
	}

}
