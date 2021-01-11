package com.study.rxjava.chapter00;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;

public class RxJavaSample {
    public static void main(String[] args) {

        Flowable.just(News.create("Important news"),
                News.create("Some other news"),
                News.create("And news, news, news"))
                .subscribe(new FlowableSubscriber<>() {

                    private org.reactivestreams.Subscription subscription;
                    private static final int MAX_NEWS = 3;
                    private int newsReceived = 0;

                    @Override
                    public void onSubscribe(org.reactivestreams.Subscription subscription) {
                        System.out.printf("new subscription %s\n", subscription);
                        this.subscription = subscription;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(News news) {
                        System.out.printf("news received: %s (%s)\n", news.getHeadline(), news.getDate());
                        newsReceived++;
                        if (newsReceived >= MAX_NEWS) {
                            System.out.printf("%d news received (max: %d), cancelling subscription\n", newsReceived,
                                    MAX_NEWS);
                            subscription.cancel();
                            return;
                        }

                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.err.printf("error occurred fetching news: %s\n", throwable.getMessage());
                        throwable.printStackTrace(System.err);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("fetching news completed");
                    }
                });
    }
}
