package nabil.spring6reactive.mappers;

import nabil.spring6reactive.domain.Beer;
import nabil.spring6reactive.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO beerDTO);
}
