package th.co.collector.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.TransCode;

@Repository
public interface TransCodeRepository extends CrudRepository<TransCode, Long> {

}
