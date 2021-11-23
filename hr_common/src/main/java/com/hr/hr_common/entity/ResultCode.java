package com.hr.hr_common.entity;

public enum ResultCode {
    SUCCESS(true, 10000, "成功！"),
    FAIL(false, 10001, "失敗。"),
    UNAUTHENTICATED(false, 10002, "未登録。"),
    UNAUTHORISED(false, 10003, "権限はない"),
    SERVER_ERROR(false, 99999, "Sorry, サーバーエラーだよ。　だよ〜");


    boolean success;
    int code;
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
