package space.besh.beka_back.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import space.besh.beka_back.entity.Producer;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer,Long> {

    @Query("select p from Producer p where (:#{#companyName} is null or p.companyName ilike (concat('%',:companyName,'%'))) and (:#{#fio} is null or p.fio ilike (concat('%',:fio,'%')))")
    List<Producer> findAll(String companyName, String fio);
}
