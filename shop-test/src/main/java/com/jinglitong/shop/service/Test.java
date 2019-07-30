/*package com.jinglitong.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinglitong.shop.entity.Customer;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Test {
	
	
	@Autowired
	private CutProgramService cutProgramService;
	
	*//**
	 * 将买了体验卡的订单设为已发货状态
	 * @return
	 *//*
	public ShopRespose updateOrderStatus(){
		try {
			cutProgramService.updateOrderStatus();
		}catch(Exception e) {
			log.error("修改订单状态失败!");
			return new ShopRespose(IConstants.FAILED);
		}
		return new ShopRespose(IConstants.SUCCESS);
	}
	
	*//**
	 * 补齐customer_member表的数据
	 * @param endTime
	 * @return
	 *//*
	public ShopRespose updateCustMemberData(String endTime) {
		try {
			cutProgramService.updateCustMemberData(endTime);
		}catch(Exception e) {
			log.error("补齐customer_member表的数据失败!");
			return new ShopRespose(IConstants.FAILED);
		}
		return new ShopRespose(IConstants.SUCCESS);
	}
	
	*//**
	 * 计算出每个人的下级总人数,VIP人数,更新到该表中.
	 * @return
	 *//*
	public ShopRespose getTeamNums() {
		try {
			cutProgramService.getTeamNums2();
		}catch(Exception e) {
			log.error("计算出每个人的下级人数失败!");
			return new ShopRespose(IConstants.FAILED);
		}
		return new ShopRespose(IConstants.SUCCESS);
	}
	
	*//**
     * 
     *將购买体验卡的用户  已下过单的修改为putong  未下过单的改为vip
     *//*
	public ShopRespose updateTiYanMember() {
		try {
			cutProgramService.updateTiYanMember();
		}catch(Exception e) {
			log.error("计算出每个人的下级人数失败!");
			return new ShopRespose(IConstants.FAILED);
		}
		return new ShopRespose(IConstants.SUCCESS);
	}
	
	
	public ShopRespose updateCustLevel(String level,String endTime) {
		try {
			cutProgramService.updateCustLevel(level,endTime);
		}catch(Exception e) {
			log.error("指定用户升级");
			return new ShopRespose(IConstants.FAILED);
		}
		return new ShopRespose(IConstants.SUCCESS);
	}
	
	
}
*/