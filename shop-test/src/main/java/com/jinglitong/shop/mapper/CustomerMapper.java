package com.jinglitong.shop.mapper;

import com.jinglitong.shop.entity.Customer;
import com.jinglitong.shop.util.MyMapper;
import com.jinglitong.shop.vo.CustomerVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface CustomerMapper extends MyMapper<Customer> {
   /* *//**
     * 根据zid获取用户信息
     * @param zid
     * @return
     *//*
    CustomerVo selectByCustId(@Param("zid") String zid);

    CustomerVo get(@Param("zid") String zid);

    *//**
     * 修改用户认证状态
     * @param state
     * @param custId
     *//*
    @Update("update customer set certificate_state = #{state} where zid = #{custId}")
    Integer updateCertificateStateByzid(@Param("state") int state, @Param("custId") String custId);

    List<Map<String,String>> selectinviteList(@Param("zid") String zid);

    List<Customer> selectCustList(CustVO custVO);

    Customer getByCustId(Customer customer);

    *//**
     * 获取直接邀请人
     * @param customer
     * @return
     *//*
    Customer getDircetInviter(Customer customer);

    *//**
     * 获取用户邀请关系List
     * @param customer
     * @return
     *//*
    List<HashMap<String ,Object>> getInviteList(Customer customer);

    Customer selectByCustIdOrInviteCode(@Param("custId") String custId,@Param("inviteCode") String inviteCode);

    CustomerOrderVO selectBySubOrderId(@Param("subOrderId")String subOrderId);

    Customer getHighLevelByCustId(@Param("custId") String custId);*/

	List<Customer> getCustNoMember();

	List<CustomerVo> getCustTiYanCart(@Param("skuId")String skuId);
	
	List<Customer> getCustByInviteCode(@Param("inviteCode") String inviteCode);

	List<Customer> getCustOne(@Param("custId") String custId);

	List<Customer> getCustByTreeLevel(@Param("treeLevel")int treeLevel);
}