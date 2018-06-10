package com.adetech.simila.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adetech.simila.R;
import com.adetech.simila.model.Artist;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
{

    private List<Artist> mDataList;
    private Context mContext;

    public CustomAdapter(Context context, List<Artist> dataList)
    {
        mContext = context;
        mDataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        return  new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
            holder.artistNameTxtView.setText(mDataList.get(position).getName());
            holder.mArtistMatchRate.setText(mDataList.get(position).getMatch());
    }

    @Override
    public int getItemCount()
    {
        return mDataList.size();
    }


     class CustomViewHolder extends  RecyclerView.ViewHolder
    {
        public final  View mView;

        TextView artistNameTxtView;
        TextView mArtistMatchRate;

        public CustomViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;

            artistNameTxtView = mView.findViewById(R.id.title);
            mArtistMatchRate = mView.findViewById(R.id.match);
        }


    }
}
