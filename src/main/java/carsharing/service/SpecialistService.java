package carsharing.service;

import carsharing.dao.model.Specialist;
import carsharing.dao.repository.SpecialistRepository;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;

    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);

    public ArrayList<SpecialistDTO> getAll() {
        return (ArrayList<SpecialistDTO>) specialistMapper.convertToDTO(specialistRepository.findAll());
    }

    public SpecialistDTO findById(Long id) {
        return specialistMapper.convertToDTO(specialistRepository.findById(id));
    }

    public void delete(Long id) {
        specialistRepository.delete(id);
    }

    public void save(Specialist entity) {
        specialistRepository.saveOrUpdate(entity);
    }
}
