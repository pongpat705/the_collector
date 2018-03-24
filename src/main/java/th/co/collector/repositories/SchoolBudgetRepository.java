package th.co.collector.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.moneycontrol.SchoolBudget;

@Repository
public interface SchoolBudgetRepository extends PagingAndSortingRepository<SchoolBudget, Long> {

}
