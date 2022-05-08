package carsharing.service;

import carsharing.dao.model.Role;
import carsharing.dao.repository.RoleRepository;
import carsharing.service.exception.ServerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ArrayList<Role> getAll() {
        return (ArrayList<Role>) roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("This role does not exist!"));
    }

    public Set<Role> getDefaultUserRoles() {
        return Collections.singleton(findById(1L));
    }
}
