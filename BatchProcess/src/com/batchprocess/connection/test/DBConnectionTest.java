package com.batchprocess.connection.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.batchprocess.connection.DBConnection;

public class DBConnectionTest {
	Connection c;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Connection c=DBConnection.getConnection();
		assertNotNull(c);
	}
	@After
	public void tearDown() throws Exception{
		//DBConnection.close();
	}
}
