package com.example.MetroStationProject.serviceTest;

import com.example.MetroStationProject.dto.CardDto;
import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.enumeration.UserRole;
import com.example.MetroStationProject.model.Card;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.CardRepository;
import com.example.MetroStationProject.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @InjectMocks
    CardServiceImpl cardServiceImpl;

    @Mock
    CardRepository cardRepository;

    Card card1 = new Card(1L,1L,"12345678",10L);
    User user = new User(1L,"cansu","123456", UserRole.NORMAL);

    @Test
    public void testCreateCard (){

        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        CardDto cardDto = new CardDto();
        cardDto.setUserId(card1.getUserId());
        cardDto.setCardNo(card1.getCardNo());
        cardDto.setBalance(card1.getBalance());

        Card result = Card.builder()
                .id(null)
                .userId(cardDto.getUserId())
                .cardNo(cardDto.getCardNo())
                .balance(cardDto.getBalance())
                .build();

        when(cardRepository.save(result)).thenReturn(result);
        assertEquals(cardServiceImpl.createCard(userDetailsDto,cardDto),result);
    }

    @Test
    public void testGetCard (){
        when(cardRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(card1));
        assertEquals(cardServiceImpl.getCard(1L).getCardNo(),card1.getCardNo());
    }

    @Test
    public void testDeleteCard (){
        UserDetailsDto userDetailsDto = new UserDetailsDto(user);
        Card card1 = new Card(1L,1L,"12345678",10L);

        when(cardRepository.findById(1L)).thenReturn(java.util.Optional.of(card1));
        assertEquals(cardServiceImpl.deleteCard(userDetailsDto,1L),card1);
    }
}
