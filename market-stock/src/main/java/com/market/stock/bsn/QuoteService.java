package com.market.stock.bsn;

import com.market.stock.data.entity.Quote;
import com.market.stock.data.entity.Symbol;
import com.market.stock.data.repository.QuoteRepository;
import com.market.stock.data.repository.SymbolRepository;
import com.market.stock.rest.dto.QuoteResponse;
import com.market.stock.rest.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
public class QuoteService {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private QuoteRepository quoteRepository;

    @Resource
    private SymbolRepository symbolRepository;

    private String yahooServiceUrl = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=";

    public Quote getStockById(String id){
        ResponseEntity<QuoteResponse> quoteResponseEntity = restTemplate.getForEntity(yahooServiceUrl + id, QuoteResponse.class);
        Result result = quoteResponseEntity.getBody().getResult().stream().findAny().orElseThrow(() -> new NoSuchElementException("Quote not found"));
        return Quote.builder()
                .ask(result.ask)
                .bid(result.bid)
                .currency(Quote.Currency.valueOf(result.currency))
                .regularMarketPrice(result.regularMarketPrice)
                .symbol(Symbol.builder().id(result.symbol).name(result.shortName).build()).build();
    }

    public Quote addQuote(Quote quote){
        symbolRepository.save(quote.getSymbol());
        return quoteRepository.save(quote);
    }

    public List<Quote> getQuotesBySymbolId(String symbolId){
        return quoteRepository.findAllBySymbolOrderByCreatedOnDesc(Symbol.builder().id(symbolId).build());
    }

    private HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;

    }
}
