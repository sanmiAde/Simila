package com.adetech.simila.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adetech.simila.R;
import com.adetech.simila.activity.ArtistDetailActivity;
import com.adetech.simila.activity.ArtistPictureActivity;
import com.adetech.simila.model.ArtistList.Artist;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarArtistListAdapter extends RecyclerView.Adapter<SimilarArtistListAdapter.CustomViewHolder>
{

    private static final String DIALOG_DATE = "DialogDate";
    private List<Artist> mDataList;
    private Context mContext;

    public SimilarArtistListAdapter(Context context, List<Artist> dataList)
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

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position)
    {
        //TODO fix this  use template..
        //ToDo app crashes whn you search genre.
        holder.artistNameTxtView.setText(mDataList.get(position).getName());
        holder.mArtistMatchRate.setText("Match Rate: " + " " + mDataList.get(position).getMatch());

        String artistImagePath = (mDataList.get(position).getImage().get(2).getText());
        if (!TextUtils.isEmpty(artistImagePath))
        {
            Picasso.with(mContext).load(artistImagePath).placeholder(R.drawable.ic_launcher_background).into(holder.mArtistImgView);
        }


        holder.mArtistImgView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = ArtistPictureActivity.newIntent(mContext, mDataList.get(position).getImage().get(5).getText(), mDataList.get(position).getName());
                mContext.startActivity(intent);

            }
        });

        holder.artistNameTxtView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = ArtistDetailActivity.newIntent(mContext, mDataList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mDataList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;


        TextView artistNameTxtView;
        TextView mArtistMatchRate;
        ImageView mArtistImgView;

        public CustomViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;

            artistNameTxtView = mView.findViewById(R.id.title);
            mArtistMatchRate = mView.findViewById(R.id.match);
            mArtistImgView = mView.findViewById(R.id.artistImageView);


        }


    }
}
