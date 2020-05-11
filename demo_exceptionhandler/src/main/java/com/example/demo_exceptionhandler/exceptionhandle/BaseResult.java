package com.example.demo_exceptionhandler.exceptionhandle;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int RESULT_FAIL = 0;
    public static final int RESULT_SUCCESS = 1;

    //返回代码
    private String code;

    //返回消息
    private String message;

    //返回对象
    private Object result;

    /**
     * 构造重载
     * @param code
     * @param message
     */
    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     *
     * @return
     */
    public static BaseResult success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static BaseResult success(Object data) {
        BaseResult br = new BaseResult(CommonEnum.SUCCESS.getResultCode(), CommonEnum.SUCCESS.getResultMsg(), data);
        return br;
    }

    /**
     * 失败
     *
     * @param errorInfoInterface
     * @return
     */
    public static BaseResult error(BaseErrorInfoInterface errorInfoInterface) {
        BaseResult br = new BaseResult(errorInfoInterface.getResultCode(), errorInfoInterface.getResultMsg(), null);
        return br;
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResult error(String code, String message) {
        BaseResult br = new BaseResult(code, message, null);
        return br;
    }

    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static BaseResult error(String message) {
        BaseResult br = new BaseResult("-1", message, null);
        return br;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
