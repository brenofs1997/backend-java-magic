package com.teste.backendjavamagic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.backendjavamagic.controllers.CardController;
import com.teste.backendjavamagic.dtos.CardDto;
import com.teste.backendjavamagic.models.CardModel;
import com.teste.backendjavamagic.repositories.CardRepository;
import com.teste.backendjavamagic.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CardController.class)
public class CardControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CardRepository cardRepository;
    @MockBean
    private CardService service ;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String CARD_DTO_JSON ="{\"edicao\":\"ouro\",\n" +
            "    \"foil\":true,\n" +
            "    \"idioma\":\"português\",\n" +
            "    \"nome\" :\"Mago\",\n" +
            "    \"preco\":20,\n" +
            "    \"qtde_semelhantes\": 1}";
    @BeforeEach
    public void setUp() {
        service = new CardService(cardRepository);
    }

    @Test
    public void postCardDTOReturnsCorrectJson() throws Exception {
        CardDto cardDto =Mockito.mock(CardDto.class);
        CardModel card =new CardModel();
        UUID uuid = UUID.randomUUID();
        card.setId(uuid);
        card.setNome("Machina");
        card.setPreco(BigDecimal.valueOf(100));
        card.setEdicao("ouro");
        card.setFoil(true);
        card.setIdioma("português");
        card.setQtde_semelhantes(1);

        when(cardRepository.save(card)).thenReturn(card);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/card")
                .accept(MediaType.APPLICATION_JSON).content(CARD_DTO_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }




}
