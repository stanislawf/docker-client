package de.hft.stuttgart.factories;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.stuttgart.command.exec.ICommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecutionJson;

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
