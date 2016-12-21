package de.hft.client.test.cmds;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import de.hft.client.dockercmds.api.IContainerCommands;
import de.hft.client.dockercmds.impl.ContainerCommands;

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
