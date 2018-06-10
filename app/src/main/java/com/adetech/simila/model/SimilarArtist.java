package com.adetech.simila.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarArtist
{
    @SerializedName("similarartists")
    @Expose
    private ArtistList similarartists;

    public ArtistList getSimilarartists()
    {
        return similarartists;
    }

    public void setSimilarartists(ArtistList similarartists)
    {
        this.similarartists = similarartists;
    }



}
