package carsharing.web.dto;

import carsharing.dao.model.Card;
import carsharing.dao.model.Deal;
import carsharing.dao.model.Specialist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private long id;

    private String name;

    private String surname;

    private String email;

    private String passport_number;

    private String phone_number;

    private boolean is_verified;

    private List<Deal> deals;

    private List<Card> cards;

    @JsonIgnore
    private Specialist specialist_id;
}
