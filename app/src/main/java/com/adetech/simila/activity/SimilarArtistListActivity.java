package com.adetech.simila.activity;

import android.support.v4.app.Fragment;

import com.adetech.simila.fragment.SimilarArtistListFragment;

public class SimilarArtistListActivity extends SingleFragmentActivity
{


    @Override
    protected Fragment createFragment()
    {
        return SimilarArtistListFragment.newInstance();
    }


}
