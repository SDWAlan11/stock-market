package com.market.stock.data;

import com.market.stock.data.entity.Quote;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.QuoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
@Transactional
@Rollback(value = false)
public class TestLastPriceRepository {

    @Resource
    private QuoteRepository lastPriceRepository;

    @Test
    public void testRead(){
        Future<Optional<Quote>> lastPrice = lastPriceRepository.findTopBySymbolOrderByCreatedOnDesc(Symbol.builder().id("AAL").build());
        Assert.notNull(lastPrice, "Not found");

    }

}
