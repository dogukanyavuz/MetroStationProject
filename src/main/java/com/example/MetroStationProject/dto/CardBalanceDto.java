package com.example.MetroStationProject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CardBalanceDto {

    @NotNull(message = "this field is not null")
    private Long userId;

    @NotEmpty(message = "this field is not null")
    @Size(min=4, max = 8)
    private String cardNumber;
}
