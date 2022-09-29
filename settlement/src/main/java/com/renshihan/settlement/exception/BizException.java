package com.renshihan.settlement.exception;

import com.renshihan.settlement.enums.BaseCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -2623309261327598087L;

    private int code;
    private String errorMessage;
    private Object[] messageArgs; // messageArgs used for format message
    private String showMe; // showMe will be followed tils for user
    private BaseCode baseCode;
    public BizException(BaseCode code) {
        super(code.getDesc());
        this.code = code.getCode();
        this.baseCode = code;
    }

    public BizException(BaseCode code, String message) {
        super(StringUtils.isEmpty(message) ? code.getDesc() : message);
        this.code = code.getCode();
        this.baseCode = code;
        this.errorMessage =message;
    }

    public BizException(BaseCode code, Throwable e) {
        super(code.getDesc(),e);
        this.code = code.getCode();
        this.baseCode = code;
        this.errorMessage =code.getDesc();
    }

    public BizException(BaseCode code, String message, Throwable e) {
        super(code.getDesc(),e);
        this.code = code.getCode();
        this.baseCode = code;
        this.errorMessage =message;

    }

}
