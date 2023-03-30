package com.kiriakov.magtrain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupWithPSPs {
    int id;
    @NonNull
    private String name;
    private String description;
    private Set<PreSimpleParticipant> participants = new HashSet<>();

    public GroupWithPSPs(Group group) {
        id = group.getId();
        name = group.getName();
        description = group.getDescription();
        participants = group
                .getParticipants()
                .values()
                .stream()
                .map(PreSimpleParticipant::new)
                .collect(Collectors.toSet());
    }
}
