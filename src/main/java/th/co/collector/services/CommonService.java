package th.co.collector.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.collector.entities.TransCode;
import th.co.collector.repositories.TransCodeRepository;

@Service
public class CommonService {

	@Autowired
	TransCodeRepository codeRepository;
	
	@Transactional
	public TransCode genTransCode(String controlType) {
		Long number = codeRepository.count();
		Integer countNumber = number.intValue()+1;
		String transCode = controlType+String.valueOf(countNumber);
		TransCode newCode = new TransCode();
		newCode.setCode(transCode);
		newCode.setCreateDate(new Date());
		
		codeRepository.save(newCode);
		
		return newCode;
	}
}
