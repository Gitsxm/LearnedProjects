package com.example.demo_exceptionhandler.exceptionhandle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    /**
     * 无参构造
     */
    public BizException() {
        super();
    }

    /**
     * BaseErrorInfoInterface 参数构造
     * @param errorInfoInterface
     */
    public BizException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    /**
     * BaseErrorInfoInterface，Throwable 参数构造
     * @param errorInfoInterface
     * @param cause
     */
    public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    /**
     * 错误信息参数构造
     * @param errorMsg
     */
    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    /**
     * 错误信息、编码 参数构造
     * @param errorCode
     * @param errorMsg
     */
    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 编码、信息、异常父类 参数构造
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
