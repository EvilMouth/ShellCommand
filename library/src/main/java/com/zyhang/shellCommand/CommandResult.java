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

    @Override
    public String toString() {
        return "CommandResult{" + "resultCode=" + resultCode +
                ", successMsg='" + successMsg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
