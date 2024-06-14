package space.besh.beka_back.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    BigDecimal price;

    Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
