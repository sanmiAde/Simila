package com.adetech.simila.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.adetech.simila.R;
import com.adetech.simila.adapter.CustomAdapter;
import com.adetech.simila.model.Artist;
import com.adetech.simila.model.SimilarArtist;
import com.adetech.simila.network.GetDataService;
import com.adetech.simila.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment
{
    private final String TAG = "MainFragment";

    public static MainFragment newInstance()
    {
        return new MainFragment();
    }

    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    ProgressDialog mProgressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void performApiRequest(GetDataService service, String artistName)
    {
        Call<SimilarArtist> call = service.getAllSimilarArtist("artist.getsimilar",artistName,"f1206ed0cd61663480d26f89d76d622b","json" );

        call.enqueue(new Callback<SimilarArtist>()
        {
            @Override
            public void onResponse(Call<SimilarArtist> call, Response<SimilarArtist> response)
            {
                hideProgressDialog();
                Log.d(TAG,"onFailure Something wrong "+ response.code());
                List<Artist> artists;
                if (response.body() != null)
                {
                     artists  =response.body().getSimilarartists().getArtist();
                    generateDataList(artists);
                }

            }

            @Override
            public void onFailure(Call<SimilarArtist> call, Throwable t)
            {
                hideProgressDialog();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"onFailure Something wrong "+ t.getMessage());
            }
        });
    }

    private void hideProgressDialog()
    {
        mProgressDialog.dismiss();
    }

    private void showProgressDialog()
    {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    private void generateDataList(List<Artist> artistListList)
    {
        mRecyclerView = getActivity().findViewById(R.id.customRecyclerView);
        mAdapter = new CustomAdapter(getActivity(), artistListList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                Toast.makeText(getContext(), "Text submitted", Toast.LENGTH_SHORT).show();
                showProgressDialog();
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                performApiRequest(service, s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                Toast.makeText(getContext(), "Text changed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
