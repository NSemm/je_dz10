package com.k7;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        TwoWayList<String> twoWayList = new TwoWayList<>();
        twoWayList.add("one");
        twoWayList.add("two");
        twoWayList.add("three");
        twoWayList.add("four");
        twoWayList.add("five");
        //twoWayList.remove(3);
        //twoWayList.set(1, "set from head");
        //twoWayList.setFromTail(1, "set from tail");
        System.out.println("-------List-------");
        for (String s : twoWayList) {
            System.out.println(s);
        }
        System.out.println("-------Revers list-------");
        Iterator<String> iterator = twoWayList.iteratorReverse();
        while (iterator.hasNext()) System.out.println(iterator.next());
    }
}