package com.jinglitong.shop.mapper;

import com.jinglitong.shop.entity.Orders;
import com.jinglitong.shop.vo.CustomerVo;
import com.jinglitong.shop.utils.MyMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper extends MyMapper<Orders> {

	List<Orders> getBuyTiyanOrder(@Param("skuId") String skuId);

	/**
	 * 查询用户购买体验卡之后是否下过单
	 * @param custVo
	 * @return
	 */
	List<Orders> ifOrders(CustomerVo custVo);

}