package org.nrt.core.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private LocalDate depositFirstdate;

    private String depositPercent;

    private Integer depositTermInMonths;

    public Deposit(){
    }

    public Deposit(Integer depositId,
                   Client client,
                   Bank bank,
                   LocalDate depositFirstdate,
                   String depositPercent,
                   Integer depositTermInMonths){
        this.depositId = depositId;
        this.client = client;
        this.bank = bank;
        this.depositFirstdate = depositFirstdate;
        this.depositPercent = depositPercent;
        this.depositTermInMonths = depositTermInMonths;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public LocalDate getDepositFirstdate() {
        return depositFirstdate;
    }

    public void setDepositFirstdate(LocalDate depositFirstdate) {
        this.depositFirstdate = depositFirstdate;
    }

    public String getDepositPercent() {
        return depositPercent;
    }

    public void setDepositPercent(String depositPercent) {
        this.depositPercent = depositPercent;
    }

    public Integer getDepositTermInMonths() {
        return depositTermInMonths;
    }

    public void setDepositTermInMonths(Integer depositTermInMonths) {
        this.depositTermInMonths = depositTermInMonths;
    }
}
