package com.kiriakov.magtrain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Participant {
    private int id;
    @NonNull
    private String name;
    private String wish;
    private Participant recipient;
}
