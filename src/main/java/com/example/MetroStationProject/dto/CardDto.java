package com.example.MetroStationProject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CardDto {

    @NotEmpty
    @Range(min = 1, max = Integer.MAX_VALUE)
    public String cardNo;

    @NotEmpty
    @Range(min = 10)
    public Long balance;

    @NotEmpty
    public Long money;
}
