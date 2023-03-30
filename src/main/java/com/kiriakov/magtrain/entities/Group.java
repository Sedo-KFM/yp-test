package com.kiriakov.magtrain.entities;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Group {
    int id;
    @NonNull
    private String name;
    private String description;
    private Map<Integer, Participant> participants = new HashMap<>();
}
