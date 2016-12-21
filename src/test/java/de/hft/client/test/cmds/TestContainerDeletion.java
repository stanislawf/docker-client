package de.hft.client.test.cmds;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import de.hft.client.dockercmds.api.IContainerCommands;
import de.hft.client.dockercmds.impl.ContainerCommands;

public class TestContainerDeletion {

	private IContainerCommands sut;

	@Before
	public void before() {
		sut = new ContainerCommands();
		sut.createContainer("pgsql", "containerDelete");
	}

	@Test
	public void testDeleteContainer() {

		sut.deleteContainer("containerDelete");
		assertThat(sut.getStatusCode(), CoreMatchers.is(HttpURLConnection.HTTP_NO_CONTENT));
	}

	@Test
	public void testContainerDoesNotExist() {
		sut.deleteContainer("containerDeleted");
		assertThat(HttpURLConnection.HTTP_NOT_FOUND, CoreMatchers.is(sut.getStatusCode()));
	}

}
