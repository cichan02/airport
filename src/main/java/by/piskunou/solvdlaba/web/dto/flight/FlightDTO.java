package by.piskunou.solvdlaba.web.dto.flight;

import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import by.piskunou.solvdlaba.web.dto.airplane.AirplaneDTO;
import by.piskunou.solvdlaba.web.dto.AirportDTO;
import by.piskunou.solvdlaba.web.dto.SeatDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightDTO {

    @Null(groups = {onCreate.class, onUpdate.class}, message = "Id should be null")
    private Long id;

    @Null(groups = onUpdate.class, message = "Origin airport should be null")
    @NotNull(groups = onCreate.class, message = "Origin airport should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AirportDTO from;

    @Null(groups = onUpdate.class, message = "Destination airport should null")
    @NotNull(groups = onCreate.class, message = "Destination airport should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AirportDTO to;

    @Null(groups = onUpdate.class, message = "Airplane should null")
    @NotNull(groups = onCreate.class, message = "Airplane should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AirplaneDTO airplane;

    @NotNull(groups = {onCreate.class, onUpdate.class}, message = "Airline should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AirlineDTO airline;

    @NotNull(groups = {onCreate.class, onUpdate.class}, message = "Price should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal price;

    @NotNull(groups = {onCreate.class, onUpdate.class}, message = "Departure time should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future(message = "Departure time should be future")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime departureTime;

    @NotNull(groups = {onCreate.class, onUpdate.class}, message = "Arrival time should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future(message = "Arrival time should be future")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime arrivalTime;

    @Null(groups = {onCreate.class, onUpdate.class}, message = "List of seats should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SeatDTO> seats;

}
