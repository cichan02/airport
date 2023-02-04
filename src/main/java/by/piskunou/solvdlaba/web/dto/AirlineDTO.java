package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onSearch;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirlineDTO {

    @Null(groups = {onCreate.class, onSearch.class}, message = "Id should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotBlank(groups = onCreate.class, message = "Name should be not blank")
    @Size(max = 50, groups = {onCreate.class, onSearch.class}, message = "Name should be less than 50 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @Size(max = 2, groups = onSearch.class, message = "IATA code should be less than 2 characters")
    @Size(min = 2, max = 2, groups = onCreate.class, message = "IATA code length should be 2")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iata;

    @Size(max = 3, groups = onSearch.class, message = "ICAO code should be less than 3 characters")
    @Size(min = 3, max = 3, groups = onCreate.class, message = "ICAO code length should be 3")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icao;

    @NotBlank(groups = onCreate.class, message = "Callsign should be not blank")
    @Size(max = 30, groups = {onCreate.class, onSearch.class}, message = "Callsign should be less than 30 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String callsign;

}
