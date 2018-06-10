package com.adetech.simila.network;

import com.adetech.simila.model.Artist;
import com.adetech.simila.model.SimilarArtist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService
{
    @GET("?method=artist.getsimilar&artist=birdy&api_key=f1206ed0cd61663480d26f89d76d622b&format=json")
    Call<SimilarArtist> getALlSimilarArtist();
}
