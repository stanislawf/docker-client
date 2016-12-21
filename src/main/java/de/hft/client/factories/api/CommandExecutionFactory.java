package de.hft.client.factories.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;

public abstract class CommandExecutionFactory {

	private ICommandExecution commandExecution;

	public CommandExecutionFactory(CloseableHttpClient client) {
		this.commandExecution = getCommandExection(client);
	}

	public abstract ICommandExecution getCommandExection(CloseableHttpClient client);

	public CloseableHttpResponse executeCommand(String path) {
		return commandExecution.executeCommand(path);
	}

}
