/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springsource.restbucks.training.payment;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.joda.time.Months;
import org.joda.time.Years;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springsource.restbucks.training.AbstractIntegrationTest;

/**
 * Integration tests for {@link CreditCardRepository}.
 * 
 * @author Oliver Gierke
 */
public class CreditCardRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	CreditCardRepository repository;

	@Test
	public void createsCreditCard() {

		CreditCardNumber number = new CreditCardNumber("4321432143214321");
		CreditCard creditCard = new CreditCard(number, "Oliver Gierke", Months.TWELVE, Years.years(2014));

		creditCard = repository.save(creditCard);
		assertThat(repository.findByNumber(number), is(creditCard));
	}
}
