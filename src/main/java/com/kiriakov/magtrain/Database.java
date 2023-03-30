package com.kiriakov.magtrain;

import com.kiriakov.magtrain.entities.Group;

import java.util.HashMap;
import java.util.Map;


public class Database {
    static Map<Integer, Group> groups;
    private static int nextId = 1;

    static {
        groups = new HashMap<>();
    }

    static int takeNextId() {
        return nextId++;
    }
}
