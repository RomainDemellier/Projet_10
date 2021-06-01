package com.oc.projets.projet_10;

import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.dto.ExemplaireDTO;
import com.oc.projets.projet_10.dto.UsagerDTO;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.*;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.ProlongationException;
import com.oc.projets.projet_10.service.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@Testcontainers
@SpringBootTest(classes = Projet7Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
//@Sql("file:db/all_data.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmpruntServiceTests {

/*	@Value("${test}")
	private String string;*/

	@Mock
	private AuteurService auteurService;

	@Mock
	private  LivreService livreService;

	@Mock
	private ExemplaireService exemplaireService;

	@Mock
	private UsagerService usagerService;

	@Mock
	private ReservationService reservationService;

	@Mock
	private Emprunt emprunt;

	@Mock
	private Livre livre;

	@Mock
	private Usager usagerMock;

	@Mock
	private UsagerGetDTO usagerGetDTOMock;

	@Mock
	private EmpruntDTO empruntDTOMock;

	@Mock
	private ExemplaireDTO exemplaireDTOMock;

	@Autowired
	@Spy
	@InjectMocks
	private EmpruntService empruntService;

	List<Emprunt> empruntList = new ArrayList<Emprunt>();

	Livre livre1 = new Livre();
	Livre livre2 = new Livre();
	Livre livre3 = new Livre();
	Livre livre4 = new Livre();
	Livre livre5 = new Livre();
	Livre livre6 = new Livre();

	Exemplaire exemplaire1 = new Exemplaire();
	Exemplaire exemplaire2 = new Exemplaire();
	Exemplaire exemplaire3 = new Exemplaire();
	Exemplaire exemplaire4 = new Exemplaire();
	Exemplaire exemplaire5 = new Exemplaire();
	Exemplaire exemplaire6 = new Exemplaire();



/*	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withUsername("romain")
			.withPassword("romain")
			.withDatabaseName("test");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@BeforeEach
	public void setup() {
		 reservationServiceMock = Mockito.spy(reservationService);
	}*/

	@BeforeAll
	public void setup(){
		livre1.setId(1L);
		livre2.setId(2L);
		livre3.setId(3L);
		livre4.setId(4L);
		livre5.setId(5L);
		livre6.setId(6L);

		exemplaire1.setLivre(livre1);
		exemplaire2.setLivre(livre2);
		exemplaire3.setLivre(livre3);
		exemplaire4.setLivre(livre4);
		exemplaire5.setLivre(livre5);
		exemplaire6.setLivre(livre6);

		Emprunt emprunt1 = new Emprunt();
		emprunt1.setExemplaire(exemplaire1);
		Emprunt emprunt2 = new Emprunt();
		emprunt2.setExemplaire(exemplaire2);
		Emprunt emprunt3 = new Emprunt();
		emprunt3.setExemplaire(exemplaire3);
		Emprunt emprunt4 = new Emprunt();
		emprunt4.setExemplaire(exemplaire4);
		Emprunt emprunt5 = new Emprunt();
		emprunt5.setExemplaire(exemplaire5);

		empruntList.add(emprunt1);
		empruntList.add(emprunt2);
		empruntList.add(emprunt3);
		empruntList.add(emprunt4);
		empruntList.add(emprunt5);
	}


/*	@Test
	@Sql("file:db/test_data2.sql")
	void contextALoads() {

	}*/

	@Test
	@Sql({ "file:db/delete_all_tables.sql", "file:db/test_data3.sql" })
	void contextBLoads() {
		System.out.println("context loads2");
		System.out.println(this.usagerService.getAll().size());
/*	System.out.println(this.auteurService.findAll().size());
	System.out.println(this.livreService.getAllLivres().size());
	System.out.println(this.exemplaireService.getAllExemplaires().size());
	System.out.println(this.usagerService.getAll().size());
	System.out.println(this.empruntService.getListEmprunts().size());
	System.out.println(this.reservationService.getAllReservations().size());*/
	}

	@Test
	@DisplayName("Teste la méthode prolonger avec un emprunt déja prolongé")
	void checkProlongerEmprunt1(){
		when(this.emprunt.getProlonge()).thenReturn(true);
		System.out.println("dans check prolonger");
		System.out.println(this.emprunt.getProlonge());
		Assertions.assertThrows(EmpruntException.class, () -> this.empruntService.prolonger(this.emprunt));
	}

	@Test
	@DisplayName("Teste la méthode prolonger avec une date retour dépassée")
	void checkProlongerEmprunt2(){
		LocalDate localDate = LocalDate.now().minusDays(new Long(1));
		when(this.emprunt.getDateRetour()).thenReturn(localDate);
		Assertions.assertThrows(ProlongationException.class, () -> this.empruntService.prolonger(this.emprunt));
	}

	@Test
	@DisplayName("Teste la méthode prolonger avec un emprunt qui respecte les règles métiers")
	void checkProlongerEmprunt3() throws EmpruntException,ProlongationException {
		Emprunt empruntTest = new Emprunt();
		empruntTest.setProlonge(false);
		empruntTest.setDateEmprunt(LocalDate.now());
		empruntTest.setDateRetour(LocalDate.now().plusDays(new Long(28)));
		Assertions.assertDoesNotThrow(() -> this.empruntService.prolonger(empruntTest));
		assertThat(empruntTest.getProlonge()).isEqualTo(true);
	}

	@Test
	@DisplayName("Teste la méthode dejaEnPossession: l'usager emprunte un exemplaire, mais il possède déjà un exemplaire de ce livre")
	void checkDejaEnPossession(){
/*		livre1.setId(1L);
		livre2.setId(2L);
		livre3.setId(3L);
		livre4.setId(4L);
		livre5.setId(5L);
		livre6.setId(6L);

		exemplaire1.setLivre(livre1);
		exemplaire2.setLivre(livre2);
		exemplaire3.setLivre(livre3);
		exemplaire4.setLivre(livre4);
		exemplaire5.setLivre(livre5);
		exemplaire6.setLivre(livre6);

		Emprunt emprunt1 = new Emprunt();
		emprunt1.setExemplaire(exemplaire1);
		Emprunt emprunt2 = new Emprunt();
		emprunt2.setExemplaire(exemplaire2);
		Emprunt emprunt3 = new Emprunt();
		emprunt3.setExemplaire(exemplaire3);
		Emprunt emprunt4 = new Emprunt();
		emprunt4.setExemplaire(exemplaire4);
		Emprunt emprunt5 = new Emprunt();
		emprunt5.setExemplaire(exemplaire5);

		empruntList.add(emprunt1);
		empruntList.add(emprunt2);
		empruntList.add(emprunt3);
		empruntList.add(emprunt4);
		empruntList.add(emprunt5);*/

		when(empruntDTOMock.getUsager()).thenReturn(this.usagerGetDTOMock);
		when(this.empruntDTOMock.getExemplaire()).thenReturn(this.exemplaireDTOMock);
		//when(this.empruntDTOMock.getId()).thenReturn(5L);
		when(this.usagerGetDTOMock.getId()).thenReturn(2L);
		when(this.usagerService.findById(any(Long.class))).thenReturn(this.usagerMock);
		when(this.empruntService.findByUsagerAndActif(any(Usager.class))).thenReturn(this.empruntList);
		when(this.exemplaireService.findById(any(Long.class))).thenReturn(exemplaire3);
		Assertions.assertThrows(EmpruntException.class, () -> this.empruntService.dejaEnPossession(empruntDTOMock));
	}

	@Test
	@DisplayName("Teste la méthode dejaEnPossession: l'usager ne détient pas d'exemplaire de ce livre")
	void checkDejaEnPossession2(){
/*		livre1.setId(1L);
		livre2.setId(2L);
		livre3.setId(3L);
		livre4.setId(4L);
		livre5.setId(5L);
		livre6.setId(6L);

		exemplaire1.setLivre(livre1);
		exemplaire2.setLivre(livre2);
		exemplaire3.setLivre(livre3);
		exemplaire4.setLivre(livre4);
		exemplaire5.setLivre(livre5);
		exemplaire6.setLivre(livre6);

		Emprunt emprunt1 = new Emprunt();
		emprunt1.setExemplaire(exemplaire1);
		Emprunt emprunt2 = new Emprunt();
		emprunt2.setExemplaire(exemplaire2);
		Emprunt emprunt3 = new Emprunt();
		emprunt3.setExemplaire(exemplaire3);
		Emprunt emprunt4 = new Emprunt();
		emprunt4.setExemplaire(exemplaire4);
		Emprunt emprunt5 = new Emprunt();
		emprunt5.setExemplaire(exemplaire5);

		empruntList.add(emprunt1);
		empruntList.add(emprunt2);
		empruntList.add(emprunt3);
		empruntList.add(emprunt4);
		empruntList.add(emprunt5);*/

		when(empruntDTOMock.getUsager()).thenReturn(this.usagerGetDTOMock);
		when(this.empruntDTOMock.getExemplaire()).thenReturn(this.exemplaireDTOMock);
		//when(this.empruntDTOMock.getId()).thenReturn(5L);
		when(this.usagerGetDTOMock.getId()).thenReturn(2L);
		when(this.usagerService.findById(any(Long.class))).thenReturn(this.usagerMock);
		when(this.empruntService.findByUsagerAndActif(any(Usager.class))).thenReturn(this.empruntList);
		when(this.exemplaireService.findById(any(Long.class))).thenReturn(exemplaire6);
		Assertions.assertDoesNotThrow(() -> this.empruntService.dejaEnPossession(empruntDTOMock));
	}
}
