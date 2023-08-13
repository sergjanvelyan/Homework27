package com.example.homework27.repository;

import com.example.homework27.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository <Orders,Long> {
}
