package com.abbtech.repository;

import com.abbtech.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findByUser_Id(Long userId);
}
