package com.jinglitong.shop.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinglitong.shop.service.CutProgramService;

/**
 * @Author: wanghh
 * @Date: 2019/6/5 10:51
 */
@Api(value = "会员卡佣金用户割接",tags = "会员卡佣金用户割接 API")
@Controller
@RequestMapping(value = "/business/cutProgram")
public class CutProgramController {

    /*@Autowired
    private CutProgramService cutProgramService;

    *//**
     * 将所有现有会员用户的初始日期结束日期调整.
     * 并补齐customer_member表的其他数据
     * @param endTime
     * @return
     *//*
    @RequestMapping(value = "/updateCustMemberData/{endTime}",method = RequestMethod.GET)
    @ResponseBody
    public String updateCustMemberData(@PathVariable("endTime") String endTime){
        cutProgramService.updateCustMemberData(endTime);
        return "success";
    }

    *//**
     * 将customer_member_team表数据全部补齐.
     * 并计算出每个人的下级总人数,VIP人数,更新到该表中.
     * @return
     *//*
    @RequestMapping(value = "/getTeamNums",method = RequestMethod.GET)
    @ResponseBody
    public String getTeamNums(){
        cutProgramService.getTeamNums();
        return null;
    }*/
}
