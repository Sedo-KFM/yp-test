package com.kiriakov.magtrain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SimpleGroup {
    int id;
    @NonNull
    private String name;
    private String description;

    public SimpleGroup(Group group) {
        id = group.getId();
        name = group.getName();
        description = group.getDescription();
    }
}