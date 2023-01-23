package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.persistent.PassengerRepository;
import by.piskunou.solvdlaba.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    @Transactional
    public Passenger create(Passenger passenger) {
        passengerRepository.create(passenger);
        return passenger;
    }

    @Override
    public boolean isExists(long id) {
        return passengerRepository.isExistsById(id);
    }

}
