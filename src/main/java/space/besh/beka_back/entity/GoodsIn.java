package space.besh.beka_back.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "goods_in")
public class GoodsIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Goods product;

    @Column(name = "quantity_in")
    Long quantityIn;
    String name;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    Producer producer;

}
