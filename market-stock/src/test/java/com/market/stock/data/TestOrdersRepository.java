package com.market.stock.data;

import com.market.stock.data.entity.Orders;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
@Transactional
@Rollback(value = false)
public class TestOrdersRepository {

    @Resource
    private OrderRepository orderRepository;

    @Test
    public void testRead() throws ExecutionException, InterruptedException {
        Future<List<Orders>> orders = orderRepository.findAllBySymbol(Symbol.builder().id("AAL").build());
        Assert.notNull(orders.get(), "Not found");

    }

}
