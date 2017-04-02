package com.susie.ierp.bill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.susie.ierp.mybatis.Bill;
import com.susie.ierp.mybatis.BillMapper;

@Service
@Transactional
public class BillSreviseImpl implements IBillSrevise {
	
	@Autowired
	private BillMapper billMapper;

	public Bill getBillById(int id) {
		return billMapper.selectByPrimaryKey(id);
	}

	public List<Bill> getAllBill() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addBill(Bill bill) {
		return billMapper.insert(bill);
	}

	public int updateBill(Bill bill) {
		return billMapper.updateByPrimaryKey(bill);
	}

	public int deleteBill(int id) {
		return billMapper.deleteByPrimaryKey(id);
	}

}
