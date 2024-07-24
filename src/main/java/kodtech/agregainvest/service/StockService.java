package kodtech.agregainvest.service;


import kodtech.agregainvest.controller.dto.CreateStockDto;
import kodtech.agregainvest.entity.Stock;
import kodtech.agregainvest.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        // DTO -> ENTITY
        var stock = new Stock(
          createStockDto.stockId(),
                createStockDto.description()
        );
        stockRepository.save(stock);
    }
}


