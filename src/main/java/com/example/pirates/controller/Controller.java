package com.example.pirates.controller;

import com.example.pirates.model.dao.StoreRepo;
import com.example.pirates.model.dto.*;
import com.example.pirates.model.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = { "*" })
@RestController
public class Controller {
    static Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    StoreService storeService;

    @PostMapping("/registStore")
    public Store registStore(@RequestBody Store store){
        logger.info("regist store");
        Store result = null;
        try {
            result = storeService.save(store);
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return result;
    }

    @GetMapping("/getStoreInfo")
    public StoreDetail getStoreInfo(@RequestBody Long id){
        logger.info("get one store info");
        StoreDetail result = null;

        try {
            result = storeService.findById(id).get();
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return result;
    }

    @DeleteMapping("/deleteStore")
    public Store deleteStore(@RequestBody Long id){
        logger.info("delete store");
        Store result = null;

        try {
            storeService.deleteById(id);
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return result;
    }

    @GetMapping("/getStores")
    public List<StoreStatus> getStores(){
        logger.info("get stores");
        List<StoreStatus> list = null;
        try {
            list = storeService.getList();
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return list;
    }

    @PostMapping("/registHoliday")
    public Store registHoliday(@RequestBody Store store){
        logger.info("regist holiday");

        Store result = null;
        try {
            result = storeService.registHoliday(store);
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return result;
    }
}
