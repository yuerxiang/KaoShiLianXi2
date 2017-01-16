package com.atguigu.yuerxiang.kaoshilianxi.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.yuerxiang.kaoshilianxi.base.BaseFragment;

/**
 * Created by 程隆甫 on 2017/1/16.
 */

public class LocalVideoFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e("TAG","本地视频初始化了。。");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG","本地视频数据初始化了。。");
        textView.setText("本地视频");
    }


    @Override
    public void onRefrshData() {
        super.onRefrshData();
        textView.setText("本地视频刷新");
//        Log.e("TAG","onHiddenChanged。。"+this.toString());
    }


}
