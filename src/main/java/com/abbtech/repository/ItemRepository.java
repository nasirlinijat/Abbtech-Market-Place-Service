package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    Page<Item> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);
}
