package carsharing.service;

import carsharing.dao.model.Rate;
import carsharing.dao.repository.RateRepository;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.web.dto.RateDTO;
import carsharing.web.mapper.RateMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);

    public List<RateDTO> getAll() {
        return rateMapper.convertToDTO(Streamable.of(rateRepository.findAll()).toList());
    }

    public RateDTO findById(Long id) {
        Optional<Rate> rate = rateRepository.findById(id);
        if (rate.isPresent()) {
            return rateMapper.convertToDTO(rate.get());
        } else {
            throw new ServerNotFoundException("Rate doesn't exists!");
        }
    }

    public void delete(Long id) {
        rateRepository.deleteById(id);
    }

    public void save(Rate entity) {
        rateRepository.save(entity);
    }

    public void save(RateDTO rateDTO) {
        rateRepository.save(rateMapper.convertToEntity(rateDTO));
    }
}
