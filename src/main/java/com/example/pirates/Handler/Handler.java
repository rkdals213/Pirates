package com.example.pirates.Handler;

import java.util.StringTokenizer;

public class Handler {

    public boolean check(String open, String close, String now){
        StringTokenizer open1 = new StringTokenizer(open, ":");
        int openTime = Integer.parseInt(open1.nextToken()) * 60 + Integer.parseInt(open1.nextToken());

        StringTokenizer close1 = new StringTokenizer(close, ":");
        int closeTime = Integer.parseInt(close1.nextToken()) * 60 + Integer.parseInt(close1.nextToken());

        StringTokenizer now1 = new StringTokenizer(now, ":");
        int nowTime = Integer.parseInt(now1.nextToken()) * 60 + Integer.parseInt(now1.nextToken());

        return nowTime >= openTime && nowTime <= closeTime;
    }
}
