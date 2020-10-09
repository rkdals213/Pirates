package com.example.pirates.model.dto;

import com.example.pirates.Handler.Handler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.*;

public interface StoreStatus {

    String getName();

    String getDescription();

    int getLevel();

    @JsonIgnore
    List<String> getHolidays();

    @JsonIgnore
    Set<BusinessTimes> getBusinessTimes();

    default String getBusinessStatus() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(date);

        List<String> holidays = getHolidays();

        if (holidays.contains(today)) return "HOLIDAY";
        else {
            format = new SimpleDateFormat("E", Locale.ENGLISH);
            today = format.format(date);
            System.out.println(today);
            Set<BusinessTimes> businessTimes = getBusinessTimes();
            for (BusinessTimes b : businessTimes) {
                if (!b.getDay().contains(today)) continue;
                StringTokenizer open = new StringTokenizer(b.getOpen(), ":");
                StringTokenizer close = new StringTokenizer(b.getClose(), ":");

                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                String now = format2.format(new Date());
                StringTokenizer now2 = new StringTokenizer(now,":");

                int openTime = Integer.parseInt(open.nextToken()) * 60 + Integer.parseInt(open.nextToken());
                int closeTime = Integer.parseInt(close.nextToken()) * 60 + Integer.parseInt(close.nextToken());
                int nowTime = Integer.parseInt(now2.nextToken()) * 60 + Integer.parseInt(now2.nextToken());
                if(nowTime >= openTime && nowTime <= closeTime) return "OPEN";
            }
        }

        return "CLOSE";
    }

}
