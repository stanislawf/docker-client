package de.hft.client.dockercmds.api;

import java.util.List;

/**
 * This interface is providing the methods needed for the execution of specific Docker commands.
 */

import de.hft.client.model.Container;

public interface IContainerCommands {

	public static String CONTAINERS = "containers";

	public List<Container> getAllContainers();

	public void createContainer(String imageName, String containerName);

	/**
	 * Starts the container with the given container name.
	 * 
	 * @param containerName
	 *            name of the container which shall be started.
	 */
	public void startContainer(String containerName);

	public void stopContainer(String containerName);

	public void deleteContainer(String containerName);

	public String getJsonResponse();

	public int getStatusCode();

}
