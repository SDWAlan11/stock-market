package com.market.stock.rest;

import com.market.stock.bsn.StockService;
import com.market.stock.bsn.dto.StockDto;
import com.market.stock.data.entity.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.annotation.Resource;

@WebMvcTest(controllers = StockEndpoint.class)
public class SymbolEndpointTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(stockService.getStockById(anyString())).thenReturn(
                StockDto.builder().symbol(Symbol.builder().id("123456").build()).build());

        String url = "/v1/stocks/123456";


        this.mockMvc.perform(get(url))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value("123456"));
    }
}