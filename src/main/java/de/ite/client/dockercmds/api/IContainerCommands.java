package de.ite.client.dockercmds.api;

import java.util.List;

import de.ite.client.model.Container;

/**
 * This class is providing all methods which are related to Docker containers.
 * 
 * @author stanislawfreund
 *
 */
public interface IContainerCommands {

	public static final String DELETE = "?v=1&force=1";
	public static final String CREATE = "create?name=";
	public static final String GET_ALL_CONTAINERS = "json?all=1";
	public static final String STOP = "stop";
	public static final String START = "start";
	public static final String CONTAINERS = "containers";
	public static final String URL_SEPERATOR = "/";

	/**
	 * This method is returning a list with all Container available on the
	 * Docker daemon.
	 * 
	 * @return List with Docker Container.
	 */
	public List<Container> getAllContainers();

	/**
	 * This method is responsible for the creation of Docker containers, based
	 * on a given image and to expose the container to given port.
	 * 
	 * @param imageName
	 *            Image name the container shall be created from.
	 * @param containerName
	 *            Name of the container.
	 * @param port
	 *            Port which can be used to access the container.
	 */
	public void createContainer(String imageName, String containerName, String port);

	/**
	 * Starts the container with the given container name.
	 * 
	 * @param containerName
	 *            name of the container which shall be started.
	 */
	public void startContainer(String containerName);

	/**
	 * Stops the container with the given container name.
	 * 
	 * @param containerName
	 *            name of the container which shall be stopped.
	 */
	public void stopContainer(String containerName);

	/**
	 * Deletes the container with the given container name.
	 * 
	 * @param containerName
	 *            name of the container which shall be deleted.
	 */
	public void deleteContainer(String containerName);

	/**
	 * This method returns the JsonResponse from the HTTP request.
	 * 
	 * @return JSON as String.
	 */
	public String getJsonResponse();

	/**
	 * This method returns the Status Code of the HTTP request from the Server.
	 * 
	 * @return Returns the Status of the request.
	 */
	public int getStatusCode();

}
