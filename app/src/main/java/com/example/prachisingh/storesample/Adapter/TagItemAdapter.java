package com.example.prachisingh.storesample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prachisingh.storesample.Activity.ProductListActivity;
import com.example.prachisingh.storesample.Activity.TagActivity;
import com.example.prachisingh.storesample.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.prachisingh.storesample.StringConstants.ACCESSTOKEN;
import static com.example.prachisingh.storesample.StringConstants.PAGENUMBER;
import static com.example.prachisingh.storesample.StringConstants.PRODUCTIDARRAY;
import static com.example.prachisingh.storesample.StringConstants.SELECTEDTAG;
import static com.example.prachisingh.storesample.StringConstants.PRODUCTIDARRAY;

/**
 * Created by prachisingh on 23/09/18.
 */

public class TagItemAdapter extends RecyclerView.Adapter<TagItemAdapter.TagViewHolder> {
    Context mContext;
    ArrayList<String> mTags;
    HashMap<String,ArrayList<Long>> vMap;
    int mPageNumber;
    String mAcessToken;

    public TagItemAdapter(Context context, ArrayList<String> tags, HashMap<String,ArrayList<Long>> map,int pagenumber,String acessToken){
        mTags=tags;
        mContext=context;
        vMap=map;
        mPageNumber=pagenumber;
        mAcessToken=acessToken;
    }
    @Override
    public TagItemAdapter.TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_list_item, parent, false);
        return new TagItemAdapter.TagViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final TagItemAdapter.TagViewHolder holder, final int position) {
        holder.tagName.setText(mTags.get(position));
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(SELECTEDTAG,mTags.get(position));
                intent.putExtra(PRODUCTIDARRAY,vMap.get(mTags.get(position)));
                intent.putExtra(PAGENUMBER,mPageNumber);
                intent.putExtra(ACCESSTOKEN,mAcessToken);
                intent.setClass(mContext,ProductListActivity.class);
                mContext.startActivity(intent);

            }
        };
        holder.tagName.setOnClickListener(listener);


    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tag_name)
        TextView tagName;
        public TagViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
