package com.opt.contoller;


import com.opt.entity.Sale;
import com.opt.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SaleController {

    private static final Logger logger = LogManager.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;



    @GetMapping("/total-sales-today")
    public Double getTotalSalesOfCurrentDay() {
        logger.info("Received request to fetch total sales of current day");
        return saleService.getTotalSalesOfCurrentDay();
    }

    @GetMapping("/max-sale-day")
    public List<Object[]> getMaxSaleDay(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        logger.info("Received request to fetch max sale day between {} and {}", startDate, endDate);
        return saleService.getMaxSaleDay(startDate, endDate);
    }

    @GetMapping("/top-selling-items-all-time")
    public List<Object[]> getTopSellingItemsOfAllTime() {
        logger.info("Received request to fetch top selling items of all time");
        return saleService.getTopSellingItemsOfAllTime();
    }


}
