package com.example.pirates.controller;

import com.example.pirates.model.dto.*;
import com.example.pirates.model.dto.interfaces.StoreDetail;
import com.example.pirates.model.dto.interfaces.StoreStatus;
import com.example.pirates.model.service.StoreService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = { "*" })
@RestController
public class Controller {
    static Logger logger = LoggerFactory.getLogger(Controller.class);

    private StoreService storeService;

    @Autowired
    public Controller(StoreService storeService) {
        Assert.notNull(storeService, "storeService 개체가 반드시 필요!");
        this.storeService = storeService;
    }

    /**
    * 점포 추가 API
    * @param store
    * @return store
    */

    @PostMapping("/registStore")
    @ApiOperation(value = "점포 추가 API")
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

    /**
     * 점포 휴무일 등록 API
     * @param store
     * @return store
     */

    @PostMapping("/registHoliday")
    @ApiOperation(value = "점포 휴무일 등록 API")
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


    /**
     * 점포 목록 조회 API
     * @param
     * @return List<store>
     */

    @GetMapping("/getStores")
    @ApiOperation(value = "점포 목록 조회 API")
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

    /**
     * 점포 상세정보 조회 API
     * @param id
     * @return storeDetail
     */

    @GetMapping("/getStoreInfo")
    @ApiOperation(value = "점포 상세정보 조회 API")
    public StoreDetail getStoreInfo(@RequestParam("id") Long id){
        logger.info("get one store info");
        StoreDetail result = null;

        try {
            result = storeService.findById(id).get();
        }catch (RuntimeException e){
            logger.error(e.toString());
        }

        return result;
    }


    /**
     * 점포 삭제 API
     * @param id
     * @return int
     */

    @DeleteMapping("/deleteStore")
    @ApiOperation(value = "점포 삭제 API")
    public int deleteStore(@RequestParam("id") Long id){
        logger.info("delete store");

        try {
            storeService.deleteById(id);
        }catch (RuntimeException e){
            logger.error(e.toString());
            return 0;
        }

        return 1;
    }
}
