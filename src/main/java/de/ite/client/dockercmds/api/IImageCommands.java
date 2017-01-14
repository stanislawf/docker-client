package de.ite.client.dockercmds.api;

import java.util.List;

import de.ite.client.model.Image;

/**
 * This class is providing all methods which are related to Docker images.
 * 
 * @author stanislawfreund
 *
 */
public interface IImageCommands {

	public static final String GET_ALL_IMAGES = "json?all=0";
	public static final String IMAGES = "images";
	public static final String URL_SEPERATOR = "/";

	/**
	 * This method is returning a list with all Images available on the Docker
	 * daemon.
	 * 
	 * @return List with Docker images.
	 */
	public List<Image> listImages();

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
