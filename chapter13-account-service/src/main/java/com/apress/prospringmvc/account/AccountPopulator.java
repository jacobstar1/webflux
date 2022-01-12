/*
Freeware License, some rights reserved

Copyright (c) 2020 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.prospringmvc.account;

import com.apress.prospringmvc.account.document.Account;
import com.apress.prospringmvc.account.document.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static com.apress.prospringmvc.account.document.Account.Authority;

import java.util.List;

/**
 * Created by Iuliana Cosmina on 30/08/2020
 */
@Component
public class AccountPopulator {

	private final Logger logger = LoggerFactory.getLogger(AccountPopulator.class);

	private final AccountRepository accountRepository;

	public AccountPopulator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;

		Address address = new Address();

		address.setStreet("Liberty Street");
		address.setCity("of angels");
		address.setCountry("Europe");

		Account john = new Account();
		john.setFirstName("john");
		john.setUsername("john");
		john.setLastName("doe");
		john.setEmailAddress("john@doe.com");
		john.setPassword("doe");
		//john.setPassword(passwordEncoder.encode("doe"));
		john.setAddress(address);
		john.setRoles(List.of(Authority.ROLE_USER));

		Account jane = new Account();
		jane.setFirstName("jane");
		jane.setLastName("doe");
		jane.setUsername("jane");
		jane.setEmailAddress("jane@doe.com");
		jane.setPassword("doe");
		//jane.setPassword(passwordEncoder.encode("doe"));
		jane.setAddress(address);
		jane.setRoles(List.of(Authority.ROLE_USER, Authority.ROLE_ADMIN));

		Account admin = new Account();
		admin.setFirstName("admin");
		admin.setLastName("admin");
		admin.setUsername("admin");
		admin.setEmailAddress("admin@admin.com");
		admin.setPassword("admin");
		//admin.setPassword(passwordEncoder.encode("admin"));
		admin.setAddress(address);
		admin.setRoles(List.of(Authority.ROLE_ADMIN));

		accountRepository.deleteAll().thenMany(
				accountRepository.saveAll(List.of(john, jane, admin)))
				.thenMany(accountRepository.findAll())
				.subscribe(
						data -> logger.info("found accounts: {}", accountRepository),
						error -> logger.error("" + error),
						() -> logger.info(" -->> done accounts initialization...")
				);
	}

}
