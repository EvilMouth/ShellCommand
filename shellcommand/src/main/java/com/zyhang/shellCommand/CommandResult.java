package com.zyhang.shellCommand;

/**
 * ProjectName:ShellCommand
 * Description: shell命令结果
 * Created by zyhang on 2017/5/26.上午9:43
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class CommandResult {

    /*
     命令结果码
     */
    private int resultCode;
    /*
     成功信息,错误信息
     */
    private String successMsg, errorMsg;

    public CommandResult(int resultCode) {
        this.resultCode = resultCode;
    }

    public CommandResult(int resultCode, String successMsg, String errorMsg) {
        this.resultCode = resultCode;
        this.successMsg = successMsg;
        this.errorMsg = errorMsg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public CommandResult setResultCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public CommandResult setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public CommandResult setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    @Override
    public String toString() {
        return "CommandResult{" + "resultCode=" + resultCode +
                ", successMsg='" + successMsg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
