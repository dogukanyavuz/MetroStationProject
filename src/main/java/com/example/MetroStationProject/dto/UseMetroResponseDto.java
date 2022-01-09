package com.example.MetroStationProject.dto;

import com.example.MetroStationProject.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UseMetroResponseDto {

    private int stationNumber;

    private User user;

    private Long balance;
}
