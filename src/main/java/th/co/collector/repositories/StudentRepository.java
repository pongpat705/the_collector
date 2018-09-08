package th.co.collector.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, PagingAndSortingRepository<Student, Long>{

	
	Page<Student> findByActive(@Param(value = "active") char active, Pageable pageable);
	
	Student findByStNatidAndActive(String stNatid, char active);
}
