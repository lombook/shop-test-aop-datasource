package com.jinglitong.shop.service;

import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.jinglitong.shop.datasource.DataSource;
import com.jinglitong.shop.entity.Customer;
import com.jinglitong.shop.entity.CustomerMember;
import com.jinglitong.shop.entity.CustomerMemberTeam;
import com.jinglitong.shop.mapper.*;
import com.jinglitong.shop.utils.DateUtil;
import com.jinglitong.shop.utils.UuidUtil;
import com.jinglitong.shop.vo.CustomerVo;
import com.jinglitong.shop.entity.Orders;
import com.jinglitong.shop.mapper.OrdersMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @Author: wanghh
 * @Date: 2019/6/5 10:59
 */
@Service
@Transactional
@Slf4j
public class CutProgramService {

	@Autowired
    private CustomerMemberMapper customerMemberMapper;

	@Autowired
    private CustomerMapper customerMapper;

	@Autowired
    private CustomerMemberTeamMapper customerMemberTeamMapper;
    
	@DataSource(name="first")
    public List<CustomerMember> selectOne() {
    	List<CustomerMember> memberList = customerMemberMapper.selectCustMembers();
    	return memberList;
    }
	@DataSource(name="second")
    public List<CustomerMember> selectTwo() {
    	List<CustomerMember> memberList = customerMemberMapper.selectCustMembers();
    	return memberList;
    }
    
  
    
    /**
     * 
     * 功能说明:   等級、end时间
     * @throws ParseException
     */
    public void updateCustLevel(String level,String endTime) throws ParseException {
    	// 1,查询用户
    	List<CustomerMember> list = customerMemberMapper.selectCustMembers();
    	log.info("升级用户个数="+list.size());
    	// 2,更新有效期
    	int a = 0;
    	for(CustomerMember cm : list) {
    		String beforeLevel = cm.getLevelDefine();
    		a++;
    		log.info("=======第"+a+"个开始");
    		if(level.equals(cm.getLevelDefine())) {
    			if(StringUtils.isNotBlank(endTime)) {
    				cm.setStartTime(new Date());
        			cm.setEndTime(DateUtil.stringToDate1(endTime));
        			cm.setUpdateTime(new Date());
        			customerMemberMapper.updateByPrimaryKey(cm);
    			}
    		}else{
    			cm.setLevelDefine(level);
    			cm.setLevelDefineHis(beforeLevel);
    			if(StringUtils.isNotBlank(endTime)) {
    				cm.setStartTime(new Date());
    				cm.setEndTime(DateUtil.stringToDate1(endTime));
    			}
    			cm.setUpdateTime(new Date());
    			customerMemberMapper.updateByPrimaryKey(cm);
    			
    			// 更新team表
        		CustomerMemberTeam team = new CustomerMemberTeam();
        		team.setCustId(cm.getCustId());
        		team = customerMemberTeamMapper.selectOne(team);
        		
        		CustomerMemberTeam teamSelf = new CustomerMemberTeam();
        		teamSelf.setLevelDefine(level);
        		teamSelf.setUpdateTime(new Date());
        		teamSelf.setId(team.getId());
        		customerMemberTeamMapper.updateByPrimaryKeySelective(teamSelf);
        		// 查询该用户的上级
        		List<String> custIdList = getCust(cm.getCustId());
        		log.info("当前用户{}，所有上级{}",cm.getCustId(),custIdList);
        		
        		if(null != custIdList && custIdList.size()>0) {
        			for(String custId:custIdList) {
            			/*CustomerMemberTeam ct = new CustomerMemberTeam();
                		ct.setCustId(custId);*/
                		customerMemberTeamMapper.updateLevel(beforeLevel,level ,custId);
            		}
        		}
    		}
    		log.info("=======第"+a+"个结束");
    	}
    	
    }
    
    private List<String> getCust(String custId){
		List<String> list = new LinkedList<>();
		Customer record = new Customer();
		record.setZid(custId);
		record = customerMapper.selectOne(record);
		getByCode(list, record.getInviteCode());
		return list;
	}
	
	private void getByCode(List<String> list ,String code) {
		if(StringUtils.isNotEmpty(code)) {
			Customer cus = new Customer();
			cus.setSelfInvite(code);
			cus = customerMapper.selectOne(cus);
			if(cus != null) {
				list.add(cus.getZid());
			}
			if(StringUtils.isNotEmpty(cus.getInviteCode())) {
				getByCode(list, cus.getInviteCode());
			}
		}
		
	}
}
