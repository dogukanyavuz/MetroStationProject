package com.example.MetroStationProject.dto;

import com.example.MetroStationProject.enumeration.StationName;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
public class UseMetroDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private StationName stationName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StationName destinationStation;
}
