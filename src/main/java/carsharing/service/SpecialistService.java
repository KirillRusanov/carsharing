package carsharing.service;

import carsharing.dao.model.Specialist;
import carsharing.dao.repository.SpecialistRepository;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;

    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);

    public List<SpecialistDTO> getAll() {
        return specialistMapper.convertToDTO(Streamable.of(specialistRepository.findAll()).toList());
    }

    public SpecialistDTO findById(Long id) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent()) {
            return specialistMapper.convertToDTO(specialist.get());
        } else {
            throw new ServerNotFoundException("Specialist with this ID not found!");
        }
    }

    public void delete(Long id) {
        specialistRepository.deleteById(id);
    }

    public void save(Specialist entity) {
        specialistRepository.save(entity);
    }
}
