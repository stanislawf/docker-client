package de.hft.client.dockercmds.api;

import java.util.List;

import de.hft.client.model.Image;

public interface IImageCommands {

	public static String IMAGES = "images";

	public List<Image> listImages();

	public String getJsonResponse();

	public int getStatusCode();

}
