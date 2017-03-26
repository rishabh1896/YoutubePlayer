package com.example.rishabh.youtubeplayer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rishabh on 26/3/17.
 */

public interface YoutubeService {
    String SERVICE_ENDPOINT = "https://www.googleapis.com";
    @GET("/youtube/v3/videos")
    Observable<YoutubeModel> getYoutubeFeeds(@Query("key") String developerKey, @Query("id") String id, @Query("fields") String fields, @Query("part") String part);
}
