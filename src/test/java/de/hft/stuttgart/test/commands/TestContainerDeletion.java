package de.hft.stuttgart.test.commands;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import de.hft.stuttgart.dockercmds.ContainerCommands;
import de.hft.stuttgart.dockercmds.IContainerCommands;

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