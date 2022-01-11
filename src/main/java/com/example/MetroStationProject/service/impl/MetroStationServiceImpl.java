package com.example.MetroStationProject.service.impl;

import com.example.MetroStationProject.dto.*;
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
        Card isCardExist = this.cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException());

        long existingBalance = isCardExist.balance;
        int stationNumber = 0;

        if (existingBalance >= 5){
            stationNumber = Math.abs(useMetroDto.getStationName().ordinal() - useMetroDto.getDestinationStation().ordinal());
            existingBalance = existingBalance - 5;
        }
        else {
            String message = "Not enough money for using metro";
            log.info(message);
        }

        isCardExist.setBalance(existingBalance);
        cardRepository.save(isCardExist);

        return UseMetroResponseDto.builder()
                .stationNumber(stationNumber)
                .balance(existingBalance)
                .user(userDetailsDto.getUser())
                .build();
    }

    @Override
    public Card addMoney(UserDetailsDto userDetailsDto, CardBalanceDto cardBalanceDto, Long cardId, Long money) {

        Card card = cardRepository.findById(cardId).get();
        Card resultCard = Card.builder()
                .id(cardId)
                .cardNo(card.getCardNo())
                .userId(card.getUserId())
                .balance((card.getBalance()+money)-10)
                .build();
        return resultCard;
    }
}
