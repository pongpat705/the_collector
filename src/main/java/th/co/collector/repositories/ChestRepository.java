package th.co.collector.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.chest.Chest;

@Repository
public interface ChestRepository extends PagingAndSortingRepository<Chest, Long> {
	
	Chest findByAccountCode(String accountCode);

}
