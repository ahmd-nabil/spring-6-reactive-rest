package nabil.spring6reactive.mappers;

import nabil.spring6reactive.domain.Beer;
import nabil.spring6reactive.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface BeerMapper {

    BeerDTO BeerToBeerDTO(Beer beer);
    Beer BeerDTOToBeer(BeerDTO beerDTO);
}
