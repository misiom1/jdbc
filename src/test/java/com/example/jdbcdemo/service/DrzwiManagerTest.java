package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Drzwi;

public class DrzwiManagerTest {

	DrzwiManager drzwiManager = new DrzwiManager();
	private final static String PRODUCENT_1 = "Sony";
	private final static String MODEL_1 = "Bravo";
	private final static String KOLOR_1 = "Czerwony";
	private final static String PRODUCENT_2 = "Fiat";
	private final static String MODEL_2 = "Drzwiasty";
	private final static String KOLOR_2 = "Brązowy";
	private final static String MODEL_3 = "Wielki";
	Drzwi drzwi = new Drzwi(PRODUCENT_1, MODEL_1, KOLOR_1);
	Drzwi drzwi2 = new Drzwi(PRODUCENT_2, MODEL_2, KOLOR_2);
	Drzwi drzwi3 = new Drzwi("Samsung", MODEL_3, KOLOR_2);
	Drzwi klient = new Drzwi("Jan", "Kowalski");
	Drzwi klient2 = new Drzwi("Maciej", "Maciejewski");
	@Test
	public void checkConnection(){
		assertNotNull(drzwiManager.getConnection());
		
	}
	
	@Test
	public void checkAdding(){
		
		drzwiManager.clearDrzwis();
		assertEquals(1,drzwiManager.addDrzwi(drzwi));
		assertEquals(1,drzwiManager.addDrzwi(drzwi2));
		assertEquals(1,drzwiManager.addDrzwi(drzwi3));
		drzwiManager.Commit();
		List<Drzwi> drzwis = drzwiManager.getAllDrzwis();
		Drzwi drzwiRetrieved = drzwis.get(0);
		Drzwi drzwiRetrieved1 = drzwis.get(1);
		Drzwi drzwiRetrieved2 = drzwis.get(2);
		
		assertEquals(PRODUCENT_1, drzwiRetrieved.getProducent());
		assertEquals(MODEL_1, drzwiRetrieved.getModel());
		assertEquals(KOLOR_1, drzwiRetrieved.getKolor());
		assertEquals(PRODUCENT_2, drzwiRetrieved1.getProducent());
		assertEquals(MODEL_2, drzwiRetrieved1.getModel());
		assertEquals(KOLOR_2, drzwiRetrieved1.getKolor());
		assertEquals("Samsung", drzwiRetrieved2.getProducent());
		assertEquals(MODEL_3, drzwiRetrieved2.getModel());
		assertEquals(KOLOR_2, drzwiRetrieved2.getKolor());
		
		assertEquals(1, drzwiManager.addKlient(klient));
		assertEquals(1, drzwiManager.addKlient(klient2));
		drzwiManager.Commit();
		
		List<Drzwi> klients = drzwiManager.getAllKlients();
		drzwiRetrieved = klients.get(0);
		drzwiRetrieved1 = klients.get(1);
		assertEquals("Jan", drzwiRetrieved.getImie());
		assertEquals("Kowalski", drzwiRetrieved.getNazwisko());
		assertEquals("Maciej", drzwiRetrieved1.getImie());
		assertEquals("Maciejewski", drzwiRetrieved1.getNazwisko());
		
		assertEquals(1, drzwiManager.addKup(drzwi, klient));
		assertEquals(1, drzwiManager.addKup(drzwi3, klient2));
		assertEquals(1, drzwiManager.addKup(drzwi, klient2));
		drzwiManager.Commit();
	}
	@Test
	public void checkDeleting ()
	{
		drzwiManager.clearDrzwis();
		checkAdding();
		assertEquals(1, drzwiManager.deleteDrzwi(drzwi2));
		drzwiManager.Commit();
		
		List<Drzwi> drzwis = drzwiManager.getAllDrzwis();
		Drzwi drzwiRetrieved = drzwis.get(0);
		Drzwi drzwiRetrieved1 = drzwis.get(1);
				
		assertEquals(PRODUCENT_1, drzwiRetrieved.getProducent());
		assertEquals(MODEL_1, drzwiRetrieved.getModel());
		assertEquals(KOLOR_1, drzwiRetrieved.getKolor());
		assertEquals("Samsung", drzwiRetrieved1.getProducent());
		assertEquals(MODEL_3, drzwiRetrieved1.getModel());
		assertEquals(KOLOR_2, drzwiRetrieved1.getKolor());
		
		assertEquals(1, drzwiManager.deleteKlient(klient2));
		drzwiManager.Commit();

		List<Drzwi> klients = drzwiManager.getAllKlients();
		Drzwi klientRetrieved = klients.get(0);
		
		assertEquals("Jan", klientRetrieved.getImie());
		assertEquals("Kowalski", klientRetrieved.getNazwisko());
	}
	@Test
	public void checkJoin() {
		drzwiManager.clearDrzwis();
		drzwiManager.Commit();
		assertEquals(1,drzwiManager.addDrzwi(drzwi));
		assertEquals(1,drzwiManager.addDrzwi(drzwi2));
		assertEquals(1,drzwiManager.addDrzwi(drzwi3));
		drzwiManager.Commit();
		assertEquals(1, drzwiManager.addKlient(klient));
		assertEquals(1, drzwiManager.addKlient(klient2));
		drzwiManager.Commit();
		assertEquals(1, drzwiManager.addKup(drzwi, klient));
		assertEquals(1, drzwiManager.addKup(drzwi3, klient2));
		assertEquals(1, drzwiManager.addKup(drzwi2, klient2));
		drzwiManager.Commit();
		List<Drzwi> drzwiKlient = drzwiManager.getAllDrzwiKlient();
		Drzwi klientRetrieved = drzwiKlient.get(0);
		Drzwi klientRetrieved1 = drzwiKlient.get(1);
		Drzwi klientRetrieved2 = drzwiKlient.get(2);
		assertEquals("Sony", klientRetrieved.getProducent());
		assertEquals("Bravo", klientRetrieved.getModel());
		assertEquals("Czerwony", klientRetrieved.getKolor());
		assertEquals("Jan", klientRetrieved.getImie());
		assertEquals("Kowalski", klientRetrieved.getNazwisko());
		
		assertEquals("Samsung", klientRetrieved1.getProducent());
		assertEquals("Wielki", klientRetrieved1.getModel());
		assertEquals("Brązowy", klientRetrieved1.getKolor());
		assertEquals("Maciej", klientRetrieved1.getImie());
		assertEquals("Maciejewski", klientRetrieved1.getNazwisko());
		
		assertEquals("Fiat", klientRetrieved2.getProducent());
		assertEquals("Drzwiasty", klientRetrieved2.getModel());
		assertEquals("Brązowy", klientRetrieved2.getKolor());
		assertEquals("Maciej", klientRetrieved2.getImie());
		assertEquals("Maciejewski", klientRetrieved2.getNazwisko());
		
		List<Drzwi> drzwi = drzwiManager.getDrzwiToKlient(klient2);
		klientRetrieved = drzwi.get(0);
		klientRetrieved1 = drzwi.get(1);
		
		assertEquals("Samsung", klientRetrieved.getProducent());
		assertEquals("Wielki", klientRetrieved.getModel());
		assertEquals("Brązowy", klientRetrieved.getKolor());
		assertEquals("Maciej", klientRetrieved.getImie());
		assertEquals("Maciejewski", klientRetrieved.getNazwisko());
		
		assertEquals("Fiat", klientRetrieved1.getProducent());
		assertEquals("Drzwiasty", klientRetrieved1.getModel());
		assertEquals("Brązowy", klientRetrieved1.getKolor());
		assertEquals("Maciej", klientRetrieved1.getImie());
		assertEquals("Maciejewski", klientRetrieved1.getNazwisko());
		
		drzwiManager.deleteKup(drzwi3, klient2);
		drzwiManager.Commit();
		drzwi = drzwiManager.getDrzwiToKlient(klient2);
		klientRetrieved = drzwi.get(0);
			
		assertEquals("Fiat", klientRetrieved1.getProducent());
		assertEquals("Drzwiasty", klientRetrieved1.getModel());
		assertEquals("Brązowy", klientRetrieved1.getKolor());
		assertEquals("Maciej", klientRetrieved1.getImie());
		assertEquals("Maciejewski", klientRetrieved1.getNazwisko());
	}
	@Test
	public void checkUpdate ()
	{
		drzwiManager.clearDrzwis();
		checkAdding();
		checkDeleting();
		assertEquals(1, drzwiManager.deleteDrzwi(drzwi3));
		assertEquals(1, drzwiManager.updateDrzwi(drzwi, "Ferrari", "Drzwiowe", "Dark Brown"));
		drzwiManager.Commit();
		
		List<Drzwi> drzwis = drzwiManager.getAllDrzwis();
		Drzwi drzwiRetrieved = drzwis.get(0);
				
		assertEquals("Ferrari", drzwiRetrieved.getProducent());
		assertEquals("Drzwiowe", drzwiRetrieved.getModel());
		assertEquals("Dark Brown", drzwiRetrieved.getKolor());
	}
}
