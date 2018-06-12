package com.adetech.simila.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.adetech.simila.fragment.ArtistDetailFragment;
import com.adetech.simila.fragment.ArtistPicFragment;

public class ArtistPictureActivity extends SingleFragmentActivity
{
    private static final String EXTRA_ARTIST_URL = "com.adetech.simila.extra_artist_url";
    private static final String EXTRA_ARTIST_NAME = "com.adetech.simila.extra_artist_name";

    public static Intent newIntent(Context packageContext, String url, String artistName)
    {
        Intent intent = new Intent(packageContext, ArtistPictureActivity.class);
        intent.putExtra(EXTRA_ARTIST_URL, url);
        intent.putExtra(EXTRA_ARTIST_NAME, artistName);

        return intent;
    }

    @Override
    protected Fragment createFragment()
    {
        String artistUrl = getIntent().getStringExtra(EXTRA_ARTIST_URL);
        String artistName = getIntent().getStringExtra(EXTRA_ARTIST_NAME);

        return ArtistPicFragment.newInstance(artistUrl, artistName);
    }
}
