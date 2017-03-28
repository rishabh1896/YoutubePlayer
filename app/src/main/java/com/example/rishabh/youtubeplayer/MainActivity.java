package com.example.rishabh.youtubeplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            /*((MyApp) getApplication()).getNetComponent().inject(this);
            setContentView(R.layout.activity_main);
            TextView textView = (TextView) findViewById(R.id.text_view1);*/
            YoutubeService service = ServiceFactory.createRetrofitService(YoutubeService.class, YoutubeService.SERVICE_ENDPOINT);

            System.out.println("i'm here");
          /*  Observable<YoutubeModel> youtubeData=service.getYoutubeFeeds(Config.YOUTUBE_API_KEY, Config.id, Config.fields, "snippet,statistics");
           youtubeData.subscribeOn(Schedulers.newThread())
                   .observeOn(AndroidSchedulers.mainThread())
                   .map(data -> "ID:"+data.getItems().toString())
                   .subscribe(youtubeInfo-> Log.d("Output",youtubeInfo));

*/
            Observable<YoutubeModel> youtubeData =service.getYoutubeFeeds(Config.YOUTUBE_API_KEY, Config.id, Config.fields, "snippet,statistics");
                   youtubeData.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<YoutubeModel>() {
                        @Override
                        public void onNext(YoutubeModel youtubeModel) {
                        List<Item> l=youtubeModel.getItems();
                            System.out.println("hii"+l.get(0).getId());
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


            }





