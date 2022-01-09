package com.example.MetroStationProject.service;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UseMetroDto;
import com.example.MetroStationProject.dto.UseMetroResponseDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.model.Card;

public interface MetroStationService {

    UseMetroResponseDto getOn(UserDetailsDto userDetailsDto, Long cardId, UseMetroDto useMetroDto);

    Card addMoney (CardDto cardDto, Long money);


}
