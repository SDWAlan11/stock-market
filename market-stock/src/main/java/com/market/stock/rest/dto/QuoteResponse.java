package com.market.stock.rest.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName("quoteResponse")
public class QuoteResponse {
    private List<Result> result;
    private Error error;
}

