package com.example.MetroStationProject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CardDto {

    @NotNull(message = "this field is not null")
    private Long userId;

    @NotEmpty
    @Range(min = 1, max = Integer.MAX_VALUE)
    public String cardNo;

    @NotNull(message = "this field is not null")
    private Long balance;
}
