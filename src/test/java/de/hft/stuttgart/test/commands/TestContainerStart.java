package de.hft.stuttgart.test.commands;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import de.hft.client.dockercmds.api.IContainerCommands;
import de.hft.client.dockercmds.impl.ContainerCommands;

public class TestContainerStart {

	private static IContainerCommands sut;

	@Before
	public void before() {
		sut = new ContainerCommands();
	}

	@Test
	public void testStartContainer() {
		sut.startContainer("client1");
		assertThat(HttpURLConnection.HTTP_NO_CONTENT, Matchers.is(sut.getStatusCode()));
	}

	@Test
	public void testContainerAlreadyRunning() {
		sut.startContainer("client1");
		assertThat(HttpURLConnection.HTTP_NOT_MODIFIED, Matchers.is(sut.getStatusCode()));
	}

	@Test
	public void testStartNonExistentContainer() {
		sut.startContainer("client2");
		assertThat(HttpURLConnection.HTTP_NOT_FOUND, Matchers.is(sut.getStatusCode()));
	}

	@AfterClass
	public static void after() {
		sut.stopContainer("client1");
		assertThat(HttpURLConnection.HTTP_NO_CONTENT, Matchers.is(sut.getStatusCode()));
	}

}
