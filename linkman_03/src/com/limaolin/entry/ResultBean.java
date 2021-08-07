package com.limaolin.entry;

import java.io.Serializable;

/**
 * 包名:com.itheima.entry
 *
 * @author Leevi
 * 日期2020-07-21  10:47
 */
public class ResultBean implements Serializable {
    //flag 表示服务器是否异常
    private Boolean flag;
    //data 表示这次请求的处理结果
    private Object data;
    //errorMsg 表示服务器的异常信息
    private String errorMsg;

    public ResultBean() {
    }

    public ResultBean(Boolean flag) {
        this.flag = flag;
    }

    public ResultBean(Boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultBean(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public ResultBean(Boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
