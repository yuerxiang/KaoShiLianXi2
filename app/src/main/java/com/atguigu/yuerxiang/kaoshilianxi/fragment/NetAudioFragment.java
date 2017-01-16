package com.atguigu.yuerxiang.kaoshilianxi.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.yuerxiang.kaoshilianxi.R;
import com.atguigu.yuerxiang.kaoshilianxi.adapter.NetAudioFragmentAdapter;
import com.atguigu.yuerxiang.kaoshilianxi.base.BaseFragment;
import com.atguigu.yuerxiang.kaoshilianxi.bean.NetAudioBean;
import com.atguigu.yuerxiang.kaoshilianxi.utils.CacheUtils;
import com.atguigu.yuerxiang.kaoshilianxi.utils.Constant;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by 程隆甫 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {


    private NetAudioFragmentAdapter myAdapter;
    private List<NetAudioBean.ListBean> datas;
    private static final String TAG = NetAudioFragment.class.getSimpleName();
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;

    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "网络视频数据初始化了...");

        String saveJson = CacheUtils.getString(mContext, Constant.NET_AUDIO_URL);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        getDataFromNet();
    }

    private void processData(String json) {
        NetAudioBean netAudioBean = paraseJson(json);
        LogUtil.e(netAudioBean.getList().get(0).getText()+"-----------");

        datas = netAudioBean.getList();

        if(datas != null && datas.size() >0){
            //有视频
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            myAdapter = new NetAudioFragmentAdapter(mContext,datas);
            listview.setAdapter(myAdapter);
        }else{
            //没有视频
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }
    /**
     * json解析数据
     * @param json
     * @return
     */
    private NetAudioBean paraseJson(String json) {
        return new Gson().fromJson(json,NetAudioBean.class);
    }





    private void getDataFromNet() {
        RequestParams reques = new RequestParams(Constant.NET_AUDIO_URL);
        x.http().get(reques, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    CacheUtils.putString(mContext,Constant.NET_AUDIO_URL,result);
                    LogUtil.e("onSuccess==" + result);
                    processData(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtil.e("onError==" + ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtil.e("onCancelled==" + cex.getMessage());
                }

                @Override
                public void onFinished() {
                    LogUtil.e("onFinished==");
                }
        });
        //设置点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                NetAudioBean.ListBean listEntity = datas.get(position);
                if(listEntity !=null ){
                    //3.传递视频列表
                    Intent intent = new Intent(mContext,ShowImageAndGifActivity.class);
                    if(listEntity.getType().equals("gif")){
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url",url);
                        mContext.startActivity(intent);
                    }else if(listEntity.getType().equals("image")){
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url",url);
                        mContext.startActivity(intent);
                    }
                }


            }
        });
    }



    class ShowImageAndGifActivity extends AppCompatActivity {

        private String url;
        private ImageOptions imageOptions;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_image_and_gif);

            url = getIntent().getStringExtra("url");
            LogUtil.e(url);

           PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);

            final PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);

            imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(80))
                    //设置圆角
                    .setRadius(DensityUtil.dip2px(5))
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setLoadingDrawableId(R.drawable.video_default)
                    .setFailureDrawableId(R.drawable.video_default)
                    .build();
            x.image().bind(photoView, url, imageOptions, new Callback.CommonCallback<Drawable>() {
                @Override
                public void onSuccess(Drawable result) {
                    attacher.update();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });


        }
    }
}