package de.hft.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;
import de.hft.client.cmd.exec.impl.PostCommandExecution;
import de.hft.client.factories.api.CommandExecutionFactory;

public class PostCommandExecutionFactory extends CommandExecutionFactory {

	public PostCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new PostCommandExecution(client);
	}

}
