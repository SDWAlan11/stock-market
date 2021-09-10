package com.market.stock.bsn;

import java.util.concurrent.ExecutionException;

public interface StaticsFactory {
    double getLastPrice(String stockId) throws ExecutionException, InterruptedException;
    double getAccumulatedPrice(String stockId) throws ExecutionException, InterruptedException;
    int getQuantity(String stockId) throws ExecutionException, InterruptedException;
}
