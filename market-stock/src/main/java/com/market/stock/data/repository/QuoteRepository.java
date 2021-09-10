package com.market.stock.data.repository;

import com.market.stock.data.entity.Quote;
import com.market.stock.data.entity.Symbol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface QuoteRepository extends CrudRepository<Quote, String> {
    @Async
    Future<Optional<Quote>> findTopBySymbolOrderByCreatedOnDesc(Symbol symbol);

    List<Quote> findAllBySymbolOrderByCreatedOnDesc(Symbol symbol);
}
