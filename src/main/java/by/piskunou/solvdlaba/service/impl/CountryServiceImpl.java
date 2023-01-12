package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreatedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.persistent.impl.CountryRepositoryImpl;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepositoryImpl countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryCities(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        return countryRepository.findCountryCities(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }

    @Override
    public boolean isExists(long id) {
        return countryRepository.findById(id)
                                .isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryAirports(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        return countryRepository.findCountryAirports(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        return countryRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("There's no country with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(String name) {
        return countryRepository.findByName(name)
                                .orElseThrow(() -> new ResourceNotFoundException("There's no country with such name"));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        if(isExists(country.getName())) {
            throw new ResourceNotCreatedException("Country with such name has already exists");
        }

        Long id = countryRepository.create(country)
                                   .orElseThrow(() -> new ResourceNotFoundException("Server error"));
        country.setId(id);

        return country;
    }

    @Override
    public boolean isExists(String name) {
        return countryRepository.findByName(name)
                                .isPresent();
    }

    @Override
    @Transactional
    public Country updateNameById(long id, String name) {
        if(!isExists(id)){
            throw new ResourceNotFoundException("There's no country with such id");
        }

        if(isExists(name)) {
            throw new ResourceNotUpdatedException("Country with such name has already exists");
        }

        return countryRepository.updateNameById(id, name)
                                .orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        countryRepository.removeById(id);
    }

}
