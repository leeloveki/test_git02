package com.wn.bean;

/**
 * @author yellow Docter
 * @date 2022 -06-30
 * @desc
 *   result 返回结果对象
 *
 *   code :  100000   成功的编码
 *   msg  :   成功     成功的消息
 *   data:   {
 *        list数据
 *   }
 */
public class R {

    private String msg;

    private Integer code;

    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "R{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public static R ok(){

        R r = new R();
        r.setMsg("成功");
        r.setCode(100000);

        return r;
    }

    public static R error(){

        R r = new R();
        r.setMsg("失败");
        r.setCode(200000);
        return r;
    }

    public static R loginSuccess(){
        R r = new R();
        r.setMsg("登录成功");
        r.setCode(100000);
        return r;
    }
    public static R loginFail(){
        R r = new R();
        r.setMsg("登录失败");
        r.setCode(200000);
        return r;
    }
    public static R registerSuccess(){
        R r = new R();
        r.setMsg("注册成功");
        r.setCode(100000);
        return r;
    }
    public static R registerFail(){
        R r = new R();
        r.setMsg("注册失败");
        r.setCode(200000);
        return r;
    }
}
