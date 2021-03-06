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
        stocksDto.parallelStream().forEach(this::afterReturningAdvice);
    }

    @AfterReturning(pointcut="execution(* com.market.stock.bsn.StockService.*Stock*(..))", returning="stockDto")
    public void afterReturningAdvice(StockDto stockDto){
        Quote quote = quoteService.getStockById(stockDto.getSymbol().getId());
        System.out.println("Message to send " + quote);
        kafkaProducer.sendMessage(quote);
    }

    @AfterThrowing(pointcut="execution(* com.market.stock.bsn.StockService.*Stock*(..))", throwing="ex")
    public void afterThrowingAdvice(Exception ex){
        System.out.println("HOLI");
        ex.printStackTrace();
    }
}
