package kodtech.agregainvest.repository;

import kodtech.agregainvest.entity.AccountStock;
import kodtech.agregainvest.entity.AccountStockId;
import kodtech.agregainvest.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId > {

}
