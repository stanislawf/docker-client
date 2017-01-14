package de.ite.client.dockercmds.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.ite.client.dockercmds.api.IContainerCommands;
import de.ite.client.factories.api.CommandExecutionFactory;
import de.ite.client.factories.api.CommandExecutionJsonFactory;
import de.ite.client.factories.impl.DeleteCommandExecutionFactory;
import de.ite.client.factories.impl.GetCommandExecutionFactory;
import de.ite.client.factories.impl.PostCommandExecutionFactory;
import de.ite.client.factories.impl.PostCommandJsonExecutionFactory;
import de.ite.client.model.Container;
import de.ite.client.setup.DockerConnection;

public class ContainerCommands implements IContainerCommands {

	private CommandExecutionFactory commandExecution;

	private CommandExecutionJsonFactory commandJSonExecution;

	private int statusCode = 0;

	private String jsonResponse = "";

	private CloseableHttpClient client;

	private DockerConnection settings;

	public ContainerCommands() {
		this.settings = new DockerConnection();
	}

	@Override
	public void startContainer(String containerName) {
		this.client = settings.getHttpConnection();
		commandExecution = new PostCommandExecutionFactory(client);
		String path = CONTAINERS + URL_SEPERATOR + containerName + URL_SEPERATOR + START;
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
				}
			} finally {
				client.close();
				httpResponse.close();
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopContainer(String containerName) {
		this.client = settings.getHttpConnection();
		commandExecution = new PostCommandExecutionFactory(client);
		String path = CONTAINERS + URL_SEPERATOR + containerName + URL_SEPERATOR + STOP;
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
				}
			} finally {
				client.close();
				httpResponse.close();
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Container> getAllContainers() {
		this.client = settings.getHttpConnection();
		commandExecution = new GetCommandExecutionFactory(client);
		List<Container> containers = new ArrayList<>();
		String path = CONTAINERS + URL_SEPERATOR + GET_ALL_CONTAINERS;
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
					if (HttpURLConnection.HTTP_OK == statusCode) {
						ObjectMapper mapper = new ObjectMapper();
						for (Object obj : new JSONArray(jsonResponse)) {
							Container container = mapper.readValue(((JSONObject) obj).toString(), Container.class);
							containers.add(container);
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
		return containers;
	}

	@Override
	public void createContainer(String imageName, String containerName, String port) {
		this.client = settings.getHttpConnection();
		commandJSonExecution = new PostCommandJsonExecutionFactory(client);
		String path = CONTAINERS + URL_SEPERATOR + CREATE + containerName;
		String jsonEntity = "{\"Image\":\"" + imageName + "\", \"ExposedPorts\":{\"" + port
				+ "/tcp\":{}}, \"PortBindings\":{\"" + port + "/tcp\":[{\"HostPort\":\"" + port + "\"}]}}";
		try {
			StringEntity stringEntity = new StringEntity(jsonEntity);
			CloseableHttpResponse httpResponse = commandJSonExecution.executeCommandJson(path, stringEntity);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
				}
			} finally {
				httpResponse.close();
				client.close();
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteContainer(String containerName) {
		this.client = settings.getHttpConnection();
		commandExecution = new DeleteCommandExecutionFactory(client);
		String path = CONTAINERS + URL_SEPERATOR + containerName + DELETE;
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
				}
			} finally {
				httpResponse.close();
				client.close();
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getJsonResponse() {
		return jsonResponse;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

}
