package com.market.stock.bsn.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.market.stock.data.entity.Symbol;
import com.market.stock.rest.dto.Statics;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Data
@Builder
public class StockDto {
    @JsonUnwrapped
    private Symbol symbol;
    private Statics statics;

}
