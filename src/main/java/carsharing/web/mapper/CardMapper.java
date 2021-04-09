package carsharing.web.mapper;

import carsharing.dao.model.Card;
import carsharing.web.dto.CardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CardMapper {

    CardDTO convertToDTO(Card entity);

    Card convertToEntity(CardDTO cardDTO);

    List<CardDTO> convertToDTO(List<Card> cardsEntities);

    List<Card> convertToEntity(List<CardDTO> cardsDTOs);
}
