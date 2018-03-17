package th.co.collector.repositories; 
 
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole; 
 
@Transactional 
@Repository 
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {
 
	Iterable<UserRole> findByUser(User user);
} 