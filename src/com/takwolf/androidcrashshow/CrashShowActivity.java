package com.takwolf.androidcrashshow;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;

/**
 * 异常信息显示页面
 * @author TakWolf
 */
public class CrashShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_show);
        //接收异常对象
        Intent intent = getIntent();
        Throwable e = (Throwable) intent.getSerializableExtra("e");
        //构建字符串
        StringBuffer sb = new StringBuffer();
        sb.append("非常抱歉，程序运行过程中出现了一个无法避免的错误。");
        sb.append("请您及时与开发者取得联系并反馈该问题，此举将有助于我们改善应用体验。");
        sb.append("以此给您带来的诸多不便，我们深表歉意，敬请谅解。\n");
        sb.append("----------------------\n");
        sb.append("异常时间：\n");
        Time time = new Time();
        time.setToNow();
        sb.append(time.toString() + "\n\n");
        sb.append("异常类型：\n");
        sb.append(e.getClass().getName() + "\n\n");
        sb.append("异常信息：\n");
        sb.append(e.getMessage() + "\n\n");
        sb.append("异常堆栈：\n" );
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        //显示信息
        TextView tvInfo = (TextView) findViewById(R.id.crash_show_tv_info);
        tvInfo.setText(sb.toString());
    }

}
