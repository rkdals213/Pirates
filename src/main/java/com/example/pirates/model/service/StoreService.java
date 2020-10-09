package com.example.pirates.model.service;

import com.example.pirates.model.dao.StoreRepo;
import com.example.pirates.model.dto.Store;
import com.example.pirates.model.dto.StoreDetail;
import com.example.pirates.model.dto.StoreStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreService {

    @Autowired
    StoreRepo storeRepo;

    public Store save(Store store) {
        return storeRepo.save(store);
    }

    public List<StoreStatus> getList() {
//
        return storeRepo.findAllByOrderByLevelDesc();
    }

    public Optional<StoreDetail> findById(Long id) {
        return storeRepo.findStoreDetailById(id);
    }

    public void deleteById(Long id) {
        storeRepo.deleteById(id);
    }

    public Store registHoliday(Store store) {
        Store temp = storeRepo.findById(store.getId()).get();

        List<String> list = temp.getHolidays();
        for (String t : store.getHolidays()) {
            if(!list.contains(t)) list.add(t);
        }
        temp.setHolidays(list);
        return temp;
    }
}
