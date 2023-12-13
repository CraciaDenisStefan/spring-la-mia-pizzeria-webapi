package org.java.spring.db.dto;

import java.time.LocalDate;

public class PizzaOfferteDTO {

    private int pizza_id;
    private String titolo;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public int getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(int pizza_id) {
        this.pizza_id = pizza_id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return getPizza_id() + " - " + getTitolo() + " - " + getDataInizio() + " - " + getDataFine();
    }
}

