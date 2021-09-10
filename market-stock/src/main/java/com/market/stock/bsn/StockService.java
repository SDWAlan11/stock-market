package com.market.stock.bsn;

import com.market.stock.bsn.dto.StockDto;
import com.market.stock.data.entity.Orders;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.SymbolRepository;
import com.market.stock.rest.dto.Statics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Resource private SymbolRepository symbolRepository;
    @Resource private StaticsFactory staticsFactory;

    public StockDto getStockById(String id){
        Symbol symbol = symbolRepository.findById(id).orElseThrow( () -> new NoSuchElementException("Stock not found"));
        Statics statics = getStaticsByStockId(id);
        return StockDto.builder().symbol(symbol).statics(statics).build();
    }

    public List<StockDto> getStocks(){
        List<Symbol> allSymbols = symbolRepository.findAll();
        return allSymbols.parallelStream().map(
                stock ->
                        StockDto.builder()
                                .symbol(stock)
                                .statics(getStaticsByStockId(stock.getId()))
                                .build()
        ).collect(Collectors.toList());
    }

    public List<Orders> getOrdersByStockId(String StockId){
         Symbol symbol = symbolRepository.findById(StockId).orElseThrow( () -> new NoSuchElementException("Stock not found") );
        return symbol.getOrders();
    }

    public StockDto addStock(Symbol symbol){
        System.out.println(symbol);
        System.out.println(symbol.hashCode());
        Symbol symbolReturn = symbolRepository.save(symbol);
        return StockDto.builder().symbol(symbolReturn).build();
    }

    public Statics getStaticsByStockId(String stockId){
        try {
            double lastPrice = staticsFactory.getLastPrice(stockId);
            int quantity = staticsFactory.getQuantity(stockId);
            double ppp = quantity > 0 ? (staticsFactory.getAccumulatedPrice(stockId) / quantity) : 0;

            return Statics.builder()
                    .lastPrice(lastPrice)
                    .ppp(ppp)
                    .quantity(quantity)
                    .gain((quantity * lastPrice) - (quantity * ppp))
                    .build();
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException("Error in return statics for Stock: " + stockId, e);
        }
    }
}
