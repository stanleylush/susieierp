package com.susie.ierp.bill.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.susie.ierp.bill.service.IBillSrevise;
import com.susie.ierp.mybatis.Bill;
import com.susie.ierp.mybatis.BillMapper;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class BillMapperTest {
	
	@Autowired
	BillMapper billMapper;
	
	@Autowired
	IBillSrevise billService;

	@Test
	public void testInsert() {
		
		Bill bill = generateObj();
		
		int result = billMapper.insert(bill);
		Assert.assertEquals(1, result);
		throw new RuntimeException();
	}

	@Transactional
	public void testTransactionFailed(){
		billService.addBill(generateObj());
		billService.deleteBill(1);
		throw new RuntimeException();
	}


	private Bill generateObj() {
		Bill bill = new Bill();
		bill.setBillNo(String.valueOf(Math.random()));
		bill.setPayType("wx");
		bill.setBillMny("100");
		bill.setSalesman("swy");
		bill.setTs(new Date());
		return bill;
	}
	
}
