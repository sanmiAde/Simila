package com.adetech.simila.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adetech.simila.R;
import com.adetech.simila.adapter.SimilarArtistListAdapter;
import com.adetech.simila.model.Artist;
import com.adetech.simila.model.SimilarArtist;
import com.adetech.simila.network.GetDataService;
import com.adetech.simila.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SimilarArtistListFragment extends Fragment
{
    private final String TAG = "SimilarArtistListFragment";
    ProgressDialog mProgressDialog;
    private SimilarArtistListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mSearchedArtist = "";

    public static SimilarArtistListFragment newInstance()
    {
        return new SimilarArtistListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = view.findViewById(R.id.ArtistRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void performApiRequest(GetDataService service, String artistName)
    {
        Call<SimilarArtist> call = service.getAllSimilarArtist("artist.getsimilar", artistName, "f1206ed0cd61663480d26f89d76d622b", "json");

        call.enqueue(new Callback<SimilarArtist>()
        {
            @Override
            public void onResponse(Call<SimilarArtist> call, Response<SimilarArtist> response)
            {
                hideProgressDialog();

                List<Artist> artists;
                if (response.code() == 200)
                {
                    if (response.body().getSimilarartists() != null)
                    {
                        artists = response.body().getSimilarartists().getArtist();
                        generateDataList(artists);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Artist not found", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<SimilarArtist> call, Throwable t)
            {
                hideProgressDialog();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

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

    /**
     * Setup recycler view with data obtained from last.fm API.
     *
     * @param artistListList Arraylist obtained from last.fm API
     */
    private void generateDataList(List<Artist> artistListList)
    {
        mAdapter = new SimilarArtistListAdapter(getActivity(), artistListList);
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
                if (!mSearchedArtist.equals(s))
                {
                    Toast.makeText(getContext(), "Text submitted", Toast.LENGTH_SHORT).show();
                    searchView.clearFocus();
                    mSearchedArtist = s;
                    showProgressDialog();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    performApiRequest(service, s);
                }

                    return true;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
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
