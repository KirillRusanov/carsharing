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

import java.util.List;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);

    public List<RateDTO> getAll() {
        return rateMapper.convertToDTO(Streamable.of(rateRepository.findAll()).toList());
    }

    protected Rate findById(Long id) {
        return rateRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("Rate doesn't exists!"));
    }

    public RateDTO getById(Long id) {
        return rateMapper.convertToDTO(findById(id));
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
