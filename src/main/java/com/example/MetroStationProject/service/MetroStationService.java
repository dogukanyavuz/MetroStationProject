package com.example.MetroStationProject.service;

import com.example.MetroStationProject.dto.*;
import com.example.MetroStationProject.model.Card;

public interface MetroStationService {

    UseMetroResponseDto getOn(UserDetailsDto userDetailsDto, Long cardId, UseMetroDto useMetroDto);

    Card addMoney (UserDetailsDto userDetailsDto, CardBalanceDto cardBalanceDto, Long cardId, Long money);


}
