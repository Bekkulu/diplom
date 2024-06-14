package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.besh.beka_back.entity.Consumer;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer,Long> {

    @Query("select c from Consumer c where (:#{#phone} is null or c.phone ilike (concat('%',:phone,'%'))) and (:#{#fio} is null or c.name ilike (concat('%',:fio,'%')))")
    List<Consumer> findAll(String phone, String fio);
}
