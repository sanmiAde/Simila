package com.adetech.simila.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adetech.simila.R;
import com.adetech.simila.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity
{


    @Override
    protected Fragment createFragment()
    {
        return MainFragment.newInstance();
    }
}
