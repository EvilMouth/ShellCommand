package com.zyhang.shellCommand.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zyhang.shellCommand.CommandResult;
import com.zyhang.shellCommand.ShellCommand;

/**
 * ProjectName:ShellCommand
 * Description:
 * Created by zyhang on 2017/6/29.23:44
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MainActivity extends AppCompatActivity {

    private CheckBox mCheckBox;
    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
    }

    public void enter(View view) {
        boolean needRoot = mCheckBox.isChecked();
        String command = mEditText.getText().toString();
        CommandResult result = ShellCommand.exec(command, needRoot, true);
        mTextView.setText(result.toString());
    }
}
