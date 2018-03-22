package th.co.collector.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.moneycontrol.CashBook;

@Repository
public interface CashBookRepository extends PagingAndSortingRepository<CashBook, Long> {

}
