package de.ite.client.dockercmds.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.ite.client.dockercmds.api.IImageCommands;
import de.ite.client.factories.api.CommandExecutionFactory;
import de.ite.client.factories.impl.GetCommandExecutionFactory;
import de.ite.client.model.Image;
import de.ite.client.setup.DockerConnection;

public class ImageCommands implements IImageCommands {

	private CommandExecutionFactory commandExecution;

	private int statusCode = 0;

	private String jsonResponse = "";

	private CloseableHttpClient client;

	private DockerConnection settings;

	public ImageCommands() {
		this.settings = new DockerConnection();
	}

	@Override
	public List<Image> listImages() {
		this.client = settings.getHttpConnection();
		commandExecution = new GetCommandExecutionFactory(client);
		List<Image> images = new ArrayList<>();
		String path = IMAGES + URL_SEPERATOR + GET_ALL_IMAGES;
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					jsonResponse = EntityUtils.toString(entity);
					if (HttpURLConnection.HTTP_OK == statusCode) {
						ObjectMapper mapper = new ObjectMapper();
						for (Object obj : new JSONArray(jsonResponse)) {
							Image image = mapper.readValue(((JSONObject) obj).toString(), Image.class);
							images.add(image);
						}
					}
				}
			} finally {
				httpResponse.close();
				client.close();
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return images;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String getJsonResponse() {
		return jsonResponse;
	}

}
