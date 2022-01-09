package com.example.MetroStationProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
public class Metro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public final String route = "Cekmekoy - Uskudar";

    public final double timeBetweenDestinations = 5.0;
}
