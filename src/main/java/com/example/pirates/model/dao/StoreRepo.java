package com.example.pirates.model.dao;

import com.example.pirates.model.dto.Store;
import com.example.pirates.model.dto.StoreDetail;
import com.example.pirates.model.dto.StoreStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

    Optional<StoreDetail> findStoreDetailById(Long id);

    List<StoreStatus> findAllByOrderByLevelDesc();

}
