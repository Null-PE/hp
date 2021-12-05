package com.hr.hr_common.exception;


import com.hr.hr_common.entity.ResultCode;

public class CommonException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private ResultCode code = ResultCode.SERVER_ERROR;

    public CommonException() {
    }

    public ResultCode getCode() {
        return code;
    }

    public CommonException(ResultCode code) {
        super(code.message());
        this.code = code;
    }
}
