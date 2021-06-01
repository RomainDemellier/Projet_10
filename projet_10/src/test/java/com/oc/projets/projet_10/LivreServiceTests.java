package com.oc.projets.projet_10;

import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.ProlongationException;
import com.oc.projets.projet_10.exception.ReservationException;
import com.oc.projets.projet_10.exception.UsagerException;
import com.oc.projets.projet_10.repository.ReservationRepository;
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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Testcontainers
@SpringBootTest(classes = Projet7Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class LivreServiceTests {

    @Mock
    Livre livreMock;

    @Mock
    ReservationService reservationService;

    @Autowired
    @Spy
    @InjectMocks
    LivreService livreService;

/*    @Test
    @DisplayName("Teste la méthode setReservableNumberOfReservations avec un nombre de résevations > 2*nbreTotal")
    @Order(1)*/
/*    void checkSetReservableNumberOfReservations1(){
        Livre livre = new Livre();
        livre.setReservable(true);
        livre.setNbreTotal(2);
//        this.livreService.setReservableNumberOfReservations(livre,5);
        assertThat(livre.isReservable()).isEqualTo(false);
    }*/

/*    @Test
    @DisplayName("Teste la méthode setReservableNumberOfReservations avec un nombre de résevations < 2*nbreTotal")
    @Order(2)*/
/*    void checkSetReservableNumberOfReservations2(){
        Livre livre = new Livre();
        livre.setReservable(false);
        livre.setNbreTotal(2);
//        this.livreService.setReservableNumberOfReservations(livre,3);
        assertThat(livre.isReservable()).isEqualTo(true);
    }*/

/*    @Test
    @DisplayName("Teste la méthode setReservableNumberOfReservations avec un nombre de résevations = 2*nbreTotal")
    @Order(3)*/
/*    void checkSetReservableNumberOfReservations3(){
        Livre livre = new Livre();
        livre.setReservable(true);
        livre.setNbreTotal(2);
//        this.livreService.setReservableNumberOfReservations(livre,4);
        assertThat(livre.isReservable()).isEqualTo(false);
    }*/

    @Test
    @DisplayName("Teste la méthode rendreLivreIfNoReservations avec false")
    @Order(4)
    void checkRendreLivreIfNoReservations1(){
        doNothing().when(this.livreService).rendre(any(Livre.class));
        this.livreService.rendreLivreIfNoReservations(livreMock,false);
        verify(this.livreService, times(1)).rendre(livreMock);
    }

    @Test
    @DisplayName("Teste la méthode rendreLivreIfNoReservations avec true")
    @Order(5)
    void checkRendreLivreIfNoReservations2(){
        this.livreService.rendreLivreIfNoReservations(livreMock,true);
        verify(this.livreService, times(0)).rendre(livreMock);
    }
}
