package com.kiriakov.magtrain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SimpleParticipant {
    private int id;
    @NonNull
    private String name;
    private String wish;

    public SimpleParticipant(Participant participant) {
        id = participant.getId();
        name = participant.getName();
        wish = participant.getWish();
    }
}
