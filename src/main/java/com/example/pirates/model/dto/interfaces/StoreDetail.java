package com.example.pirates.model.dto.interfaces;

import com.example.pirates.model.dto.BusinessTimes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.*;

public interface StoreDetail {
    String getName();

    String getOwner();

    String getDescription();

    String getLevel();

    String getAddress();

    String getPhone();

    @JsonIgnore
    List<String> getHolidays();

    @JsonIgnore
    List<BusinessTimes> getBusinessTimes();

    default List<Object> getBusinessDays() {
        List<BusinessTimes> businessTimes = getBusinessTimes();
        List<Object> result = new ArrayList<>();
        List<String> holidays = getHolidays();

        String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Calendar calendar = Calendar.getInstance();

        int cnt = 0;
        int cnt2 = 0;

        while (cnt < 3) {
            Map<String, String> map = new HashMap<>();
            int year = calendar.get(calendar.YEAR);
            int month = calendar.get(calendar.MONTH) + 1;
            int day = calendar.get(calendar.DATE) + cnt2;
            int dow = (calendar.get(calendar.DAY_OF_WEEK) + cnt2 - 1) % 7;
            System.out.println(dayOfWeek[dow]);

            if (year % 4 == 0 && year % 100 != 0 && year % 400 == 0) daysOfMonth[1] = 29;

            if (day > daysOfMonth[month]) {
                day = day - daysOfMonth[month];
                month++;
            }
            if (month > 12) {
                month = month - 12;
                year++;
            }


            String date = "";
            if (day < 10) date = year + "-" + month + "-0" + day;
            else date = year + "-" + month + "-" + day;

            for (int j = 0; j < businessTimes.size(); j++) {
                BusinessTimes bs = businessTimes.get(j);
                if (bs.getDay().equals(dayOfWeek[dow])) {
                    if (holidays.contains(date)) {
                        map.put("day", bs.getDay());
                        map.put("open", bs.getOpen());
                        map.put("close", bs.getClose());
                        map.put("status", "HOLIDAY");
                        result.add(map);
                        cnt++;
                        break;
                    } else {
                        StringTokenizer open = new StringTokenizer(bs.getOpen(), ":");
                        StringTokenizer close = new StringTokenizer(bs.getClose(), ":");

                        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                        String now = format2.format(new Date());
                        StringTokenizer now2 = new StringTokenizer(now, ":");

                        int openTime = Integer.parseInt(open.nextToken()) * 60 + Integer.parseInt(open.nextToken());
                        int closeTime = Integer.parseInt(close.nextToken()) * 60 + Integer.parseInt(close.nextToken());
                        int nowTime = Integer.parseInt(now2.nextToken()) * 60 + Integer.parseInt(now2.nextToken());

                        SimpleDateFormat format = new SimpleDateFormat("E", Locale.ENGLISH);
                        String todayofweek = format.format(new Date());
                        System.out.println(todayofweek + "================");
                        if (nowTime >= openTime && nowTime <= closeTime && bs.getDay().contains(todayofweek))
                            map.put("status", "OPEN");
                        else map.put("status", "CLOSE");
                        map.put("day", bs.getDay());
                        map.put("open", bs.getOpen());
                        map.put("close", bs.getClose());
                        result.add(map);
                        cnt++;
                        break;
                    }
                }
            }
            cnt2++;
        }

        return result;
    }
}
