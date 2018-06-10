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
import com.adetech.simila.activity.MainActivity;
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

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading...");
        progressDoalog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<SimilarArtist> call = service.getALlSimilarArtist();


        call.enqueue(new Callback<SimilarArtist>()
        {
            @Override
            public void onResponse(Call<SimilarArtist> call, Response<SimilarArtist> response)
            {
                progressDoalog.dismiss();
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
                progressDoalog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"onFailure Something wrong "+ t.getMessage());
            }
        });

    }

    private void generateDataList(List<Artist> artistListList)
    {
        recyclerView = getActivity().findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(getActivity(), artistListList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
                Toast.makeText(getContext(), "Text inputed", Toast.LENGTH_SHORT).show();
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
