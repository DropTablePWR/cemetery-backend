package com.droptablepwr.cemetery.model.projection;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

@JsonSerialize(as = GuestInfo.class)
public interface GuestInfo {
    Integer getId();

    LocalDate getBirthDate();

    LocalDate getDeathDate();

    String getFirstName();

    String getLastName();
}
