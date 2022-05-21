package com.teste.backendjavamagic.controllers;

import com.teste.backendjavamagic.dtos.CardDto;
import com.teste.backendjavamagic.models.CardModel;
import com.teste.backendjavamagic.services.CardService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/card")
public class CardController {
    final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCard(@RequestBody @Valid CardDto cardDto){

        var cardModel = new CardModel();
        BeanUtils.copyProperties(cardDto, cardModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(cardModel));
    }

    @GetMapping
    public ResponseEntity<Page<CardModel>> getAllCards(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                    Pageable pageable){
                          return ResponseEntity.status(HttpStatus.OK).body(cardService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCardByID(@PathVariable(value = "id") UUID id){
        Optional<CardModel> cardModelOptional = cardService.findById(id);
        if (!cardModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cardModelOptional.get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCard(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CardDto cardDto){
        Optional<CardModel> cardModelOptional = cardService.findById(id);
        if (!cardModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }
        var cardModel = new CardModel();
        BeanUtils.copyProperties(cardDto, cardModel);
        cardModel.setId(cardModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(cardService.save(cardModel));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable(value = "id") UUID id){
        Optional<CardModel> cardModelOptional = cardService.findById(id);
        if (!cardModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }
        cardService.delete(cardModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully.");
    }



}
