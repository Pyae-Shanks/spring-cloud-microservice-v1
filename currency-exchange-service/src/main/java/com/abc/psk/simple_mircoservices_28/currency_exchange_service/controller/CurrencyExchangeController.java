package com.abc.psk.simple_mircoservices_28.currency_exchange_service.controller;

import com.abc.psk.simple_mircoservices_28.currency_exchange_service.entity.ExchangeValue;
import com.abc.psk.simple_mircoservices_28.currency_exchange_service.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired private Environment environment;
    @Autowired private ExchangeValueRepository exchangeValueRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from,
                                               @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        logger.info("ExchangeValue: {}", exchangeValue);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
