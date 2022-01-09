package com.example.MetroStationProject.dto;

import com.example.MetroStationProject.enumeration.StationName;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
public class UseMetroDto {

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private StationName stationName;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private StationName destinationStation;
}
