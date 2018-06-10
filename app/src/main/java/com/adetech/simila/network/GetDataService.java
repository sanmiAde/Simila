package com.adetech.simila.network;

import com.adetech.simila.model.Artist;
import com.adetech.simila.model.SimilarArtist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService
{
//    @GET("?method=artist.getsimilar&artist=birdy&api_key=f1206ed0cd61663480d26f89d76d622b&format=json")
//    Call<SimilarArtist> getALlSimilarArtist();

    @GET("/2.0/")
    Call<SimilarArtist> getAllSimilarArtist(
            @Query("method") String methodCall,
            @Query("artist") String artistName,
            @Query("api_key") String api_key,
            @Query("format") String json);

}
