package com.atguigu.yuerxiang.kaoshilianxi.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.yuerxiang.kaoshilianxi.R;
import com.atguigu.yuerxiang.kaoshilianxi.base.BaseFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 程隆甫 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {
    private static final String TAG = NetAudioFragment.class.getSimpleName();

    @ViewInject(R.id.listview)
    private ListView listview;

    @ViewInject(R.id.progressbar)
    private ProgressBar progressbar;

    @ViewInject(R.id.tv_nomedia)
    private TextView tvNomedia;
    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
        //把view注入到xUtils3框中
        x.view().inject(NetAudioFragment.this,view);
        //才初始化好的

        return view;
    }



    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "网络音频数据初始化了");
    }

 /*   @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
*/
}
