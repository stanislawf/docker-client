package de.ite.client.test.cmds;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.ite.client.dockercmds.api.IContainerCommands;
import de.ite.client.dockercmds.impl.ContainerCommands;
import de.ite.client.model.Container;

public class TestGetAllContainer {

	private IContainerCommands sut;

	@Before
	public void before() {
		sut = new ContainerCommands();
	}

	@Ignore
	@Test
	public void testContainsAllContainer() {
		List<Container> containers = sut.getAllContainers();
		List<String> names = new ArrayList<>();
		for (int index = 0; index < containers.size(); index++) {
			names.add(containers.get(index).getContainerName());
		}

		assertThat(HttpURLConnection.HTTP_OK, Matchers.is(sut.getStatusCode()));
		assertThat(names, hasItems("sleepy_heyrovsky", "boring_bohr", "client1", "apache",
				"apache_lb2", "pgsql_container", "tender_keller", "Production-Environment", "Testing-Environment"));
	}

}
