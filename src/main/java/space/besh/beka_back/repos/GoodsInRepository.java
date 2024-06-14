package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.besh.beka_back.entity.GoodsIn;

import java.util.List;

public interface GoodsInRepository extends JpaRepository<GoodsIn,Long> {

    @Query("select gi from GoodsIn gi inner join fetch gi.producer g where :#{#goodId} is null or g.id=:goodId order by gi.id desc")
    List<GoodsIn> findAll(Long goodId);
}
