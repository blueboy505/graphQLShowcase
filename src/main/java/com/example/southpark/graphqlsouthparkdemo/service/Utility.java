package com.example.southpark.graphqlsouthparkdemo.service;

import java.util.ArrayList;
import java.util.Collection;

public class Utility {

    public static <T> Collection<T> makeCollection(Iterable<T> iter) {
        Collection<T> list = new ArrayList<T>();
        for (T item : iter) {
            list.add(item);
        }
        return list;
    }
}
