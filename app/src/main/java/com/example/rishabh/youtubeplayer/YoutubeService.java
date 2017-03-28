package com.example.rishabh.youtubeplayer;

import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rishabh on 26/3/17.
 */

public interface YoutubeService {
    String SERVICE_ENDPOINT = "https://www.googleapis.com/youtube/v3/";
    @GET("videos?")
    Observable<YoutubeModel> getYoutubeFeeds(@Query("key") String developerKey, @Query("id") String id, @Query("fields") String fields, @Query("part") String part);
}
