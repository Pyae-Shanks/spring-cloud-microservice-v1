package com.abc.psk.simple_mircoservices_28.currency_conversion_service.controller;

import com.abc.psk.simple_mircoservices_28.currency_conversion_service.entity.CurrencyConversionBean;
import com.abc.psk.simple_mircoservices_28.currency_conversion_service.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        //CurrencyConversionBean currencyConversionBean = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, from, to).getBody();

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> currencyConversionBean = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        currencyConversionBean.getBody().setTotalCalculatedAmount(currencyConversionBean.getBody().getConversionMultiple().multiply(quantity));
        return currencyConversionBean.getBody();
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                       @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {

        CurrencyConversionBean currencyConversionBean = proxy.retrieveExchangeValue(from, to);
        logger.info("Response: {}", currencyConversionBean);
        currencyConversionBean.setTotalCalculatedAmount(currencyConversionBean.getConversionMultiple().multiply(quantity));
        currencyConversionBean.setQuantity(quantity);
        return currencyConversionBean;
    }
}
