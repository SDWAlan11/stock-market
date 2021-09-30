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
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StockService.class)
public class TestSymbolService {

    @MockBean
    private SymbolRepository symbolRepository;

    @Resource
    private StockService stockService;

    @MockBean
    private StaticsFactory staticsFactory;

    @Test
    public void testGetStockById() throws ExecutionException, InterruptedException {
        when(symbolRepository.findById("123456")).thenReturn(Optional.of(Symbol.builder().id("123456").build()));

        when(staticsFactory.getQuantity(any())).thenReturn(1);
        when(staticsFactory.getAccumulatedPrice(any())).thenReturn(1.0);
        when(staticsFactory.getLastPrice(any())).thenReturn(1.0);



        StockDto stock = stockService.getStockById("123456");
        Assert.assertNotNull(stock);
    }

}
