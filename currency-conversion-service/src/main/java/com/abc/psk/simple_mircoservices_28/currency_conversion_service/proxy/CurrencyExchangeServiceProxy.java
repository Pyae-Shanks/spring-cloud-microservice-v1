package com.abc.psk.simple_mircoservices_28.currency_conversion_service.proxy;

import com.abc.psk.simple_mircoservices_28.currency_conversion_service.entity.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
//@RibbonClient(name = "netflix-api-gateway-server")
public interface CurrencyExchangeServiceProxy {

    //@GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
