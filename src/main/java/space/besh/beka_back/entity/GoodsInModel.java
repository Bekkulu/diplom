package space.besh.beka_back.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoodsInModel {

    Long id;
    BigDecimal price;
    Long productId;
    Long quantityIn;
    String name;
    Long producerId;
}
