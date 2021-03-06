/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kathline.horizontalrefreshlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {

    private final Context mContext;
    private final List<Integer> mItems;
    private int mCurrentItemId = 0;
    private int[] imageIds = {R.mipmap.card_cover1, R.mipmap.card_cover2, R.mipmap.card_cover3,
            R.mipmap.card_cover4, R.mipmap.card_cover5, R.mipmap.card_cover6,
            R.mipmap.card_cover7, R.mipmap.card_cover8};

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView myImage;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            myImage = (ImageView) view.findViewById(R.id.image);
        }
    }

    public LayoutAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    public void setItems(List<Integer> list) {
        mItems.clear();
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void addItems(List<Integer> list) {
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.title.setText("Index " + mItems.get(position));
        holder.myImage.setImageResource(imageIds[position % imageIds.length]);

        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
