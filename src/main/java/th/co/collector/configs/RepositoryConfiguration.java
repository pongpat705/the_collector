package th.co.collector.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import th.co.collector.entities.chest.Chest;
import th.co.collector.entities.mobilize.MobilizeMaster;
import th.co.collector.entities.moneycontrol.BalanceMaster;
import th.co.collector.entities.moneycontrol.CashBook;
import th.co.collector.entities.moneycontrol.MoneyControl;
import th.co.collector.entities.moneycontrol.SchoolBudget;
import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(User.class, UserRole.class, BalanceMaster.class, CashBook.class, MoneyControl.class, SchoolBudget.class, Chest.class, MobilizeMaster.class);
	}
	
}
