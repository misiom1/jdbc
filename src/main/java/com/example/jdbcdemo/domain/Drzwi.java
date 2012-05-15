package com.example.jdbcdemo.domain;

public class Drzwi {
	private long id;
	private String producent;
	private String model;
	private String kolor;
	private String imie;
	private String nazwisko;
	private long klientId;
	private long drzwiId;
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public long getKlientId() {
		return klientId;
	}

	public void setKlientId(long klientId) {
		this.klientId = klientId;
	}

	public long getDrzwiId() {
		return drzwiId;
	}

	public void setDrzwiId(long drzwiId) {
		this.drzwiId = drzwiId;
	}

	public Drzwi() {
	}
	
	public Drzwi(String producent, String model, String kolor) {
		super();
		this.producent = producent;
		this.model = model;
		this.kolor = kolor;
	}
	public Drzwi(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProducent() {
		return producent;
	}

	public void setProducent(String producent) {
		this.producent = producent;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getKolor() {
		return kolor;
	}

	public void setKolor(String kolor) {
		this.kolor = kolor;
	}
}
