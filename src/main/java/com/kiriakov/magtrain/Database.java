package com.kiriakov.magtrain;

import com.kiriakov.magtrain.entities.Group;
import com.kiriakov.magtrain.entities.Participant;

import java.util.HashMap;


public class Database {
    static HashMap<Integer, Group> groups;
    private static int nextId = 1;

    static {
        groups = new HashMap<>();
    }

    static int takeNextId() {
        return nextId++;
    }
}
