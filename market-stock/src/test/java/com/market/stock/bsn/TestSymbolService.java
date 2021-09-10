package com.market.stock.bsn;

import com.market.stock.bsn.dto.StockDto;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.SymbolRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StockService.class)
public class TestSymbolService {

    @MockBean
    private SymbolRepository symbolRepository;

    @Resource
    private StockService stockService;

    @Test
    public void testGetStockById(){
        when(symbolRepository.findById("123456")).thenReturn(Optional.of(Symbol.builder().id("123456").build()));

        StockDto stock = stockService.getStockById("123456");
        Assert.assertNotNull(stock);
    }

}
