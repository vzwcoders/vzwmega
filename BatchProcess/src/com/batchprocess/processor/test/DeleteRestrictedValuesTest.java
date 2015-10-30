package com.batchprocess.processor.test;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.batchprocess.processor.DeleteRestrictedValues;

public class DeleteRestrictedValuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=FileNotFoundException.class)
	public void test() {
		DeleteRestrictedValues.readFile("test", "invalid","invalidrecname", null,null, "initialTimeFrame","finalTimeFrame",null);
	}

}
