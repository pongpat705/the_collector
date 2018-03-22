package th.co.collector.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.moneycontrol.BalanceMaster;

@Repository
public interface BalanceMasterRepository extends PagingAndSortingRepository<BalanceMaster, Long> {

}
