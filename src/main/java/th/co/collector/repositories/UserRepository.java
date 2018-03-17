package th.co.collector.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.collector.entities.user.User;

@Transactional(rollbackFor = Exception.class)
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByUserName(@Param(value = "userName") String userName);
	
	User findByUserNameAndPassword(@Param(value = "userName") String userName, @Param(value = "password") String password);
	
	Page<List<User>> findByUserNameContaining(@Param(value = "userName") String userName, Pageable pageable);
	
	@Query(value=" SELECT * FROM user u inner join user_role ur on u.user_id = ur.user_id WHERE ur.role = :role ORDER BY ?#{#pageable} ",
			countQuery = " SELECT count(*) FROM user u inner join user_role ur on u.user_id = ur.user_id WHERE ur.role = :role ",
			nativeQuery = true)
	Page<List<User>> findByRole(@Param(value = "role") String role, Pageable pageable);
	
}
