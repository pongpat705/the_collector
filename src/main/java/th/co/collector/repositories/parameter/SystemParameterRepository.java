package th.co.collector.repositories.parameter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.co.collector.entities.parameter.SystemParameter;

@Repository
public interface SystemParameterRepository extends CrudRepository<SystemParameter, Long>{
	
	SystemParameter findByParamGroupAndParamCode(String paramGroup, String paramCode);
	List<SystemParameter> findByParamGroupOrderBySortNumber(String paramGroup);

}
