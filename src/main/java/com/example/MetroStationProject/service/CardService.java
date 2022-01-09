package com.example.MetroStationProject.service;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.model.Card;

public interface CardService {

    Card createCard(UserDetailsDto userDetailsDto, CardDto cardDto);

    Card getCard(Long cardId);

    Card deleteCard(UserDetailsDto userDetailsDto, Long cardId);

}
