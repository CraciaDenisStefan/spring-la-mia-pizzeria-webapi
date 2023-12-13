package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length; 
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 60)
    @NotEmpty(message = "Il campo nome è obbligatorio")
    @Length(min = 3, max = 60, message = "Il nome deve essere lungo tra 3 e 60 caratteri")
    private String nome;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    private String descrizione;

    @NotEmpty(message = "Il campo fotoUrl è obbligatorio")
    private String fotoUrl;

    @NotNull(message = "Il campo prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0.01")
    @DecimalMax(value = "999.99", message = "Il prezzo deve essere minore di 999.99")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double prezzo;
    
    @OneToMany(mappedBy = "pizza")
	private List<OffertaSpeciale> offerte;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
	private List<Ingrediente> ingrediente;
    
    public Pizza() { }
	public Pizza(String nome, String descrizione, String fotoUrl, double prezzo, Ingrediente... ingrediente) {
		setNome(nome);
		setDescrizione(descrizione);
		setFotoUrl(fotoUrl);
		setPrezzo(prezzo);
		setIngrediente(ingrediente);
	
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFotoUrl() {
		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	public List<OffertaSpeciale> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<OffertaSpeciale> offerte) {
		this.offerte = offerte;
	}
	
	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}
	
	
	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}
	public void setIngrediente(Ingrediente... ingrediente) {	
		setIngrediente(Arrays.asList(ingrediente));
	}
	
	public void clearIngrediente() {
		getIngrediente().clear();
	}
	
	
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getNome() + " - " 
				+ getDescrizione() + " (" + getPrezzo() + ")";
	}
	
}
