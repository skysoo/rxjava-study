package com.study.rxjava.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Searcher {
    public List<String> search(String keyword) {
        List<String> dataList = Arrays.asList("Macau", "Malaysia", "Maldives", "Mexico", "Myanmar",
                "Macedonia");
        List<String> resultList = new ArrayList<>();

        for (String s : dataList) {
            if (s.startsWith(keyword))
                resultList.add(s);
        }
        return resultList;
    }
}
