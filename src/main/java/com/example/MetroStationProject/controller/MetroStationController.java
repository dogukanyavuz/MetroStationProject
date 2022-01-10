package com.example.MetroStationProject.controller;

import com.example.MetroStationProject.dto.*;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.service.MetroStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(
        value = "/metro",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class MetroStationController {

    private final MetroStationService metroStationService;

    public MetroStationController(MetroStationService metroStationService) {
        this.metroStationService = metroStationService;
    }

    @PostMapping("/{cardId}")
    private @ResponseBody
    UseMetroResponseDto getOn(@AuthenticationPrincipal UserDetailsDto userDetailsDto,
                              @PathVariable Long cardId,
                              @RequestBody @Valid UseMetroDto useMetroDto) {
        return metroStationService.getOn(userDetailsDto,cardId,useMetroDto);
    }

    @PutMapping("/{cardId}/{money}")
    private @ResponseBody
    Card addMoney(@AuthenticationPrincipal UserDetailsDto userDetailsDto,
                  @PathVariable Long cardId,
                  @PathVariable Long money,
                  @RequestBody @Valid CardBalanceDto cardBalanceDto) {
        return metroStationService.addMoney(userDetailsDto,cardBalanceDto,cardId,money);
    }
}
