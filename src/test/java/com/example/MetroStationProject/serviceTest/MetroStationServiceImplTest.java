package com.example.MetroStationProject.serviceTest;

import com.example.MetroStationProject.dto.CardBalanceDto;
import com.example.MetroStationProject.dto.UseMetroDto;
import com.example.MetroStationProject.dto.UseMetroResponseDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.enumeration.StationName;
import com.example.MetroStationProject.enumeration.UserRole;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.model.Metro;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.CardRepository;
import com.example.MetroStationProject.repository.MetroRepository;
import com.example.MetroStationProject.service.impl.MetroStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MetroStationServiceImplTest {

    @InjectMocks
    MetroStationServiceImpl metroStationService;

    @Mock
    MetroRepository metroRepository;

    @Mock
    CardRepository cardRepository;

    @Mock
    UserDetailsService userDetailsService;

    Metro metro = new Metro(1l);

    Card card = new Card(1l,1l,"123456",10l);

    User user = new User(1L, "dogukan", "123456", UserRole.NORMAL);

    @Test
    public void testGetOn() throws Exception{

        UseMetroDto useMetroDto = new UseMetroDto();

        useMetroDto.setStationName(StationName.ALTINSEHIR);
        useMetroDto.setDestinationStation(StationName.BAGLARBASI);

        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        Card card1 = Card.builder()
                .id(2l)
                .userId(2l)
                .cardNo("1234567")
                .balance(20l)
                .build();

        long existingBalance = card1.balance;
        int stationNumber = 0;

        if (existingBalance >= 5l){
            stationNumber = Math.abs(useMetroDto.getStationName().ordinal() - useMetroDto.getDestinationStation().ordinal());
            existingBalance = existingBalance - 5;
        }

        UseMetroResponseDto useMetroResponseDto = UseMetroResponseDto.builder()
                .stationNumber(stationNumber)
                .balance(existingBalance)
                .user(user)
                .build();

       when(cardRepository.findById(card1.getId())).thenReturn(Optional.ofNullable(card1));
       assertEquals(metroStationService.getOn(userDetailsDto,card1.getId(),useMetroDto),useMetroResponseDto);
    }

    @Test
    public void testAddMoney(){

        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        CardBalanceDto cardBalanceDto = new CardBalanceDto();

        Long money = 60l;

        Card card1 = Card.builder()
                .id(card.getId())
                .userId(card.getUserId())
                .cardNo(card.getCardNo())
                .balance(card.getBalance()+money)
                .build();

        when(cardRepository.findById(card.getId())).thenReturn(Optional.ofNullable(card));
        assertEquals(metroStationService.addMoney(userDetailsDto,cardBalanceDto,card.getId(),card1.getBalance()),card1);
    }
}
