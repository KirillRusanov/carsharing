package carsharing.service;

import carsharing.dao.model.Role;
import carsharing.dao.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ArrayList<Role> getAll() {
        return (ArrayList<Role>) roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id);
    }
}
