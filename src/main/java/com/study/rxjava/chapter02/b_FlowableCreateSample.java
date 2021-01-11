package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class b_FlowableCreateSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable =
                Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {
                        String[] datas = {"Hello","Flowable","RxJava!"};
                        for (String data : datas) {
                            // 구독이 해제되면 종료
                            if (emitter.isCancelled())
                                return;

                            // 데이터 통지
                            emitter.onNext(data);
                        }
                        // 데이터 통지 완료를 알림
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER); // 소비자 처리가 늦을 경우 데이터를 버퍼에 저장

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        this.subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String data) {
                        log.info(data);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.error(String.valueOf(throwable));
                    }

                    @Override
                    public void onComplete() {
                        log.info("Complete!");
                    }
                });
        Thread.sleep(500L);
    }
}
