package de.hft.client.factories.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;
import de.hft.client.cmd.exec.api.ICommandExecutionJson;

public abstract class CommandExecutionFactory {

	private ICommandExecution commandExecution;

	private ICommandExecutionJson commandExecutionJson;

	public CommandExecutionFactory(CloseableHttpClient client) {
		this.commandExecution = getCommandExection(client);
		this.commandExecutionJson = getCommandExectionJson(client);
	}

	public abstract ICommandExecution getCommandExection(CloseableHttpClient client);

	public abstract ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client);

	public CloseableHttpResponse executeCommand(String path) {
		return commandExecution.executeCommand(path);
	}

	public CloseableHttpResponse executeCommandJson(String path, StringEntity input) {
		return commandExecutionJson.executeCommand(path, input);
	}

}
