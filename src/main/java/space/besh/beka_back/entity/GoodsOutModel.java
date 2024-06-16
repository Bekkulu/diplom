package space.besh.beka_back.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoodsOutModel extends BaseEntity {
    Long id;
    Long productId;
    BigDecimal price;
    Long quantityOut;
    String name;
    Long consumerId;
}
