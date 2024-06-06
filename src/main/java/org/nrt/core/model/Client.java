package org.nrt.core.model;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    private String clientName;

    private String clientShortname;

    private String clientAddress;

    @Enumerated(EnumType.STRING)
    private ClientLegalForm clientLegalForm;

    public Client(){
    }

    public Client(Integer clientId,
                  String clientName,
                  String clientShortname,
                  String clientAddress,
                  ClientLegalForm clientLegalForm){
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientShortname = clientShortname;
        this.clientAddress = clientAddress;
        this.clientLegalForm = clientLegalForm;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientShortname() {
        return clientShortname;
    }

    public void setClientShortname(String clientShortname) {
        this.clientShortname = clientShortname;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public ClientLegalForm getClientLegalForm() {
        return clientLegalForm;
    }

    public void setClientLegalForm(ClientLegalForm clientLegalForm) {
        this.clientLegalForm = clientLegalForm;
    }
}

