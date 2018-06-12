package com.adetech.simila.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adetech.simila.R;
import com.squareup.picasso.Picasso;

public class ArtistPicFragment extends Fragment
{
    private static final String ARG_PIC_URL = "artist_pic_url";
    private static final String ARG_ARTIST_NAME = "artist_name";


    public static ArtistPicFragment newInstance(String artistPicUrl, String artistName)
    {
        Bundle args = new Bundle();
        args.putString(ARG_PIC_URL, artistPicUrl);
        args.putString(ARG_ARTIST_NAME, artistName);


        ArtistPicFragment fragment = new ArtistPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_art_pic, container, false);
        String artistPicUrl = getArguments().getString(ARG_PIC_URL, "");
        String artistName = getArguments().getString(ARG_ARTIST_NAME, "");


        ImageView artistPictureImageView = view.findViewById(R.id.large_artist_picture);

        if (!TextUtils.isEmpty(artistPicUrl))
        {
            Picasso.with(getActivity()).load(artistPicUrl).placeholder(R.drawable.ic_launcher_background).into(artistPictureImageView);
        }

        return view;

    }
}
