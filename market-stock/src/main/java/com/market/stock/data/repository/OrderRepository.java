package com.market.stock.data.repository;

import com.market.stock.data.entity.Orders;
import com.market.stock.data.entity.Symbol;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>, JpaSpecificationExecutor<Integer> {
    @Async
    Future<List<Orders>> findAllBySymbol(Symbol symbol);
}


