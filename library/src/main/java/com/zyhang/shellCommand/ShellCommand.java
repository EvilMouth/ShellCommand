package com.zyhang.shellCommand;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * ProjectName:ShellCommand
 * Description: shell命令器
 * Created by zyhang on 2017/5/26.上午9:43
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class ShellCommand {

    private static final String COMMAND_SU = "su";
    private static final String COMMAND_SH = "sh";
    private static final String COMMAND_EXIT = "exit\n";
    private static final String COMMAND_LINE_END = "\n";

    private ShellCommand() {
    }

    public static CommandResult exec(String command, boolean needRoot) {
        return exec(command, needRoot, true);
    }

    public static CommandResult exec(String command, boolean needRoot, boolean needResult) {
        return exec(new String[]{command}, needRoot, needResult);
    }

    public static CommandResult exec(List<String> commands, boolean needRoot) {
        return exec(commands, needRoot, true);
    }

    public static CommandResult exec(List<String> commands, boolean needRoot, boolean needResult) {
        return exec(commands.toArray(new String[]{}), needRoot, needResult);
    }

    public static CommandResult exec(String[] commands, boolean needRoot) {
        return exec(commands, needRoot, true);
    }

    /**
     * 执行命令
     *
     * @param commands   命令
     * @param needRoot   是否需要root权限
     * @param needResult 是否需要结果
     * @return {@link CommandResult}
     */
    public static CommandResult exec(String[] commands, boolean needRoot, boolean needResult) {
        int resultCode = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(resultCode);
        }

        Process process = null;
        DataOutputStream os = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;

        try {
            process = Runtime.getRuntime().exec(needRoot ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                /*
                 不要使用os.writeBytes(command)，避免中文字符报错
                 */
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();

            resultCode = process.waitFor();
            /*
             如果需要结果
             */
            if (needResult) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line;
                while (!TextUtils.isEmpty(line = successReader.readLine())) {
                    successMsg.append(line);
                }
                while (!TextUtils.isEmpty(line = errorReader.readLine())) {
                    errorMsg.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successReader != null) {
                    successReader.close();
                }
                if (errorReader != null) {
                    errorReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }

        return new CommandResult(resultCode,
                null != successMsg ? successMsg.toString() : null,
                null != errorMsg ? errorMsg.toString() : null);
    }
}
