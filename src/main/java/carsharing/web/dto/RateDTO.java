package carsharing.web.dto;

import carsharing.dao.model.Deal;
import carsharing.dao.model.RateType;
import lombok.Data;

import java.util.List;

@Data
public class RateDTO {
    private long id;

    private RateType type;

    private long cost;

    private List<Deal> deals;
}
