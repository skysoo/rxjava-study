package com.study.rxjava.chapter00;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ReactorSample {
    public static void main(String[] args) {

        Flux.just(News.create("Important news"),
                News.create("Some other news"),
                News.create("And news, news, news"))
                .subscribe(new Subscriber<News>() {

                    private static final int MAX_NEWS = 3;
                    private int newsReceived = 0;
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
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
