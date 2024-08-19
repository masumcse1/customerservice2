package com.opt.contoller;


import com.opt.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SaleControllerTest {

    @InjectMocks
    private SaleController saleController;

    @Mock
    private SaleService saleService;

    private MockMvc mockMvc;

    private List<Object[]> topSellingItemsAllTime;
    private List<Object[]> topSellingItemsLastMonth;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(saleController).build();


        topSellingItemsAllTime = Arrays.asList(
                new Object[]{"Item1", 1000.0},
                new Object[]{"Item2", 800.0}
        );

        topSellingItemsLastMonth = Arrays.asList(
                new Object[]{"Item1", 10L},
                new Object[]{"Item2", 8L}
        );
    }


    @Test
    public void testGetTotalSalesOfCurrentDay() throws Exception {
        when(saleService.getTotalSalesOfCurrentDay()).thenReturn(100.0);

        mockMvc.perform(get("/api/total-sales-today")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    public void testGetTopSellingItems() throws Exception {
        mockMvc.perform(get("/api/top-selling-items-all-time")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTopSellingItemsOfAllTime() throws Exception {
        when(saleService.getTopSellingItemsOfAllTime()).thenReturn(topSellingItemsAllTime);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/top-selling-items-all-time"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(topSellingItemsAllTime.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][0]").value("Item1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][1]").value(1000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1][0]").value("Item2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1][1]").value(800.0));
    }




}
