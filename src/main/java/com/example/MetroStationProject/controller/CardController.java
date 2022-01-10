package com.example.MetroStationProject.controller;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.dto.UserDto;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(
        value = "/cards",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    private @ResponseBody
    Card createCard(@AuthenticationPrincipal UserDetailsDto userDetailsDto,
                    @RequestBody @Valid CardDto cardDto) {
        return cardService.createCard(userDetailsDto,cardDto);
    }

    @GetMapping("/{cardId}")
    private @ResponseBody
    Card getCard(@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @DeleteMapping("/{cardId}")
    private @ResponseBody
    Card deleteCard(@AuthenticationPrincipal UserDetailsDto userDetailsDto, @PathVariable Long cardId) {
        return cardService.deleteCard(userDetailsDto, cardId);
    }
}
