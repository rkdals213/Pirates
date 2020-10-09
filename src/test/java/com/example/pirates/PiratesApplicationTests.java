package com.example.pirates;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.example.pirates.model.dao.StoreRepo;
import com.example.pirates.model.dto.BusinessTimes;
import com.example.pirates.model.dto.Store;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

@DataJpaTest
@RunWith(SpringRunner.class)
class PiratesApplicationTests {


    @Autowired
    StoreRepo storeRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void contextLoads() {
//        Store store = new Store();
//        store.setName("인어상점");
//        store.setAddress("서울");
//
//        BusinessTimes businessTimes = new BusinessTimes();
//        businessTimes.setDay("목요일");
//
//        store.getBusinessTimes().add(businessTimes);
//        businessTimes.setStore(store);
//
//
//        Session session = entityManager.unwrap(Session.class);
//        session.save(store);
//        session.save(businessTimes);

//        storeRepo.save(store);
//        System.out.println(storeRepo.findAll());


    }

    @Test
    void contextLoads2() {
        String open = "09:00";
        String close = "24:00";

        SimpleDateFormat format;
        format = new SimpleDateFormat("HH:mm");
        Date today = new Date();
        System.out.println(today);
        String result = format.format(today);
        System.out.println(result);

        System.out.println(check(open, close, result));
    }

    boolean check(String open, String close, String now) {
        StringTokenizer open1 = new StringTokenizer(open, ":");
        int openTime = Integer.parseInt(open1.nextToken()) * 60 + Integer.parseInt(open1.nextToken());

        StringTokenizer close1 = new StringTokenizer(close, ":");
        int closeTime = Integer.parseInt(close1.nextToken()) * 60 + Integer.parseInt(close1.nextToken());

        StringTokenizer now1 = new StringTokenizer(now, ":");
        int nowTime = Integer.parseInt(now1.nextToken()) * 60 + Integer.parseInt(now1.nextToken());

        return nowTime >= openTime && nowTime <= closeTime;
    }

    @Test
    void qwfasf() {
        String[] daysFromToday = new String[3];
        String[] daysOfWeekFromToday = new String[3];
        String[] dayOfWeek = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        List<String> holidays = new ArrayList<>();
        holidays.add("2020-10-10");
        holidays.add("2020-10-11");

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);

        for (int i = 0; i < 3; i++) {
            int year = calendar.get(calendar.YEAR);
            int month = calendar.get(calendar.MONTH) + 1;
            int day = calendar.get(calendar.DATE) + i;


            String date = "";
            if (day < 10) date = year + "-" + month + "-0" + day;
            else date = year + "-" + month + "-" + day;

            System.out.println(date + holidays.contains(date));

        }

    }

}
