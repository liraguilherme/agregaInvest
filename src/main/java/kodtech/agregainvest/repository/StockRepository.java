package kodtech.agregainvest.repository;

import kodtech.agregainvest.entity.Account;
import kodtech.agregainvest.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}
