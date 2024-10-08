/**
 * Copyright (c) 2003-2017 The Apereo Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://opensource.org/licenses/ecl2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.time.impl.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sakaiproject.time.api.Time;
import org.sakaiproject.time.api.TimeService;
import org.sakaiproject.time.api.TimeRange;
import org.sakaiproject.tool.api.SessionManager;
import org.sakaiproject.time.impl.BasicTimeService;
import org.sakaiproject.time.impl.MyTime;
import org.sakaiproject.time.impl.BasicTimeService.MyTimeRange;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Charles Severance
 */
public class BaseTimeServiceTest {

	private BasicTimeService service;

	@Before
	public void setUp() {
		service = new BasicTimeService();
	}

	@Test
	public void testDurtation() {
		Time ts = new MyTime(service, 100l);
		Time te = new MyTime(service, 142);

		// KNL-1536 The duration remains the same 
		// regardless of included and/or excluded endpoints
		TimeRange tr1 = service.newTimeRange(ts, te, false, false);
		assertEquals(tr1.firstTime().getTime(), 101l);
		assertEquals(tr1.lastTime().getTime(), 141l);
		assertEquals(tr1.duration(),42l);

		tr1 = service.newTimeRange(ts, te, true, false);
		assertEquals(tr1.firstTime().getTime(), 100l);
		assertEquals(tr1.lastTime().getTime(), 141l);
		assertEquals(tr1.duration(),42l);

		tr1 = service.newTimeRange(ts, te, true, true);
		assertEquals(tr1.firstTime().getTime(), 100l);
		assertEquals(tr1.lastTime().getTime(), 142l);
		assertEquals(tr1.duration(),42l);

		tr1 = service.newTimeRange(ts, te, false, true);
		assertEquals(tr1.firstTime().getTime(), 101l);
		assertEquals(tr1.lastTime().getTime(), 142l);
		assertEquals(tr1.duration(),42l);
	}

	@Test
	public void newTimeRange_withValidInstants_shouldReturnCorrectTimeRange() {
		BasicTimeService timeService = new BasicTimeService();
		Instant start = Instant.parse("2023-01-01T00:00:00Z");
		Instant end = Instant.parse("2023-01-02T00:00:00Z");

		TimeRange timeRange = timeService.newTimeRange(start, end);

		assertNotNull(timeRange);
		assertEquals(start.toEpochMilli(), timeRange.firstTime().getTime());
		assertEquals(end.toEpochMilli(), timeRange.lastTime().getTime());

		// The duration should be 1 day
		assertEquals(timeRange.duration(), 86400000);
	}

	@Test
	public void newTimeRange_withStartAfterEnd_shouldSwapTimes() {
		BasicTimeService timeService = new BasicTimeService();
		Instant start = Instant.parse("2023-01-02T00:00:00Z");
		Instant end = Instant.parse("2023-01-01T00:00:00Z");

		TimeRange timeRange = timeService.newTimeRange(start, end);

		assertNotNull(timeRange);
		assertEquals(end.toEpochMilli(), timeRange.firstTime().getTime());
		assertEquals(start.toEpochMilli(), timeRange.lastTime().getTime());
	}

	@Test
	public void newTimeRange_withSameStartAndEnd_shouldReturnSingleTimeRange() {
		BasicTimeService timeService = new BasicTimeService();
		Instant start = Instant.parse("2023-01-01T00:00:00Z");

		TimeRange timeRange = timeService.newTimeRange(start, start);

		assertNotNull(timeRange);
		assertEquals(start.toEpochMilli(), timeRange.firstTime().getTime());
		assertEquals(start.toEpochMilli(), timeRange.lastTime().getTime());
		assertTrue(timeRange.isSingleTime());

		// Duration should be 0
		assertEquals(timeRange.duration(), 0);
	}
}
