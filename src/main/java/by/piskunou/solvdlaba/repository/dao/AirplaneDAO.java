package by.piskunou.solvdlaba.repository.dao;

import by.piskunou.solvdlaba.domain.Airplane;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AirplaneDAO {
    public void save(Airplane airplane) {}

    public Optional<Airplane> findById(long id) {
        return Optional.empty();
    }

    public Optional<Airplane> findByModel(String model) {
        return Optional.empty();
    }

    public void removeById(long id) {}

    public void removeByModel(String model) {}
}