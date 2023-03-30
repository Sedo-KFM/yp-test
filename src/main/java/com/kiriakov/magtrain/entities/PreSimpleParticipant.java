package com.kiriakov.magtrain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PreSimpleParticipant {
    private int id;
    @NonNull
    private String name;
    private String wish;
    private SimpleParticipant recipient;

    public PreSimpleParticipant(Participant participant) {
        id = participant.getId();
        name = participant.getName();
        wish = participant.getWish();
        if (participant.getRecipient() != null) {
            recipient = new SimpleParticipant(participant.getRecipient());
        }
    }
}
