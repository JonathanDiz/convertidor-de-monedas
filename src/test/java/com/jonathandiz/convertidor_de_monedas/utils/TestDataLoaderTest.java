package com.jonathandiz.convertidor_de_monedas.utils;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistoryModel;
import com.jonathandiz.convertidor_de_monedas.repository.ExchangeRateHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestDataLoaderTest {

    @Autowired
    private ExchangeRateHistoryRepository repository;

    @Test
    void testLoadTestData() {
        Iterable<ExchangeRateHistoryModel> histories = repository.findAll();
        assertThat(histories).isNotEmpty();
    }
}
