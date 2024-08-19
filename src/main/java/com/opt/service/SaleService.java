package com.opt.service;

import com.opt.repository.SaleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SaleService {

    private static final Logger logger = LogManager.getLogger(SaleService.class);

    @Autowired
    private SaleRepository saleRepository;



    public Double getTotalSalesOfCurrentDay() {
        logger.debug("Fetching total sales of the current day");
        return saleRepository.findTotalSalesOfCurrentDay();
    }

    public List<Object[]> getMaxSaleDay(Date startDate, Date endDate) {
        logger.debug("Fetching max sale day between {} and {}", startDate, endDate);
        return saleRepository.findMaxSaleDay(startDate, endDate);
    }

    public List<Object[]> getTopSellingItemsOfAllTime() {
        logger.debug("Fetching top selling items of all time");
        Pageable pageable = PageRequest.of(0, 5);
        return saleRepository.findTopSellingItemsOfAllTime(pageable);
    }


}

