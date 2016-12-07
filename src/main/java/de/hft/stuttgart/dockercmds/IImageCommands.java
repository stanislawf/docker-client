package de.hft.stuttgart.dockercmds;

import java.util.List;

import de.hft.stuttgart.model.Image;

public interface IImageCommands {

	public static String IMAGES = "images";

	public List<Image> listImages();

	public String getJsonResponse();

	public int getStatusCode();

}
