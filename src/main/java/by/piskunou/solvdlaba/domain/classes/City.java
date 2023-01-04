package by.piskunou.solvdlaba.domain.classes;

import by.piskunou.solvdlaba.domain.Place;

import java.util.List;

public class City implements Place {
    private long id;
    private String name;
    private List<Airport> airports;
}
