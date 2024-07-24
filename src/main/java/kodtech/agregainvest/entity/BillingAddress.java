package kodtech.agregainvest.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billingadress")
public class BillingAddress {

    @Id
    @Column(name = "account_id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;



    public BillingAddress() {
    }

    public BillingAddress(UUID id, Account account, Integer number, String street) {
        this.id = id;
        this.account = account;
        this.number = number;
        this.street = street;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
