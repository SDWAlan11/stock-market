package com.market.stock.bsn.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.market.stock.data.entity.Symbol;
import com.market.stock.rest.dto.Statics;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {
    @JsonUnwrapped
    private Symbol symbol;
    private Statics statics;
}
