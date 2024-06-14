package space.besh.beka_back.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "consumer")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
