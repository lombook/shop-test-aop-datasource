package com.jinglitong.shop.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_member_team")
public class CustomerMemberTeam {
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
     * putong:普通用户 vip:vip zhuguan:主管 jingli:经理
     */
    @Column(name = "level_define")
    private String levelDefine;

    /**
     * 团队内普通用户
     */
    @Column(name = "putong_num")
    private Integer putongNum;

    /**
     * 团队内VIP
     */
    @Column(name = "vip_num")
    private Integer vipNum;

    /**
     * 团队内主管数量
     */
    @Column(name = "zhuguan_num")
    private Integer zhuguanNum;

    /**
     * 团队经理数量
     */
    @Column(name = "jingli_num")
    private Integer jingliNum;
    
    @Column(name = "hehuoren_num")
    private Integer hehuorenNum;

    /**
     * 团队人数总数
     */
    @Column(name = "team_num")
    private Integer teamNum;

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
     * 获取团队内普通用户
     *
     * @return putong_num - 团队内普通用户
     */
    public Integer getPutongNum() {
        return putongNum;
    }

    /**
     * 设置团队内普通用户
     *
     * @param putongNum 团队内普通用户
     */
    public void setPutongNum(Integer putongNum) {
        this.putongNum = putongNum;
    }

    /**
     * 获取团队内VIP
     *
     * @return vip_num - 团队内VIP
     */
    public Integer getVipNum() {
        return vipNum;
    }

    /**
     * 设置团队内VIP
     *
     * @param vipNum 团队内VIP
     */
    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    /**
     * 获取团队内主管数量
     *
     * @return zhuguan_num - 团队内主管数量
     */
    public Integer getZhuguanNum() {
        return zhuguanNum;
    }

    /**
     * 设置团队内主管数量
     *
     * @param zhuguanNum 团队内主管数量
     */
    public void setZhuguanNum(Integer zhuguanNum) {
        this.zhuguanNum = zhuguanNum;
    }

    /**
     * 获取团队经理数量
     *
     * @return jingli_num - 团队经理数量
     */
    public Integer getJingliNum() {
        return jingliNum;
    }

    /**
     * 设置团队经理数量
     *
     * @param jingliNum 团队经理数量
     */
    public void setJingliNum(Integer jingliNum) {
        this.jingliNum = jingliNum;
    }

    /**
     * 获取团队人数总数
     *
     * @return team_num - 团队人数总数
     */
    public Integer getTeamNum() {
        return teamNum;
    }

    /**
     * 设置团队人数总数
     *
     * @param teamNum 团队人数总数
     */
    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
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