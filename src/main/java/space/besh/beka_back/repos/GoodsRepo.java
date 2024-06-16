package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.besh.beka_back.entity.Goods;

import java.util.Optional;

@Repository
public interface GoodsRepo extends JpaRepository<Goods, Long> {

    Optional<Goods> findById(long id);

}
