package com.liangxunwang.unimanager.model.tip;

public class SuccessTip implements Tip {
    private boolean success;
    private int code;
    private String message;

    public SuccessTip() {
        super();
        this.success = true;
        this.code = 200;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
