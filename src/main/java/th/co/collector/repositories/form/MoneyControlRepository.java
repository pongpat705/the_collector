package th.co.collector.repositories.form;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.collector.entities.moneycontrol.MoneyControl;

@Transactional(rollbackFor = Exception.class)
@Repository
public interface MoneyControlRepository extends PagingAndSortingRepository<MoneyControl, Long> {

	Page<MoneyControl> findByControlType(@Param(value = "controlType") String controlType, Pageable pageable);
}
