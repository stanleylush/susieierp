package com.susie.ierp.bill.service;

import java.util.List;

import com.susie.ierp.mybatis.Bill;

public interface IBillSrevise {
	
	public Bill getBillById(int id);
	
	public List<Bill> getAllBill();
	
	public int addBill(Bill bill);
	
	public int updateBill(Bill bill);
	
	public int deleteBill(int id);

}
