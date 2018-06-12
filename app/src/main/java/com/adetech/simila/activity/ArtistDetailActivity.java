package com.adetech.simila.activity;

import android.support.v4.app.Fragment;

import com.adetech.simila.fragment.ArtistDetailFragment;

public class ArtistDetailActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return ArtistDetailFragment.newInstance();
    }
}
