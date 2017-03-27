package com.example.rishabh.youtubeplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            YoutubeService service = ServiceFactory.createRetrofitService(YoutubeService.class, YoutubeService.SERVICE_ENDPOINT);

            System.out.println("i'm here");
           /* Observable<YoutubeModel> youtubeData=service.getYoutubeFeeds(Config.YOUTUBE_API_KEY, Config.id, Config.fields, "snippet,statistics");
           youtubeData.subscribeOn(Schedulers.newThread())
                   .observeOn(AndroidSchedulers.mainThread())
                   .map(data -> "ID:"+data.getItems().toString())
                   .subscribe(youtubeInfo-> Log.d("Output",youtubeInfo));
*/

            service.getYoutubeFeeds(Config.YOUTUBE_API_KEY, Config.id, Config.fields, "snippet,statistics")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<YoutubeModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(YoutubeModel youtubeModel) {
                        List<Item> l=youtubeModel.getItems();
                            System.out.println(l);
                        }

                        @Override
                        public void onError(Throwable e) {
                           Log.d("Output",e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        }


            }





