package space.besh.beka_back.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "goods_out")
public class GoodsOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Goods good;

    BigDecimal price;

    @Column(name = "quantity_out")
    Long quantityOut;

    String name;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    Consumer consumer;
}
