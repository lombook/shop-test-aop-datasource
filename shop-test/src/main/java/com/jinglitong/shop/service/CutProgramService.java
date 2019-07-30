package com.jinglitong.shop.service;

import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.jinglitong.shop.datasource.DataSource;
import com.jinglitong.shop.entity.Customer;
import com.jinglitong.shop.entity.CustomerMember;
import com.jinglitong.shop.entity.CustomerMemberTeam;
import com.jinglitong.shop.mapper.*;
import com.jinglitong.shop.util.DateUtil;
import com.jinglitong.shop.util.UuidUtil;
import com.jinglitong.shop.vo.CustomerVo;
import com.jinglitong.shop.entity.Orders;
import com.jinglitong.shop.mapper.OrdersMapper;
import com.jinglitong.springshop.vo.CustomerTeamVo;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.utils.DateUtils;
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
    
	@Autowired
    private OrdersMapper ordersMapper;
    
    @Value("${tiyancard.skuid}")
    private String TI_YAN_CARD_SKUID;

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
     * 将所有现有会员用户的初始日期结束日期调整.并补齐customer_member表的其他数据.
     * @param endTime
     * @throws ParseException 
     */
    public void updateCustMemberData(String endTime) throws ParseException {
        List<CustomerMember> memberList = customerMemberMapper.selectAll();
        
        // 1, 割接customer_member中的数据
        /*if(memberList != null && memberList.size() > 0) {
        	for (CustomerMember customerMember : memberList) {
                // 判断用户是否是会员
                if (1 == customerMember.getIsMember()){
                    // 是会员
                    customerMember.setLevelDefine("vip");
                    customerMember.setLevelDefineHis("putong");
                    customerMember.setStartTime(new Date()); // 生效时间
                    customerMember.setEndTime(DateUtil.stringToDate1(endTime));// 失效时间
                }else if (0 == customerMember.getIsMember()){
                    // 不是会员
                    customerMember.setLevelDefine("putong");
                    customerMember.setLevelDefineHis("putong");
                    //customerMember.setStartTime(new Date()); // 生效时间
                    //customerMember.setEndTime(new Date());// 失效时间
                }
                // 更新会员信息
                customerMemberMapper.updateByPrimaryKeySelective(customerMember);
            }
        }*/
        
        // 2, 将所有customer用户未在customer_member中的数据迁移过来
        List<Customer> customers = customerMapper.getCustNoMember();
        if(customers != null && customers.size() > 0) {
        	for(Customer cust : customers) {
            	CustomerMember member = new CustomerMember();
            	member.setZid(UuidUtil.getUUID());
            	member.setCustId(cust.getZid());
            	member.setLevelDefine("putong");
            	member.setLevelDefineHis("putong");
            	//member.setStartTime(new Date()); // 生效时间
            	//member.setEndTime(new Date());// 失效时间
            	member.setCreateTime(new Date());
            	member.setUpdateTime(new Date());
            	customerMemberMapper.insert(member);
            }
        }
        
    }

    /**
     * 
     *將购买体验卡的用户  已下过单的修改为putong  未下过单的改为vip
     */
    public void updateTiYanMember() throws ParseException {
        // 1 查询购买体验卡的用户
        List<CustomerVo> custvoList = customerMapper.getCustTiYanCart(TI_YAN_CARD_SKUID);
        
        if(null != custvoList && custvoList.size() > 0) {
        	for(CustomerVo custVo : custvoList) {
        		// 2 查询该用户在购买体验卡之后有没有下过单
        		CustomerMember member = customerMemberMapper.getMemberByCustId(custVo.getCustId());
        		List<Orders> orders = ordersMapper.ifOrders(custVo);
        		if(null != orders && orders.size()>0) {
        			// 将用户设置为普通用户
        			member.setLevelDefine("putong");
        			member.setEndTime(null);
        			member.setStartTime(null); 
        			member.setUpdateTime(new Date());
        		}else {
        			// 将用户设置为VIP
        			member.setLevelDefine("vip");
        			member.setEndTime(DateUtil.stringToDate1("2019-12-31 23:59:59"));// 失效时间
        			member.setStartTime(new Date()); // 生效时间
        			member.setUpdateTime(new Date());
        		}
        		customerMemberMapper.updateByPrimaryKey(member);
        	}
        }
    }
   
    /**
     * 所有之前购买了体验卡的用户设置为等级普通,并将所有买了体验卡的订单设为已发货状态
     */
    public void updateOrderStatus() {
    	List<Orders> orderList = ordersMapper.getBuyTiyanOrder(TI_YAN_CARD_SKUID);
    	for(Orders order : orderList) {
    		// 状态改为已发货
    		order.setStatus(3);
    		ordersMapper.updateByPrimaryKey(order);
    	}
    }
    
    
    /**
     * 计算出每个人的下级总人数,VIP人数,更新到该表中.
     */
	public void getTeamNums2() {
        // 1,查询所有用户
        List<Customer> customers = customerMapper.selectAll();
        
        for (Customer cust : customers) {
            // 1.1 查询该用户的身份
            CustomerMember customerMember = new CustomerMember();
            customerMember.setCustId(cust.getZid());
            customerMember = customerMemberMapper.selectOne(customerMember);
            
            Map<String, Integer> map = new HashMap<>();
            map.put("putong", 0);
            map.put("vip", 0);
            doCount(cust,map);
            //System.out.println(map);
            
            CustomerMemberTeam team = new CustomerMemberTeam();
            team.setZid(UuidUtil.getUUID());
            team.setCustId(cust.getZid());
            team.setLevelDefine(customerMember.getLevelDefine());
            team.setPutongNum(map.get("putong"));
            team.setVipNum(map.get("vip"));
            team.setZhuguanNum(0);
            team.setJingliNum(0);
            team.setTeamNum(map.get("putong") + map.get("vip"));
            team.setCreateTime(new Date());
            team.setUpdateTime(new Date());
            customerMemberTeamMapper.insert(team);
            
        }
    }
	public void doCount(Customer customer,Map<String,Integer> map){
		List<Customer> customerList = customerMapper.getCustByInviteCode(customer.getSelfInvite());
		
		if(customerList==null ||customerList.size()==0){
			return ;
		}
		List<CustomerTeamVo> custTeamNums2 = customerMemberMapper.getCustTeamNums(customer.getSelfInvite());
		for (CustomerTeamVo custTeamNum : custTeamNums2) {
            if ("putong".equals(custTeamNum.getLevelDefine())){
            	Integer putongNum = map.get("putong");
                putongNum += custTeamNum.getNumbers();
                map.put("putong", putongNum);
            }else if("vip".equals(custTeamNum.getLevelDefine())){
            	Integer vipNum = map.get("vip");
                vipNum += custTeamNum.getNumbers();
                map.put("vip", vipNum);
            }
        }
		if(customerList!=null &&customerList.size()>0){
			for (Customer child : customerList) {
				//递归
				doCount(child,map);
			}
				
		} 
	}
	
	
	
	
	
	
	 /**
     * 计算出每个人的下级总人数,VIP人数,更新到该表中.
     */
    public void getTeamNums() {
        // 1,查询所有团队
        //List<CustomerMemberTeam> customerMemberTeams = customerMemberTeamMapper.selectAll();
        List<Customer> customers = customerMapper.selectAll();
        for (Customer cust : customers) {
            // 1.1 查询该用户的身份
            CustomerMember customerMember = new CustomerMember();
            customerMember.setCustId(cust.getZid());
            customerMember = customerMemberMapper.selectOne(customerMember);
            // 1.2 查询该用户下级各个成员身份及个数
            List<CustomerTeamVo> custTeamNums = customerMemberMapper.getCustTeamNums(cust.getZid());
            // 1.3 customer_member_team 插入数据
            int putongNum = 0;
            int vipNum = 0;
            int zhuguanNum = 0;
            int jingliNum = 0;

            for (CustomerTeamVo custTeamNum : custTeamNums) {
                if ("putong".equals(custTeamNum.getLevelDefine())){
                    putongNum = custTeamNum.getNumbers();
                }else if("vip".equals(custTeamNum.getLevelDefine())){
                    vipNum = custTeamNum.getNumbers();
                }else if ("zhuguan".equals(custTeamNum.getLevelDefine())){
                    zhuguanNum = custTeamNum.getNumbers();
                }else if ("jingli".equals(custTeamNum.getLevelDefine())){
                    jingliNum = custTeamNum.getNumbers();
                }
            }
            int teamNum = putongNum + vipNum + zhuguanNum + jingliNum;
            
            CustomerMemberTeam team = new CustomerMemberTeam();
            team.setZid(UuidUtil.getUUID());
            team.setCustId(cust.getZid());
            team.setLevelDefine(customerMember.getLevelDefine());
            team.setPutongNum(putongNum);
            team.setVipNum(vipNum);
            team.setZhuguanNum(zhuguanNum);
            team.setJingliNum(jingliNum);
            team.setTeamNum(teamNum);
            team.setCreateTime(new Date());
            team.setUpdateTime(new Date());
            customerMemberTeamMapper.insert(team);
        }

    }
    public Map<String,Integer> selectNums(Map<String,Integer> map,List<Customer> customers) {
    	int putongNum = 0;
        int vipNum = 0;
        int zhuguanNum = 0;
        int jingliNum = 0;
        int teamNum = 0;
    	
    	for(int i = 0; i < customers.size(); i++) {
    		List<Customer> customerList = customerMapper.getCustOne(customers.get(i).getZid());
    		if(null == customerList || customerList.size() == 0) {
        		continue;
        	}
    		List<CustomerTeamVo> custTeamNums2 = customerMemberMapper.getCustTeamNums(customers.get(i).getZid());
    		for (CustomerTeamVo custTeamNum : custTeamNums2) {
                if ("putong".equals(custTeamNum.getLevelDefine())){
                    putongNum += custTeamNum.getNumbers();
                }else if("vip".equals(custTeamNum.getLevelDefine())){
                    vipNum = +custTeamNum.getNumbers();
                }else if ("zhuguan".equals(custTeamNum.getLevelDefine())){
                    zhuguanNum += custTeamNum.getNumbers();
                }else if ("jingli".equals(custTeamNum.getLevelDefine())){
                    jingliNum += custTeamNum.getNumbers();
                }
            }
    		map.put("putongNum", putongNum);
        	map.put("vipNum",vipNum);
        	map.put("zhuguanNum", zhuguanNum);
        	map.put("jingliNum", jingliNum);
        	
    		selectNums(map,customerList);
    	}
    	teamNum = putongNum + vipNum + zhuguanNum + jingliNum;
    	map.put("teamNum", teamNum);
    	return map;
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
