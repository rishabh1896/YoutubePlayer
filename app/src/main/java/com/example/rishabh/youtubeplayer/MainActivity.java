package com.example.rishabh.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    YoutubeService youtubeService;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ((MyApp) getApplication()).getNetComponent().inject(this);
            setContentView(R.layout.activity_main);
            TextView textView = (TextView) findViewById(R.id.text_view1);
//
            Observable<YoutubeModel> youtubeData =youtubeService.getYoutubeFeeds(Config.YOUTUBE_API_KEY, Config.id, Config.fields, "snippet,statistics");
                   youtubeData.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<YoutubeModel>() {
                        @Override
                        public void onNext(YoutubeModel youtubeModel) {
                        List<Item> l=youtubeModel.getItems();
                            System.out.println("hii"+l.get(0).getId());
                           launchIntent(l);
                        }

                        @Override
                        public void onError(Throwable e) {
                           Log.d("Output",e.getMessage());
                        }

                        @Override
                        public void onCompleted() {

                        }
                    });


        }

    private void launchIntent(List<Item> l) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, Config.YOUTUBE_API_KEY, l.get(0).getId());
        startActivity(intent);
    }


}





