package com.abc.psk.simple_mircoservices_28.currency_exchange_service.repository;

import com.abc.psk.simple_mircoservices_28.currency_exchange_service.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
