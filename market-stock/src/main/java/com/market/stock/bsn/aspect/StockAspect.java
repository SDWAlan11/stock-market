package com.market.stock.bsn.aspect;

import com.market.stock.bsn.KafkaProducer;
import com.market.stock.bsn.QuoteService;
import com.market.stock.bsn.dto.StockDto;
import com.market.stock.data.entity.Quote;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Aspect
@Component
public class StockAspect {

    @Resource
    private QuoteService quoteService;

    @Resource
    private KafkaProducer kafkaProducer;

    @AfterReturning(pointcut="execution(* com.market.stock.bsn.StockService.getStock*(..))", returning="stocksDto")
    public void afterReturningAdvice(List<StockDto> stocksDto){
        stocksDto.parallelStream().forEach(stockDto -> afterReturningAdvice(stockDto));
    }

    @AfterReturning(pointcut="execution(* com.market.stock.bsn.StockService.*Stock*(..))", returning="stockDto")
    public void afterReturningAdvice(StockDto stockDto){
        Quote quoteCreated = quoteService.addQuoteBySymbolId(stockDto.getSymbol().getId());
        kafkaProducer.sendMessage(quoteCreated);
    }

    @AfterThrowing(pointcut="execution(* com.market.stock.bsn.StockService.*Stock*(..))", throwing="ex")
    public void afterThrowingAdvice(Exception ex){
        System.out.println("HOLI");
        ex.printStackTrace();
    }
}
