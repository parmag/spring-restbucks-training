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
package org.springsource.restbucks.training.order;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springsource.restbucks.training.core.MonetaryAmount.*;
import static org.springsource.restbucks.training.order.Order.Status.*;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springsource.restbucks.training.AbstractIntegrationTest;
import org.springsource.restbucks.training.core.MonetaryAmount;

/**
 * Integration tests for Spring Data based {@link OrderRepository}.
 * 
 * @author Oliver Gierke
 */
public class OrderRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	OrderRepository repository;

	@Test
	public void findsAllOrders() {

		Iterable<Order> orders = repository.findAll();
		assertThat(orders, is(Matchers.<Order> iterableWithSize(2)));
	}

	@Test
	public void createsNewOrder() {

		Order order = repository.save(new Order(new Item("English breakfast", new MonetaryAmount(EURO, 2.70))));

		Iterable<Order> result = repository.findAll();
		assertThat(result, is(Matchers.<Order> iterableWithSize(3)));
		assertThat(result, hasItem(order));
	}

	@Test
	public void findsOrderByStatus() {

		List<Order> result = repository.findByStatus(PAYMENT_EXPECTED);
		assertThat(result, hasSize(2));

		Order order = result.get(0);
		order.markPaid();
		order = repository.save(order);

		assertThat(repository.findByStatus(PAYMENT_EXPECTED), hasSize(1));
		assertThat(repository.findByStatus(PAID), hasSize(1));
	}

	// TODO-01.02: Implement test case for pagination
	@Test
	public void paginatesCorrectly() {

	}

	// TODO-02.02: Implement test case for predicate execution
	@Test
	public void findsOrdersByPredicate() {

	}

	// TODO-03.02: Implement test case for custom implementation
	@Test
	public void executesCustomImplementationsCorrectly() {

	}
}
