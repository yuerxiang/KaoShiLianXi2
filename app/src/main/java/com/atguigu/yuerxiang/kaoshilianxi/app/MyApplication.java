package com.atguigu.yuerxiang.kaoshilianxi.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 程隆甫 on 2017/1/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志...
    }
}
