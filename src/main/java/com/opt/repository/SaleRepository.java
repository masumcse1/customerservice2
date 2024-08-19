package com.opt.repository;


import com.opt.entity.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM(s.totalSaleAmount) FROM Sale s WHERE s.saleDate = CURRENT_DATE")
    Double findTotalSalesOfCurrentDay();

    @Query("SELECT s.saleDate, SUM(s.totalSaleAmount) as total FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate GROUP BY s.date ORDER BY total DESC")
    List<Object[]> findMaxSaleDay(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT s.route, SUM(s.totalSaleAmount) as total FROM Sale s GROUP BY s.route ORDER BY total DESC")
    List<Object[]> findTopSellingItemsOfAllTime(Pageable pageable);



}
