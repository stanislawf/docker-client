package de.hft.stuttgart.dockercmds;

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

import de.hft.stuttgart.factories.CommandExecutionFactory;
import de.hft.stuttgart.factories.GetCommandExecutionFactory;
import de.hft.stuttgart.model.Image;
import de.hft.stuttgart.setup.DockerSettings;

public class ImageCommands implements IImageCommands {

	private CommandExecutionFactory commandExecution;

	private int statusCode = 0;

	private String jsonResponse = "";

	private CloseableHttpClient client;

	private DockerSettings settings;

	public ImageCommands() {
		this.settings = new DockerSettings();
	}

	@Override
	public List<Image> listImages() {
		this.client = settings.getHttpConnection();
		commandExecution = new GetCommandExecutionFactory(client);
		List<Image> images = new ArrayList<>();
		String path = IMAGES + "/json?all=0";
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					jsonResponse = EntityUtils.toString(entity);
					if (HttpURLConnection.HTTP_OK == statusCode) {
						ObjectMapper mapper = new ObjectMapper();
						JSONArray array = new JSONArray(jsonResponse);
						for (int index = 0; index < array.length(); index++) {
							JSONObject jsonObj = array.getJSONObject(index);
							Image image = mapper.readValue(jsonObj.toString(), Image.class);
							images.add(image);
						}
					}
				}
			} finally {
				httpResponse.close();
				client.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
