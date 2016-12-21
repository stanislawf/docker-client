package de.hft.client.dockercmds.impl;

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

import de.hft.client.dockercmds.api.IContainerCommands;
import de.hft.client.factories.api.CommandExecutionFactory;
import de.hft.client.factories.api.CommandExecutionJsonFactory;
import de.hft.client.factories.impl.DeleteCommandExecutionFactory;
import de.hft.client.factories.impl.GetCommandExecutionFactory;
import de.hft.client.factories.impl.PostCommandExecutionFactory;
import de.hft.client.factories.impl.PostCommandJsonExecutionFactory;
import de.hft.client.model.Container;
import de.hft.client.setup.DockerConnection;

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
		String path = CONTAINERS + "/" + containerName + "/start";
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
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopContainer(String containerName) {
		this.client = settings.getHttpConnection();
		commandExecution = new PostCommandExecutionFactory(client);
		String path = CONTAINERS + "/" + containerName + "/stop";
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
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Container> getAllContainers() {
		this.client = settings.getHttpConnection();
		commandExecution = new GetCommandExecutionFactory(client);
		List<Container> containers = new ArrayList<>();
		String path = CONTAINERS + "/json?all=1";
		try {
			CloseableHttpResponse httpResponse = commandExecution.executeCommand(path);
			try {
				statusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					jsonResponse = EntityUtils.toString(httpEntity);
					if (HttpURLConnection.HTTP_OK == statusCode) {
						ObjectMapper mapper = new ObjectMapper();
						JSONArray array = new JSONArray(jsonResponse);
						for (int index = 0; index < array.length(); index++) {
							JSONObject jsonObj = array.getJSONObject(index);
							Container container = mapper.readValue(jsonObj.toString(), Container.class);
							containers.add(container);
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
		return containers;
	}

	@Override
	public void createContainer(String imageName, String containerName) {
		this.client = settings.getHttpConnection();
		commandJSonExecution = new PostCommandJsonExecutionFactory(client);
		String path = CONTAINERS + "/create?name=" + containerName;
		String jsonEntity = "{\"Image\":\"" + imageName + "\"}";
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
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteContainer(String containerName) {
		this.client = settings.getHttpConnection();
		commandExecution = new DeleteCommandExecutionFactory(client);
		String path = CONTAINERS + "/" + containerName + "?v=1&force=1";
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
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
