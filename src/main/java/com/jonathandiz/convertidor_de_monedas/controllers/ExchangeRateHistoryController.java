package com.jonathandiz.convertidor_de_monedas.controllers;

import com.jonathandiz.convertidor_de_monedas.model.ExchangeRateHistory;
import com.jonathandiz.convertidor_de_monedas.services.ExchangeRateHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin

public class ExchangeRateHistoryController {

	    private final ExchangeRateHistoryService service;

	    public ExchangeRateHistoryController(ExchangeRateHistoryService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<ExchangeRateHistory> saveHistory(@RequestBody ExchangeRateHistory history) {
	        return ResponseEntity.ok(service.saveHistory(history));
	    }

	    @GetMapping
	    public ResponseEntity<List<ExchangeRateHistory>> getAllHistory() {
	        return ResponseEntity.ok(service.getAllHistory());
	    }
	}

