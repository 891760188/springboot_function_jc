package ye.guo.huang.jwt.common;

import ye.guo.huang.jwt.common.enums.HttpStatusEnum;

/**
 *  回复前端数据类
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

public class ResponseBean {

    // http 状态码 HttpStatusEnum
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public static ResponseBean response(Object data){
        return new ResponseBean(data);
    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean(Object data) {
        this.code = HttpStatusEnum.OK.code();
        this.data = data;
    }

    public ResponseBean() {
        this.code = HttpStatusEnum.OK.code();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}