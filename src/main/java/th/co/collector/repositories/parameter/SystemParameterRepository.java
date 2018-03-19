package th.co.collector.repositories.parameter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.co.collector.entities.parameter.SystemParameter;

public interface SystemParameterRepository extends CrudRepository<SystemParameter, Long>{
	
	SystemParameter findByParamGroupAndParamCode(String paramGroup, String paramCode);
	List<SystemParameter> findByParamGroup(String paramGroup);

}
