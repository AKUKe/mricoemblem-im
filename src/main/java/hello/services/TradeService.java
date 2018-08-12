package hello.services;

import hello.bean.Trade;

public interface TradeService {

    void afterTradeExecuted(Trade trade);
}
