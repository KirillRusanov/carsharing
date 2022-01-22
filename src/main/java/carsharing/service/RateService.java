package carsharing.service;

import carsharing.dao.model.Rate;
import carsharing.dao.repository.RateRepository;
import carsharing.web.dto.RateDTO;
import carsharing.web.mapper.RateMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);

    public ArrayList<RateDTO> getAll() {
        return (ArrayList<RateDTO>) rateMapper.convertToDTO(rateRepository.findAll());
    }

    public RateDTO findById(Long id) {
        return rateMapper.convertToDTO(rateRepository.findById(id));
    }

    public void delete(Long id) {
        rateRepository.delete(id);
    }

    public void save(Rate entity) {
        rateRepository.saveOrUpdate(entity);
    }

    public void save(RateDTO rateDTO) {
        rateRepository.saveOrUpdate(rateMapper.convertToEntity(rateDTO));
    }
}
