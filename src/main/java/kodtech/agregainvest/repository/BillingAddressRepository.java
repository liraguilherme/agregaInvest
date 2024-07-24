package kodtech.agregainvest.repository;

import kodtech.agregainvest.entity.AccountStock;
import kodtech.agregainvest.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {

}
