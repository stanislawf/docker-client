package de.hft.stuttgart.dockercmds;

import java.util.List;

import de.hft.stuttgart.model.Container;

public interface IContainerCommands {

	public static String CONTAINERS = "containers";

	public List<Container> getAllContainers();

	public void createContainer(String imageName, String containerName);

	public void startContainer(String containerName);

	public void stopContainer(String containerName);

	public void deleteContainer(String containerName);

	public String getJsonResponse();

	public int getStatusCode();


}
