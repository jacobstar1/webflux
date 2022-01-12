package com.apress.prospringmvc.moneytransfer.setter;

import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedAccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedTransactionRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marten Deinum
 */
@Configuration
public class ApplicationContextConfiguration {

	@Bean
	public AccountRepository accountRepository() {
		return new MapBasedAccountRepository();
	}

	@Bean
	public TransactionRepository transactionRepository() {
		return new MapBasedTransactionRepository();
	}

	@Bean
	public MoneyTransferService moneyTransferService() {
		var transferService = new MoneyTransferServiceImpl();
		transferService.setAccountRepository(accountRepository());
		transferService.setTransactionRepository(transactionRepository());
		return transferService;
	}

}
