package th.co.collector.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.mobilize.MobilizeMaster;

@Repository
public interface MobilizeReporsitory extends PagingAndSortingRepository<MobilizeMaster, Long> {

}
