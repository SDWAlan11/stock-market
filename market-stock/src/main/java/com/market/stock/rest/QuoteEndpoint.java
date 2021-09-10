package com.market.stock.rest;

import com.market.stock.bsn.QuoteService;

import com.market.stock.data.entity.Quote;
import com.market.stock.rest.dto.Result;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/quotes")
@CrossOrigin
public class QuoteEndpoint {

    @Resource
    private QuoteService quoteService;

    @GetMapping(value = "/{id}")
    public Quote getStockById(@PathVariable("id") String id){
        return quoteService.getStockById(id);
    }

}
