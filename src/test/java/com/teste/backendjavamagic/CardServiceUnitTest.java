package com.teste.backendjavamagic;


import com.teste.backendjavamagic.models.CardModel;
import com.teste.backendjavamagic.repositories.CardRepository;
import com.teste.backendjavamagic.services.CardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@WebMvcTest(value = CardService.class)
public class CardServiceUnitTest {



    @MockBean
    CardRepository cardRepository;
    @Autowired
    private CardService service;

    @Test
    public void cardServiceSuccess() {
        CardModel card =new CardModel();
        UUID uuid = UUID.randomUUID();
        card.setId(uuid);
        card.setNome("Machina");
        card.setPreco(BigDecimal.valueOf(100));
        card.setEdicao("ouro");
        card.setFoil(true);
        card.setIdioma("português");
        card.setQtde_semelhantes(1);

        Mockito.when(cardRepository.save(card))
                .thenReturn(card);

        CardModel cardResponse = service.save(card);

        assertEquals(cardResponse,card);
    }

}
