package com.boundary.camel.component.port;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.boundary.camel.component.common.ServiceStatus;

public class PortInfoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPortInfo() {
		PortResult info = new PortResult();
		assertEquals("check host","localhost",info.getHost());
		assertEquals("check port",7,info.getPort());
		assertEquals("check timeout",5000,info.getTimeout()); 
		assertNull("check port status",info.getPortStatus());
	}

	@Test
	public void testPortStatus() {
		PortResult info = new PortResult();
		
		info.setPortStatus(PortStatus.CONNECTED);
		assertEquals(PortStatus.CONNECTED,info.getPortStatus());
		
		info.setPortStatus(PortStatus.CONNECTION_REFUSED);		
		assertEquals(PortStatus.CONNECTION_REFUSED,info.getPortStatus());

		info.setPortStatus(PortStatus.ERROR);		
		assertEquals(PortStatus.ERROR,info.getPortStatus());
		
		info.setPortStatus(PortStatus.SOCKET_TIMEOUT);		
		assertEquals(PortStatus.SOCKET_TIMEOUT,info.getPortStatus());
		
		info.setPortStatus(PortStatus.UNKNOWN_HOST);		
		assertEquals(PortStatus.UNKNOWN_HOST,info.getPortStatus());
	}

	@Test
	public void testStatus() {
		PortResult info = new PortResult();
		
		info.setStatus(ServiceStatus.FAIL);		
		assertEquals(ServiceStatus.FAIL,info.getStatus());
		
		info.setStatus(ServiceStatus.SUCCESS);		
		assertEquals(ServiceStatus.SUCCESS,info.getStatus());
	}
	
	@Test
	public void testMessage() {
		PortResult info = new PortResult();
		String s = "monkey";
		info.setMessage(s);
		assertEquals("check message",s,info.getMessage());
	}

	@Test
	public void testHost() {
		PortResult info = new PortResult();
		String host = "monkey";
		info.setHost(host);
		assertEquals("check host",host,info.getHost());

	}

	@Test
	public void testPort() {
		PortResult info = new PortResult();
		int n = 99999;
		info.setPort(n);
		assertEquals("check port",n,info.getPort());

	}

	@Test
	public void testToString() {
		PortResult info = new PortResult();
		assertEquals("check toString()","host=localhost,port=7,timeout=5000,portStatus=",info.toString());
	}
}
