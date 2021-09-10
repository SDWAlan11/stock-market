package com.market.stock.data;

import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.SymbolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
@Transactional
@Rollback(value = false)
public class TestSymbol {

    @Resource
    private SymbolRepository symbolRepository;

    @Test
    public void testRead(){
        Symbol symbol = symbolRepository.findById("AAL").orElseThrow( () -> new IllegalArgumentException("No stock found"));
        Assert.notNull(symbol, "Not found");

    }

    @Test
    public void testWrite(){
        Symbol symbol = Symbol.builder().id("WED").name("Wed").build();
        Symbol symbolRecovered = symbolRepository.save(symbol);

        Assert.notNull(symbolRecovered, "Stock not Found");

        System.err.println(symbolRecovered);

        System.out.println(symbolRepository.findAll());

    }
}
