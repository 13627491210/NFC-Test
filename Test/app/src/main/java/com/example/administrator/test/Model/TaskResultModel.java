package com.example.administrator.test.Model;

import com.alibaba.fastjson.annotation.JSONField;

import realsun.webpos.model.BaseRecord;

/**
 * Created by hantao on 2017/10/15.
 */

public class TaskResultModel extends BaseRecord {
//    C3_558730712230 任务编号
//    C3_558730712474 完成时间 时间 8  显示
//3 编辑 C3_558730746737 是否完成 文字 8  显示
//4 编辑 C3_558730759532 返回信息

@JSONField(name ="C3_558730712230")
    private String taskid;

    @JSONField(name ="C3_558730746737")
    private String isFinish;
    @JSONField(name ="C3_558730759532")
    private String message;
    @JSONField(name ="C3_561398113543")
    private int maintotal=0;
    @JSONField(name ="C3_561398125148")
    private  int subtotal=0;

    public int getMaintotal() {
        return maintotal;
    }

    public void setMaintotal(int maintotal) {
        this.maintotal = maintotal;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getTaskid() {
        return taskid;
    }
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
