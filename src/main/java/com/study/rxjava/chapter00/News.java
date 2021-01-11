package com.study.rxjava.chapter00;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class News {

    private final String headline;
    private final LocalDate date;

    public static News create(String headline) {
        return new News(headline, LocalDate.now());
    }

    public News(String headline, LocalDate date) {
        this.headline = headline;
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public LocalDate getDate() {
        return date;
    }


    // getter, setter, constructor omitted
}