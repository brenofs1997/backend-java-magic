package com.teste.backendjavamagic.services;

import com.teste.backendjavamagic.models.CardModel;
import com.teste.backendjavamagic.repositories.CardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public CardModel save(CardModel cardModel) {
        return cardRepository.save(cardModel);
    }
}
