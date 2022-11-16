package tn.esprit.spring.service;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;

@ExtendWith(MockitoExtension.class)
class StockServiceMock {

	@InjectMocks
	StockServiceImpl stockService;
	
	@Mock
StockRepository stockRepository;
	
	Stock s = new Stock("stock test",10,100);
	Stock s1 = new Stock("stock test",50,150);
	Stock s2 = new Stock("stock test",7,160);
	
	List<Stock> stock = new ArrayList<Stock>() {{add(s1); add(s2);}}; 
	
	@Test
	void testGetAllStock() {
	stockService.retrieveAllStocks();
	verify(stockRepository).findAll();
	}
	
	@Test
	void testGetStock() {
	Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
	assertNotNull(stockService.retrieveStock((long)3));	
	}
	
	@Test
	void testaddStock() {
	Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(s);
	assertNotNull(stockService.addStock(s));
	//verify(stockRepository).save(s);
	}
	
	@Test
	void testUpdateStock() {
	Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(s);
	s.setQte(55);
	assertNotNull(stockService.updateStock(s));	
	assertEquals(55, s.getQte());
	}
	
	
	@Test
	void testDeleteStock() {
	stockService.deleteStock((long)3);
	verify(stockRepository).deleteById((long)3);
	}
	
	
	
	
	
	
	
	
	
	
}
