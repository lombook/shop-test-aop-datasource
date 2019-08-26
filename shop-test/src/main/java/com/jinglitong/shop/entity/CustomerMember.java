package com.jinglitong.shop.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_member")
public class CustomerMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String zid;

    /**
     * 用户custId
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 0不是会员 1会员
     *//*
    @Column(name = "is_member")
    private Integer isMember;*/

    /**
     * putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     */
    @Column(name = "level_define")
    private String levelDefine;

    /**
     * 曾经最高等级 默认为普通
     */
    @Column(name = "level_define_his")
    private String levelDefineHis;

    /**
     * 生效时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 失效时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return zid
     */
    public String getZid() {
        return zid;
    }

    /**
     * @param zid
     */
    public void setZid(String zid) {
        this.zid = zid == null ? null : zid.trim();
    }

    /**
     * 获取用户custId
     *
     * @return cust_id - 用户custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置用户custId
     *
     * @param custId 用户custId
     */
    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    /**
     * 获取0不是会员 1会员
     *
     * @return is_member - 0不是会员 1会员
     */
    /*public Integer getIsMember() {
        return isMember;
    }

    *//**
     * 设置0不是会员 1会员
     *
     * @param isMember 0不是会员 1会员
     *//*
    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }*/

    /**
     * 获取putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     *
     * @return level_define - putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     */
    public String getLevelDefine() {
        return levelDefine;
    }

    /**
     * 设置putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     *
     * @param levelDefine putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     */
    public void setLevelDefine(String levelDefine) {
        this.levelDefine = levelDefine == null ? null : levelDefine.trim();
    }

    /**
     * 获取曾经最高等级 默认为普通
     *
     * @return level_define_his - 曾经最高等级 默认为普通
     */
    public String getLevelDefineHis() {
        return levelDefineHis;
    }

    /**
     * 设置曾经最高等级 默认为普通
     *
     * @param levelDefineHis 曾经最高等级 默认为普通
     */
    public void setLevelDefineHis(String levelDefineHis) {
        this.levelDefineHis = levelDefineHis == null ? null : levelDefineHis.trim();
    }

    /**
     * 获取生效时间
     *
     * @return start_time - 生效时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置生效时间
     *
     * @param startTime 生效时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取失效时间
     *
     * @return end_time - 失效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置失效时间
     *
     * @param endTime 失效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}