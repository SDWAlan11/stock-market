package com.market.stock.rest;

import com.market.stock.bsn.QuoteService;
import com.market.stock.bsn.StockService;
import com.market.stock.bsn.dto.StockDto;
import com.market.stock.data.entity.Orders;
import com.market.stock.data.entity.Quote;
import com.market.stock.data.entity.Symbol;
import com.market.stock.rest.dto.Statics;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/stocks")
@CrossOrigin
public class StockEndpoint {

    @Resource private StockService stockService;
    @Resource private QuoteService quoteService;

    @GetMapping()
    public List<StockDto> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping(value = "/{id}")
    public StockDto getStockById(@PathVariable("id") String id){
        return stockService.getStockById(id);
    }

    @GetMapping(value = "/{id}/quotes")
    public List<Quote> getQuotesByStock(@PathVariable("id") String id){

        return quoteService.getQuotesBySymbolId(id);
    }

    @GetMapping(value = "/{id}/orders")
    public List<Orders> getOrdersByStockId(@PathVariable("id") String id) {
        return stockService.getOrdersByStockId(id);
    }

    @GetMapping(value = "/{id}/statics")
    public Statics getLastPriceByStockId(@PathVariable("id") String id) {
        return stockService.getStaticsByStockId(id);
    }

    @PostMapping
    public void addStock(@RequestBody Symbol symbol){
        stockService.addStock(symbol);
    }
}
