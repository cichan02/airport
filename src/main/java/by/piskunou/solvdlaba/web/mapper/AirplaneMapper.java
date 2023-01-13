package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    AirplaneDTO toDTO(Airplane entity);

    Airplane toEntity(AirplaneDTO dto);

}
