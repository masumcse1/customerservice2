package com.opt.service;


import com.opt.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class SaleServiceTest {

    @InjectMocks
    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetTotalSalesOfCurrentDay() {
        when(saleRepository.findTotalSalesOfCurrentDay()).thenReturn(100.0);

        Double totalSales = saleService.getTotalSalesOfCurrentDay();
        assertEquals(100.0, totalSales);
    }

    @Test
    public void testGetMaxSaleDay() {
        Date startDate = new Date();
        Date endDate = new Date();
        Object[] maxSaleDayArray = {"2024-07-14", 200.0};
        List<Object[]> maxSaleDay = new ArrayList<>();
        maxSaleDay.add(maxSaleDayArray);

        when(saleRepository.findMaxSaleDay(startDate, endDate)).thenReturn(maxSaleDay);

        List<Object[]> result = saleService.getMaxSaleDay(startDate, endDate);
        assertEquals(maxSaleDay, result);
    }

    @Test
    public void testGetTopSellingItemsOfAllTime() {
        Date startDate = new Date();
        Date endDate = new Date();
        Object[] maxSaleDayArray = {"2024-07-14", 200.0};
        List<Object[]> topSellingItems = new ArrayList<>();
        topSellingItems.add(maxSaleDayArray);

        //List<Object[]> topSellingItems = List.of(new Object[]{"Item1", 1000.0}, new Object[]{"Item2", 800.0});
        Pageable pageable = PageRequest.of(0, 5);
        when(saleRepository.findTopSellingItemsOfAllTime(pageable)).thenReturn(topSellingItems);

        List<Object[]> result = saleService.getTopSellingItemsOfAllTime();
        assertEquals(topSellingItems, result);
    }


}
