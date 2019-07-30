package com.jinglitong.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinglitong.shop.entity.CustomerMember;
import com.jinglitong.shop.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMemberMapper extends MyMapper<CustomerMember> {

   void getCustTeamNums(@Param("selfInvite") String selfInvite);

	CustomerMember getMemberByCustId(@Param("custId") String custId);

	public CustomerMember selectUpperCustomerMemberByCustzid(@Param("zid") String zid);

	List<CustomerMember> getCustomerMemberList(List<String> list);

	List<CustomerMember> selectCustMembers();
}