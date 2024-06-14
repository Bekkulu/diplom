package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.besh.beka_back.entity.GoodsOut;

import java.util.List;

public interface GoodsOutRepository extends JpaRepository<GoodsOut,Long> {

    @Query("select go from GoodsOut go inner join fetch go.good g where :#{#goodId} is null or g.id=:goodId order by go.id desc")
    List<GoodsOut> findAll(Long goodId);
}
