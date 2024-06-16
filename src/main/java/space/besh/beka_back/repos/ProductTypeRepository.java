package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.besh.beka_back.entity.ProductType;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Query("select pt from ProductType pt where (:#{#name} is null or pt.name ilike (concat('%', ?1,'%')))")
    List<ProductType> findAll(String name);

    ProductType findById(int id);
}
