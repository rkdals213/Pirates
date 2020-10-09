package com.example.pirates.model.service;

import com.example.pirates.model.dao.StoreRepo;
import com.example.pirates.model.dto.Store;
import com.example.pirates.model.dto.interfaces.StoreDetail;
import com.example.pirates.model.dto.interfaces.StoreStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreService {

    private StoreRepo storeRepo;

    @Autowired
    public StoreService(StoreRepo storeRepo) {
        Assert.notNull(storeRepo, "storeRepo 개체가 반드시 필요!");
        this.storeRepo = storeRepo;
    }

    public Store save(Store store) {
        return storeRepo.save(store);
    }

    public List<StoreStatus> getList() {
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
