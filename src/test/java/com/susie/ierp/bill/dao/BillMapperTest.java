package com.susie.ierp.bill.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.susie.ierp.mybatis.Bill;
import com.susie.ierp.mybatis.BillMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class BillMapperTest {
	
	@Autowired
	BillMapper billMapper;

	@Test
	public void test() {
		Bill bill = new Bill();
		bill.setBillNo("1");
		bill.setPayType("wx");
		bill.setBillMny("100");
		bill.setSalesman("swy");
		bill.setTs(new Date());
		billMapper.insert(bill);
		fail("Not yet implemented");
	}

}
