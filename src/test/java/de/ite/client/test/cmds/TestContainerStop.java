package de.ite.client.test.cmds;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import de.ite.client.dockercmds.api.IContainerCommands;
import de.ite.client.dockercmds.impl.ContainerCommands;

public class TestContainerStop {

	private static IContainerCommands sut;

	@Before
	public void before() {
		sut = new ContainerCommands();
	}

	@Test
	public void testStopContainer() {
		sut.startContainer("client1");

		sut.stopContainer("client1");
		assertThat(HttpURLConnection.HTTP_NO_CONTENT, Matchers.is(sut.getStatusCode()));
	}

	@Test
	public void testContainerAlreadyStopped() {
		sut.stopContainer("client1");
		assertThat(HttpURLConnection.HTTP_NOT_MODIFIED, Matchers.is(sut.getStatusCode()));
	}

	@Test
	public void testStopNonExistentContainer() {
		sut.startContainer("client2");
		assertThat(HttpURLConnection.HTTP_NOT_FOUND, Matchers.is(sut.getStatusCode()));
	}

}
