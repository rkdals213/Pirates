package com.example.pirates.model.dto;

import com.example.pirates.model.dao.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public interface BusinessTimesStatus {

    String getDay();

    String getOpen();

    String getClose();


    default String getStatus() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(date);

        List<String> holidays = getHolidays();

        if (holidays.contains(today)) return "HOLIDAY";
        else {
            format = new SimpleDateFormat("E");
            today = format.format(date);

            if(!getDay().contains(today)) return "CLOSE";

            StringTokenizer open = new StringTokenizer(getOpen());
            StringTokenizer close = new StringTokenizer(getClose());

            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
            String now = format2.format(new Date());
            StringTokenizer now2 = new StringTokenizer(now);

            int openTime = Integer.parseInt(open.nextToken()) * 60 + Integer.parseInt(open.nextToken());
            int closeTime = Integer.parseInt(close.nextToken()) * 60 + Integer.parseInt(close.nextToken());
            int nowTime = Integer.parseInt(now2.nextToken()) * 60 + Integer.parseInt(now2.nextToken());
            if (nowTime >= openTime && nowTime <= closeTime) return "OPEN";

        }

        return "CLOSE";
    }

    List<String> getHolidays();

}
