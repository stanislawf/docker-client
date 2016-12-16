package de.hft.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;
import de.hft.client.cmd.exec.api.ICommandExecutionJson;
import de.hft.client.cmd.exec.impl.PostCommandJsonExecution;
import de.hft.client.factories.api.CommandExecutionFactory;

public class PostCommandJsonExecutionFactory extends CommandExecutionFactory {

	public PostCommandJsonExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client) {
		return new PostCommandJsonExecution(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return null;
	}
}
