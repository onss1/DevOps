package tn.esprit.rh.achat;


import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReglementServiceImplTest {
	@Autowired
	IReglementService ReglementService;

	
	public void  testretrieveAllReglements() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Date Date2 = dateFormat.parse("26/10/1919");
		List<Reglement> reglements = (List<Reglement>) ReglementService.retrieveAllReglements();
		int expected = reglements.size();
		Reglement s =  new Reglement( (float)50, (float)45,false, Date1);
		Reglement reglement = ReglementService.addReglement(s);
		assertEquals(expected + 1,ReglementService.retrieveAllReglements().size());
		ReglementService.deleteReglement(reglement.getIdReglement());
		  }
	
	@Test
	public void testAddReglement() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		List<Reglement> Reglements = ReglementService.retrieveAllReglements();
		int expected=Reglements.size();
		Reglement s = new Reglement( (float)50, (float)45,false, Date1);
		Reglement savedReglement= ReglementService.addReglement(s);

		assertEquals(expected+1, ReglementService.retrieveAllReglements().size());
		assertNotNull(savedReglement.getIdReglement());
		assertNotNull(savedReglement.getMontantPaye());
		assertNotNull(savedReglement.getMontantRestant());
		assertNotNull(savedReglement.getPayee());
		assertNotNull(savedReglement.getDateReglement());
		
		ReglementService.deleteReglement(savedReglement.getIdReglement());

	}
	
	

	@Test
	public void testAddReglementOptimized() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date Date1 = dateFormat.parse("25/09/2000");
		Reglement s = new Reglement(50,45,false,Date1);
		Reglement savedReglement= ReglementService.addReglement(s);
		assertNotNull(savedReglement.getIdReglement());
		assertSame(false, savedReglement.getPayee());
		assertTrue(savedReglement.getMontantRestant() > 0);
		ReglementService.deleteReglement(savedReglement.getIdReglement());

	}


}