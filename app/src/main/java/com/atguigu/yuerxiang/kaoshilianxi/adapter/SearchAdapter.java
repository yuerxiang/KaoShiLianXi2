package com.atguigu.yuerxiang.kaoshilianxi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.yuerxiang.kaoshilianxi.R;
import com.atguigu.yuerxiang.kaoshilianxi.bean.SearchBean;

import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/26 11:51
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class SearchAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<SearchBean.ItemsBean> datas;

    public SearchAdapter(Context context, List<SearchBean.ItemsBean> items) {
        this.mContext = context;
        this.datas = items;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_search, null);
            //传入的是convertView
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应数据
        SearchBean.ItemsBean itemsBean = datas.get(position);
        viewHolder.tvName.setText(itemsBean.getItemTitle());
        viewHolder.tvDesc.setText(itemsBean.getKeywords());
        viewHolder.tvDuration.setText(itemsBean.getDatecheck());

//        Glide.with(mContext).load(itemsBean.getItemImage().getImgUrl1()).into(viewHolder.ivIcon);
        x.image().bind(viewHolder.ivIcon,itemsBean.getItemImage().getImgUrl1());


        return convertView;
    }



   static class ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.fl)
        FrameLayout fl;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_duration)
        TextView tvDuration;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
