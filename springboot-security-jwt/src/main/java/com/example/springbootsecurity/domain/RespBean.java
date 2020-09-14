package com.example.springbootsecurity.domain;

public class RespBean {
    private String code;
    private boolean flag;
    private Object entity;
    private String msg;

    public static RespBean success(String msg,Object object){
        RespBean bean = new RespBean();
        bean.setCode("200");
        bean.setFlag(true);
        bean.setEntity(object);
        bean.setMsg(msg);
        return bean;
    }

    public static RespBean error(String msg){
        RespBean bean = new RespBean();
        bean.setCode("-1");
        bean.setFlag(false);
        bean.setEntity(null);
        bean.setMsg(msg);
        return bean;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
