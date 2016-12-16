package de.hft.stuttgart.test.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import de.hft.client.dockercmds.api.IImageCommands;
import de.hft.client.dockercmds.impl.ImageCommands;
import de.hft.client.model.Image;

public class TestImageCommands {

	private IImageCommands sut;

	@Before
	public void before() {
		sut = new ImageCommands();
	}

	@Test
	public void testContainsImages() {
		List<Image> images = sut.listImages();
		List<String> names = new ArrayList<>();
		for (int index = 0; index < images.size(); index++) {
			names.add(images.get(index).getImageName());
		}
		assertThat(sut.getStatusCode(), Matchers.is(HttpURLConnection.HTTP_OK));
		assertThat(names, hasItems("hello-world", "client-template", "apache_loadbalancer",
				"ubuntu", "data_base", "<none>", "pgsql"));
	}

}
