package by.piskunou.solvdlaba.domain.classes;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    private long id;
    private Airport from;
    private Airport to;
    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    private Airplane airplane;
}
