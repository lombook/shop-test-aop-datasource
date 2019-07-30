package com.jinglitong.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.jinglitong.shop.entity.CustomerMemberTeam;
import com.jinglitong.springshop.utils.MyMapper;

public interface CustomerMemberTeamMapper extends MyMapper<CustomerMemberTeam> {
	
	@Update("update customer_member_team set putong_num=putong_num+#{putongNum}, vip_num=vip_num+#{vipNum},zhuguan_num=zhuguan_num+#{zhuguanNum},jingli_num=jingli_num+#{jingliNum},team_num=team_num+#{teamNum} where cust_id = #{custId}")
	Integer updateCustTeamNum(@Param("putongNum") int putongNum,@Param("vipNum") int vipNum,@Param("zhuguanNum") int zhuguanNum,
			@Param("jingliNum") int jingliNum,@Param("teamNum") int teamNum,@Param("custId") String custId);
	
	
	int updateNum(@Param("list")List<String> list,@Param("levelDefine")String levelDefine);


	void updateCustTeamById(CustomerMemberTeam ct);
	
	void updateLevel(@Param("beforeLevel")String beforeLevel,@Param("afterLevel")String afterLevel,@Param("custId")String custId);
}