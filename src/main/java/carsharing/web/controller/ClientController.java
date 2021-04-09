package carsharing.web.controller;

import carsharing.service.ClientService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.ClientDTO;
import carsharing.web.mapper.ClientMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SpecialistService specialistService;

    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);


    @GetMapping(value = "/list")
    public List<ClientDTO> getClientList() {
        return clientMapper.convertToDTO(clientService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public ClientDTO getClientById(@PathVariable("id") Long id) {
        return clientMapper.convertToDTO(clientService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.delete(id);
        return "Client deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createClient(@RequestBody ClientDTO clientDTO) {
        clientDTO.setSpecialist_id(specialistService.getById(1L));
        clientService.create(clientMapper.convertToEntity(clientDTO));
        return "Client created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateClient(@RequestBody ClientDTO clientDTO) {
        clientService.update(clientMapper.convertToEntity(clientDTO));
        return "Client updated";
    }

}