package com.kiriakov.magtrain.entities;

import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class Group {
    int id;
    @NonNull
    private String name;
    private String description;
    private HashMap<Integer, Participant> participants = new HashMap<>();
}
