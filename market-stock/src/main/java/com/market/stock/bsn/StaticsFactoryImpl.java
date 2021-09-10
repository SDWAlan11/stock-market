package com.market.stock.bsn;

import com.market.stock.data.entity.Quote;
import com.market.stock.data.entity.Orders;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.QuoteRepository;
import com.market.stock.data.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class StaticsFactoryImpl implements StaticsFactory {

    @Resource
    private QuoteRepository quoteRepository;
    @Resource private OrderRepository orderRepository;

    @Override
    public double getLastPrice(String stockId) throws ExecutionException, InterruptedException {
        Symbol symbolToFind = Symbol.builder().id(stockId).build();
        Future<Optional<Quote>> lastPriceFuture = quoteRepository.findTopBySymbolOrderByCreatedOnDesc(symbolToFind);
        Quote quote = lastPriceFuture.get().orElse(Quote.builder().build());
        return quote.getRegularMarketPrice();
    }

    @Override
    public double getAccumulatedPrice(String stockId) throws ExecutionException, InterruptedException {
        Symbol symbolToFind = Symbol.builder().id(stockId).build();
        Future<List<Orders>> ordersFuture = orderRepository.findAllBySymbol(symbolToFind);
        List<Orders> orders = ordersFuture.get();
        if (orders.isEmpty())
            return 0;
        else
            return orders.stream().mapToDouble( order -> order.getPrice() * order.getQuantity()).sum();
    }

    @Override
    public int getQuantity(String stockId) throws ExecutionException, InterruptedException {
        Symbol symbolToFind = Symbol.builder().id(stockId).build();
        Future<List<Orders>> ordersFuture = orderRepository.findAllBySymbol(symbolToFind);
        List<Orders> orders = ordersFuture.get();
        if (orders.isEmpty())
            return 0;
        else
            return ordersFuture.get().stream().mapToInt(order -> order.getQuantity()).sum();
    }
}
