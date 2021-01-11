package com.study.rxjava.chapter03;

import lombok.Getter;

@Getter
public class Car {
    CarMaker carMaker;
    String carName;
    Integer carPrice;

    public Car(CarMaker carMaker, String carName, Integer carPrice) {
        this.carMaker = carMaker;
        this.carName = carName;
        this.carPrice = carPrice;
    }
}
