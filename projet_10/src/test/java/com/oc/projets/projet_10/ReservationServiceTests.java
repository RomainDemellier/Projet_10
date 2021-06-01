package com.oc.projets.projet_10;

import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.*;
import com.oc.projets.projet_10.exception.*;
import com.oc.projets.projet_10.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Testcontainers
@SpringBootTest(classes = Projet7Application.class, properties = { "spring.mail.username=nothing" })
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @Mock
    private EmpruntService empruntServiceMock;

    @Mock
    private Livre livreMock;

    @Mock
    private Usager usagerMock;

    @Mock
    private ReservationDTO reservationDTOMock;

    @Autowired
    @Spy
    @InjectMocks
    private ReservationService reservationServiceSpy;

    /*    @Mock
    private Emprunt emprunt;*/

    /*    @Mock
    private  LivreService livreService;

    @Mock
    private UsagerService usagerService;*/

/*    @Mock
    private Reservation reservation;*/

    /*    @Mock
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;*/

/*    private Auteur julesVerne;

    private Livre vingtsMille;

    private Usager usager1;

    private Usager usager2;

    private Reservation reservation1;

    private Reservation reservation2;

    List<Reservation> reservations = new ArrayList<>();*/

/*    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:9.6.12")
            .withDatabaseName("test")
            .withUsername("romain")
            .withPassword("romain");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }*/

/*    @Before
    public void initScript(){
    }

    @BeforeEach
    public void setup() {

        //reservationServiceSpy = Mockito.spy(reservationService);
        julesVerne = new Auteur(1L,"Jules","Verne");
        vingtsMille = new Livre(3L,"Vingts Milles",julesVerne,"Science fiction", 2, 2, true);
        usager1 = new Usager(1L,"Demellier","Romain","ro@","123","USER");
        usager2 = new Usager(1L,"Demellier","Antoine","ro@","123","USER");
        reservation1 = new Reservation(1L,vingtsMille,usager1,LocalDateTime.now(),null);
        reservations.add(reservation1);
        reservation2 = new Reservation(2L,vingtsMille,usager1,LocalDateTime.now(),null);
        reservations.add(reservation2);
    }*/

    @Test
    @DisplayName("Teste la méthode isReservable avec un livre qui a un exemplaire dispo -> renvoie false")
    void checkIsReservable1(){
        when(this.livreMock.getNbreExemplaires()).thenReturn(1);
        when(this.livreMock.getNbreTotal()).thenReturn(2);
        doReturn(2).when(this.reservationServiceSpy).countReservationsByLivreAndActif(this.livreMock);
        assertThat(this.reservationServiceSpy.isLivreReservable(livreMock)).isEqualTo(false);
    }

    @Test
    @DisplayName("Teste la méthode isReservable avec le max de réservations -> renvoie false")
    void checkIsReservable2(){
        when(this.livreMock.getNbreTotal()).thenReturn(2);
        when(this.reservationServiceSpy.countReservationsByLivreAndActif(livreMock)).thenReturn(4);
        doReturn(4).when(this.reservationServiceSpy).countReservationsByLivreAndActif(this.livreMock);
        assertThat(this.reservationServiceSpy.isLivreReservable(livreMock)).isEqualTo(false);
    }

    @Test
    @DisplayName("Teste la méthode isReservable 0 exemplaires dispo et nbre de réservations < 2*nbreTotal -> renvoie true")
    void checkIsReservable3(){
        when(this.livreMock.getNbreExemplaires()).thenReturn(0);
        when(this.livreMock.getNbreTotal()).thenReturn(2);
        doReturn(3).when(this.reservationServiceSpy).countReservationsByLivreAndActif(this.livreMock);
        assertThat(this.reservationServiceSpy.isLivreReservable(livreMock)).isEqualTo(true);
    }

    @Test
    @DisplayName("Teste la méthode allReadyReserved avec déjà une réservation")
    void  checkAllReadyReserved1(){
        doReturn(1)
                .when(this.reservationServiceSpy)
                .numberReservationForLivreAndUsager(this.livreMock,this.usagerMock);
        assertThat(this.reservationServiceSpy.allReadyReserved(this.livreMock,this.usagerMock)).isEqualTo(true);
    }

    @Test
    @DisplayName("Teste la méthode allReadyReserved avec aucune réservation")
    void  checkAllReadyReserved2(){
        doReturn(0)
                .when(this.reservationServiceSpy)
                .numberReservationForLivreAndUsager(this.livreMock,this.usagerMock);
        assertThat(this.reservationServiceSpy.allReadyReserved(this.livreMock,this.usagerMock)).isEqualTo(false);
    }

    @Test
    @DisplayName("Teste la méthode checkIfEmpruntEnCours avec déjà un emprunt")
    void checkCheckIfEmpruntEnCours1(){
        when(this.empruntServiceMock.countEmpruntsByUsagerAndByLivre(this.usagerMock,this.livreMock)).thenReturn(1);
        assertThat(this.reservationServiceSpy.checkIfEmpruntEnCours(this.usagerMock,this.livreMock)).isEqualTo(true);
    }

    @Test
    @DisplayName("Teste la méthode checkIfEmpruntEnCours sans emprunts")
    void checkCheckIfEmpruntEnCours2(){
        when(this.empruntServiceMock.countEmpruntsByUsagerAndByLivre(this.usagerMock,this.livreMock)).thenReturn(0);
        assertThat(this.reservationServiceSpy.checkIfEmpruntEnCours(this.usagerMock,this.livreMock)).isEqualTo(false);
    }

    @Test
    @DisplayName("Teste la méthode create reservation avec isReservable à false -> Exception")
    void checkCreateReservation1(){
        doReturn(false).when(reservationServiceSpy).isLivreReservable(any(Livre.class));
        Assertions.assertThrows(ReservationException.class, () -> this.reservationServiceSpy.create(this.livreMock,this.usagerMock,this.reservationDTOMock));
    }

    @Test
    @DisplayName("Teste la méthode create reservation allReadyReserved -> Exception")
    void checkCreateReservation2() throws ReservationException {
        doReturn(true).when(this.reservationServiceSpy).isLivreReservable(this.livreMock);
        doReturn(true)
                .when(this.reservationServiceSpy)
                .allReadyReserved(this.livreMock,this.usagerMock);
        Assertions.assertThrows(ReservationException.class, () -> this.reservationServiceSpy.create(this.livreMock,this.usagerMock,this.reservationDTOMock));
    }

    @Test
    @DisplayName("Teste la méthode create reservation emprunt en cours -> Exception")
    void checkCreateReservation3() {
        doReturn(true).when(this.reservationServiceSpy).isLivreReservable(this.livreMock);
        doReturn(false)
                .when(this.reservationServiceSpy)
                .allReadyReserved(this.livreMock,this.usagerMock);
        doReturn(true)
                .when(this.reservationServiceSpy)
                .checkIfEmpruntEnCours(this.usagerMock,this.livreMock);
        Assertions.assertThrows(ReservationException.class, () -> this.reservationServiceSpy.create(this.livreMock,this.usagerMock,this.reservationDTOMock));
    }

    @Test
    @DisplayName("Teste la méthode create reservation sans Exception")
    void checkCreateReservation4() {
        doReturn(true).when(this.reservationServiceSpy).isLivreReservable(this.livreMock);
        doReturn(false)
                .when(this.reservationServiceSpy)
                .allReadyReserved(this.livreMock,this.usagerMock);
        doReturn(false)
                .when(this.reservationServiceSpy)
                .checkIfEmpruntEnCours(this.usagerMock,this.livreMock);
        Assertions.assertDoesNotThrow(() -> this.reservationServiceSpy.create(this.livreMock,this.usagerMock,this.reservationDTOMock));
    }

    @Test
    @DisplayName("Teste la méthode update réservation en s'assurant que la différence entre LocalDateTime.now() et la date limite " +
            "est comprise entre 48 et 49heures")
    void checkSetDateLimitReservation(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Reservation reservation = new Reservation();
        this.reservationServiceSpy.setDateLimit(reservation);
        LocalDateTime localDateTime1 = reservation.getDateLimit();
        Duration duration = Duration.between(localDateTime,localDateTime1);
        assertThat(duration.toHours()).isBetween(new Long(48), new Long(49));
    }

/*    @Test
    @DisplayName("Test la méthode findAndUpdate avec un id de réservation qui n'existe pas")
    @Order(7)
    @Sql({ "file:db/init_auteur_livre_usager_exemplaire.sql", "file:db/all_reservations.sql" })
    void checkFindAndUpdate1(){
        assertThrows(ResourceNotFoundException.class, () -> reservationService.findAndUpdate(30L));
    }

    @Test
    @DisplayName("Test la méthode findAndUpdate avec un id de réservation qui existe")
    @Order(8)
    //@Sql({ "file:db/init_auteur_livre_usager_exemplaire.sql", "file:db/all_reservations.sql" })
    void checkFindAndUpdate2(){
        assertDoesNotThrow(() -> this.reservationServiceSpy.findAndUpdate(7L));
        verify(this.reservationServiceSpy, times(1)).setDateLimit(any(Reservation.class));
    }*/

    /*@Test
    @DisplayName("Test méthode isStillReservation avec 0 réservations")
    @Order(9)
    void checkIsStillReservation1(){
        when(reservationServiceSpy.numberOfReservationsDateLimitNull(any(Livre.class))).thenReturn(0);
        assertThat(reservationServiceSpy.isStillReservations(new Livre())).isEqualTo(false);
    }

    @Test
    @DisplayName("Test méthode isStillReservation avec nombre de réservations = 0" )
    @Order(10)
    void checkIsStillReservation2(){
        when(reservationServiceSpy.numberOfReservationsDateLimitNull(any(Livre.class))).thenReturn(1);
        assertThat(reservationServiceSpy.isStillReservations(new Livre())).isEqualTo(true);
    }

    @Test
    @DisplayName("Test méthode isStillReservation avec nombre de réservations = 10" )
    @Order(11)
    void checkIsStillReservation3(){
        when(reservationServiceSpy.numberOfReservationsDateLimitNull(any(Livre.class))).thenReturn(10);
        assertThat(reservationServiceSpy.isStillReservations(new Livre())).isEqualTo(true);
    }

    @Test
    @DisplayName("Test de la méthode delete")
    @Order(12)
    //@Sql({"file:db/delete_reservations.sql", "file:db/reservations_test_findAndDelete.sql" })
    void checkFindAndDelete(){
        when(this.reservation.getDateLimit()).thenReturn(LocalDateTime.now());
        when(this.reservation.getLivre()).thenReturn(this.livre);
        doNothing().when(this.reservationServiceSpy).saveReservation(this.reservation);
        when(this.reservationServiceSpy.numberOfReservations(any(Livre.class))).thenReturn(1);
        when(this.reservationServiceSpy.numberOfReservationsDateLimitNull(any(Livre.class))).thenReturn(1);
        doNothing().when(this.reservationServiceSpy).setDateLimitIfReservation(any(Livre.class),any(Boolean.class));
        this.reservationServiceSpy.delete(this.reservation);
        verify(this.reservationServiceSpy, times(1)).setActif(any(Reservation.class));
        verify(this.reservationServiceSpy, times(1)).saveReservation(any(Reservation.class));
        verify(this.reservationServiceSpy, times(1)).isStillReservations(any(Livre.class));
        verify(this.reservationServiceSpy, times(1)).setDateLimitIfReservation(any(Livre.class),any(Boolean.class));
        verify(this.reservationServiceSpy, times(1)).numberOfReservations(any(Livre.class));
        verify(this.reservationServiceSpy, times(1)).sendEmailForReservation(any(Reservation.class),any(Boolean.class));
        verify(this.livreService, times(1)).rendreLivreIfNoReservations(any(Livre.class),any(Boolean.class));
        verify(this.livreService, times(1)).setReservableNumberOfReservations(any(Livre.class),any(int.class));
        verify(this.reservationServiceSpy, times(1)).conversionToDTO(any(Reservation.class));
    }

    @Test
    @DisplayName("Test de la méthode delete")
    @Order(13)
    //@Sql({"file:db/delete_reservations.sql", "file:db/reservations_test_findAndDelete.sql" })
    void checkFindAndDelete2(){
        when(this.reservation.getDateLimit()).thenReturn(null);
        when(this.reservation.getLivre()).thenReturn(this.livre);
        doNothing().when(this.reservationServiceSpy).saveReservation(this.reservation);
        this.reservationServiceSpy.delete(this.reservation);
        verify(this.reservationServiceSpy, times(1)).delete(any(Reservation.class));
        verify(this.reservationServiceSpy, times(1)).setActif(any(Reservation.class));
        verify(this.reservationServiceSpy, times(1)).saveReservation(any(Reservation.class));
        verify(this.reservationServiceSpy, times(0)).isStillReservations(any(Livre.class));
        verify(this.reservationServiceSpy, times(0)).setDateLimitIfReservation(any(Livre.class),any(Boolean.class));
        verify(this.reservationServiceSpy, times(0)).numberOfReservations(any(Livre.class));
        verify(this.reservationServiceSpy, times(0)).sendEmailForReservation(any(Reservation.class),any(Boolean.class));
        verify(this.livreService, times(0)).rendreLivreIfNoReservations(any(Livre.class),any(Boolean.class));
        verify(this.livreService, times(0)).setReservableNumberOfReservations(any(Livre.class),any(int.class));
        verify(this.reservationServiceSpy, times(1)).conversionToDTO(any(Reservation.class));
    }*/

/*    @Test
    @DisplayName("Test de la méthode delete")
    @Order(12)
    @Sql({"file:db/all_reservations.sql", "file:db/reservations_test_findAndDelete.sql" })
    void checkFindAndDelete2(){
        Reservation reservation = this.reservationService.findById(8L);
        assertThat(reservation.getActif()).isEqualTo(true);
        this.reservationService.findAndDelete(8L);
        reservation = this.reservationService.findById(8L);
        assertThat(reservation.getActif()).isEqualTo(true);
    }*/

/*    @Test
    //@Sql({ "file:db/delete_all_tables.sql", "file:db/init_auteur_livre_usager_exemplaire.sql", "file:db/data_for_delete_reservationB.sql" })
    @Sql("file:db/init_auteur_livre_usager_exemplaire.sql")
    @DisplayName("Test sur la fonctionnalité: Supprimer une réservation avec une autre réservation active")
    @Order(4)
    void checkDeleteReservationAB() throws ReservationException {
        //when(reservationServiceSpy.numberOfReservationsDateLimitNull(any(Livre.class))).thenReturn(0);
*//*        Reservation reservation1 = reservationServiceSpy.findById(15L);
        assertThat(reservation1.getDateLimit()).isNull();
        Livre livre = this.livreService.findById(3L);
        int nbreExemplairesBefore = livre.getNbreExemplaires();
        ReservationDTO reservationDTO = reservationServiceSpy.findAndDelete(14L);
        livre = this.livreService.findById(3L);
        reservation1 = reservationServiceSpy.findById(15L);
        assertThat(reservation1.getDateLimit()).isNotNull();
        int nbreExemplairesAfter = livre.getNbreExemplaires();
        assertThat(reservationDTO.getActif()).isEqualTo(false);
        assertThat(nbreExemplairesAfter).isEqualTo(nbreExemplairesBefore);*//*
        System.out.println(usagerService.getAll().size());
        System.out.println(livreService.getAllLivres().size());
        //System.out.println(reservationService.getAllReservations().size());
    }

	@Test
	@Sql({ "file:db/delete_reservations.sql", "file:db/data_for_delete_reservationB.sql" })
	@DisplayName("Supprime une réservation sans autre réservation active")
    @Order(5)
	void checkDeleteReservationBB() throws ReservationException {
		//Reservation reservation = this.reservationService.findById(14L);
*//*		Livre livre = this.livreService.findById(3L);
		int nbreExemplairesBefore = livre.getNbreExemplaires();
		ReservationDTO reservationDTO = this.reservationService.findAndDelete(14L);
		assertThat(this.reservationService.isStillReservations(livre)).isEqualTo(false);
		livre = this.livreService.findById(3L);
		int nbreExemplairesAfter = livre.getNbreExemplaires();
		assertThat(reservationDTO.getActif()).isEqualTo(false);
		assertThat(nbreExemplairesAfter).isEqualTo(nbreExemplairesBefore + 1);*//*
        //doNothing().when(emailService).sendMailIfStillReservations(any(Reservation.class),any(Boolean.class));
        doNothing().when(reservationServiceSpy).sendEmailForReservation(any(Reservation.class),any(Boolean.class));
        ReservationDTO reservationDTO = this.reservationServiceSpy.findAndDelete(14L);
	}

	@Test
	@Sql({ "file:db/delete_reservations.sql", "file:db/reservations_date_limit_depasse.sql" })
    @Order(6)
	void checkDateLimitDepasseCB(){
*//*		when(this.reservationServiceSpy.getReservationDateLimitDepasse()).thenReturn(new ArrayList<>());
		System.out.println(this.reservationServiceSpy.getReservationDateLimitDepasse().size());*//*
        System.out.println(usagerService.getAll().size());
        System.out.println(livreService.getAllLivres().size());
        //System.out.println(reservationService.getAllReservations().size());
	}*/
}
