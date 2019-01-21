package com.pp.server.util;


import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RxUtil {

    private static Logger logger = LoggerFactory.getLogger(RxUtil.class);

    public static void testFilter()
    {

    }

    public static void testConcatMap()
    {

    }

    public static void testFlatMap()
    {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<String>();
                for (int i = 0; i< 3; i++)
                {
                    //list.add(i+" comb "+ integer);
                    //System.out.println("load proccess" + i+" comb "+ integer);
                    StringBuilder builder = new StringBuilder();
                    builder.append(i);
                    builder.append("comb");
                    builder.append(integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static void testMap()
    {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This map operation "+ integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //System.out.println("accept "+ s);
                logger.info("accept "+ s);
            }
        });
    }
    public static void main(String[] args)
    {
        RxUtil.testMap();
    }
}
