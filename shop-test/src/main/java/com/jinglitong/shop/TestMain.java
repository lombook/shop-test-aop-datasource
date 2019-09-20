/*package com.jinglitong.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jinglitong.shop.entity.Customer;
import com.jinglitong.shop.entity.CustomerMember;
import com.jinglitong.shop.mapper.CustomerMapper;
import com.jinglitong.shop.mapper.CustomerMemberMapper;
@Component
public class TestMain implements ApplicationRunner{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private com.jinglitong.shop.service.CutProgramService cutProgramService;
	@Value("${level.member}")
	private String levelMember;
	@Value("${level.date}")
	private String levelDate;

	    @Override
	    public void run(ApplicationArguments var1) throws Exception{
	        System.out.println("MyApplicationRunner class will be execute when the project was started!");	        
	        
	        //补齐customer_member表的数据 参数有效期结束时间
	        long begin1 = System.currentTimeMillis();
	        Test.updateCustMemberData("2019-12-31 23:59:59");
	        long end1 = System.currentTimeMillis();
	        System.out.print("将customer数据迁移到customer_member所用时间 :::"+(end1-begin1));
	        logger.info("将customer数据迁移到customer_member所用时间 :::"+(end1-begin1));
	        
	        //將购买体验卡的用户  已下过单的修改为putong  未下过单的改为vip
	        long begin4 = System.currentTimeMillis();
	        Test.updateTiYanMember();
	        long end4 = System.currentTimeMillis();
	        System.out.print("修改购买体验卡用户的身份所用时间 :::"+(end4-begin4));
	        logger.info("修改购买体验卡用户的身份所用时间 :::"+(end4-begin4));
	        
	        // 将买了体验卡的订单设为已发货状态
	        long begin2 = System.currentTimeMillis();
	        Test.updateOrderStatus();
	        long end2 = System.currentTimeMillis();
	        System.out.print("将买了体验卡的订单设为已发货状态 所用时间 :::"+(end2-begin2));
	        logger.info("将买了体验卡的订单设为已发货状态 所用时间 :::"+(end2-begin2));
	        
	        //计算出每个人的下级总人数,VIP人数,更新到该表中 
	        long begin3 = System.currentTimeMillis();
	        Test.getTeamNums();
	        long end3 = System.currentTimeMillis();
	        System.out.print("计算出每个人的下级总人数 所用时间 :::"+(end3-begin3));
	        logger.info("计算出每个人的下级总人数 所用时间 :::"+(end3-begin3));
	        
	        //指定用户升级
	        long begin5 = System.currentTimeMillis();//hehuoren vip zhuguan putong jingli
	        //Test.updateCustLevel(levelMember,levelDate);//"2020-06-20 23:59:59"
	        List<CustomerMember>  one = cutProgramService.selectOne();
	        List<CustomerMember>  two  = cutProgramService.selectTwo();
	        
	        System.out.println(one.size()  +"==="+two.size());
	        long end5 = System.currentTimeMillis();
	        logger.info("升级所用时间 :::"+(end5-begin5));
	        
	    }
	   
		



}*/