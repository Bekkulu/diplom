package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import space.besh.beka_back.entity.Goods;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepo extends JpaRepository<Goods, Long> {

    Optional<Goods> findById(long id);

    @Query("select g from Goods g inner join fetch g.productType pt where (:#{#typeId} is null or pt.id=:#{#typeId}) and (:#{#name} is null or lower(g.name) like lower(concat('%',:name,'%')))")
    List<Goods> findAll(Long typeId, String name);
}
