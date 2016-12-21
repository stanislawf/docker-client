package de.hft.client.factories.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecutionJson;

public abstract class CommandExecutionJsonFactory {

	private ICommandExecutionJson commandExecutionJson;

	public CommandExecutionJsonFactory(CloseableHttpClient client) {
		this.commandExecutionJson = getCommandExectionJson(client);
	}

	public abstract ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client);

	public CloseableHttpResponse executeCommandJson(String path, StringEntity input) {
		return commandExecutionJson.executeCommand(path, input);
	}

}
