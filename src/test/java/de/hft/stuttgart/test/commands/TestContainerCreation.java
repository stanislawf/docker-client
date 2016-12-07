package de.hft.stuttgart.test.commands;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import de.hft.stuttgart.dockercmds.ContainerCommands;
import de.hft.stuttgart.dockercmds.IContainerCommands;

public class TestContainerCreation {

	private static IContainerCommands sut;

	@Before
	public void before() {
		sut = new ContainerCommands();
	}

	@Test
	public void testCreateContainer() {
		sut.createContainer("pgsql", "testName");
		assertThat(HttpURLConnection.HTTP_CREATED, CoreMatchers.is(sut.getStatusCode()));
	}

	@Test
	public void testContainerNameExistsAlready() {
		sut.createContainer("pgsql", "testName");
		assertThat(HttpURLConnection.HTTP_CONFLICT, CoreMatchers.is(sut.getStatusCode()));
	}

	@Test
	public void testImageDoesNotExist() {
		sut.createContainer("xxx", "testName");
		assertThat(HttpURLConnection.HTTP_NOT_FOUND, CoreMatchers.is(sut.getStatusCode()));
	}

	@AfterClass
	public static void afterClass() {
		sut.deleteContainer("testName");
	}

}