package com.teste.backendjavamagic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.backendjavamagic.controllers.CardController;
import com.teste.backendjavamagic.dtos.CardDto;
import com.teste.backendjavamagic.models.CardModel;
import com.teste.backendjavamagic.repositories.CardRepository;
import com.teste.backendjavamagic.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
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

    /*   Mockito.when(card.setId(uuid));
        Mockito.when(cardDto.getNome()).thenReturn("Machina");
        Mockito.when(cardDto.getPreco()).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(cardDto.getEdicao()).thenReturn("ouro");
        Mockito.when(cardDto.getFoil()).thenReturn(true);
        Mockito.when(cardDto.getIdioma()).thenReturn("português");
        Mockito.when(cardDto.getQtde_semelhantes()).thenReturn(1);*/

      // BeanUtils.copyProperties(cardDto,card);
        Mockito.when(cardRepository.save(card)).thenReturn(card);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/card")
                .accept(MediaType.APPLICATION_JSON).content(CARD_DTO_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}
