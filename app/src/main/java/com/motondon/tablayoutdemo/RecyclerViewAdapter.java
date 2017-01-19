package com.motondon.tablayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motondon.tablayoutdemo_part1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joca on 12/19/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private String[] values;
    private Context context;
    private View view1;
    private ViewHolder viewHolder1;

    public RecyclerViewAdapter(Context context1, String[] values){
        this.values = values;
        this.context = context1;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView1.setText(values[position]);
        holder.textView2.setText("This is a description for the item: " + values[position]);
    }

    @Override
    public int getItemCount(){
        return values.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text1) TextView textView1;
        @BindView(R.id.text2) TextView textView2;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}