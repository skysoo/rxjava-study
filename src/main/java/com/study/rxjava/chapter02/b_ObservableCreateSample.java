package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class b_ObservableCreateSample {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> observable =
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        String[] datas = {"Hello","Observable", "RxJava!"};
                        for (String data : datas) {
                            // 구독이 해제되면 종료
                            if (emitter.isDisposed())
                                return;

                            // 데이터 통지
                            emitter.onNext(data);
                        }
                        // 데이터 통지 완료를 알림
                        emitter.onComplete();
                    }
                });

        observable.observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // 배압 기능 없기 때문에 데이터 요청 개수 필요없다.
                    }

                    @Override
                    public void onNext(@NonNull String data) {
                        log.info(data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        log.error(String.valueOf(e));
                    }

                    @Override
                    public void onComplete() {
                        log.info("Complete!");
                    }
                });
        Thread.sleep(500L);
    }
}
