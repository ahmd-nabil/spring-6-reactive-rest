package nabil.spring6reactive.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ahmed Nabil
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {
    private Integer id;
    @Size(min = 3, max = 255, message = "beerName must be between 3 and 255 characters")
    private String beerName;

    @Size(min = 3, max = 255)
    private String beerStyle;

    @Size(min = 3, max = 25)
    private String upc;

    @Min(0)
    private Integer quantityOnHand;

    @Min(0)
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}