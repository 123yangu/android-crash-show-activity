package com.takwolf.androidcrashshow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 入口活动页面，这里我们手动制造一些异常来进行测试
 * @author TakWolf
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 绑定Toast显示按钮
     * @param view
     */
    public void onBtnToast(View view) {
    	Toast.makeText(this, "卖萌的提示     o(>_<)o ~~", Toast.LENGTH_SHORT).show();
    }

    /**
     * 绑定异常制造按钮
     * @param view
     */
    public void onBtnException(View view) {
    	//制造一个数组越界
    	String[] arr = new String[7];
    	arr[8].toString(); //此处将报异常
    }

}
