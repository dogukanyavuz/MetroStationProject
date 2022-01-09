package com.example.MetroStationProject.service.impl;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.repository.CardRepository;
import com.example.MetroStationProject.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card createCard(UserDetailsDto userDetailsDto, CardDto cardDto) {
        Card card = Card.builder()
                .userId(userDetailsDto.getUser().getId())
                .cardNo(cardDto.cardNo)
                .balance(cardDto.balance)
                .build();
        return this.cardRepository.save(card);
    }

    @Override
    public Card getCard(Long cardId) {
        return this.cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public Card deleteCard(UserDetailsDto userDetailsDto, Long cardId) {
        Card card = this.cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        this.cardRepository.delete(card);
        return card;
    }
}
