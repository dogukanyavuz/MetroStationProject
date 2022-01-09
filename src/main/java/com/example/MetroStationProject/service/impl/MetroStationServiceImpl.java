package com.example.MetroStationProject.service.impl;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UseMetroDto;
import com.example.MetroStationProject.dto.UseMetroResponseDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.model.Metro;
import com.example.MetroStationProject.repository.CardRepository;
import com.example.MetroStationProject.repository.MetroRepository;
import com.example.MetroStationProject.service.MetroStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MetroStationServiceImpl implements MetroStationService {

    private final MetroRepository metroRepository;
    private final CardRepository cardRepository;

    public MetroStationServiceImpl(MetroRepository metroRepository, CardRepository cardRepository) {
        this.metroRepository = metroRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public UseMetroResponseDto getOn(UserDetailsDto userDetailsDto, Long cardId, UseMetroDto useMetroDto) {
        Metro metro = this.metroRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException());

        long balance = this.cardRepository.findById(cardId).get().balance;
        int stationNumber = 0;

        if (balance >= 5){
            stationNumber = useMetroDto.getDestinationStation().ordinal() - useMetroDto.getStationName().ordinal();
            balance = balance - 5;
        }
        else {
            String message = "Not enough money for using metro";
            log.info(message);
        }

        Card card1 = cardRepository.findById(cardId).get();
        Card card = Card.builder()
                .cardNo(card1.getCardNo())
                .userId(card1.getUserId())
                .balance(balance)
                .build();
        cardRepository.save(card);

        return UseMetroResponseDto.builder()
                .stationNumber(stationNumber)
                .balance(card.getBalance())
                .user(userDetailsDto.getUser())
                .build();
    }

    @Override
    public Card addMoney(CardDto cardDto, Long cardId) {

        Card card = cardRepository.findById(cardId).get();
        Card resultCard = Card.builder()
                .cardNo(card.getCardNo())
                .userId(card.getUserId())
                .balance(cardDto.money)
                .build();
        return resultCard;
    }
}
