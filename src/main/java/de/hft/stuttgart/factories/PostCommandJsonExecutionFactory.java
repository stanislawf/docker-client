package de.hft.stuttgart.factories;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.stuttgart.command.exec.ICommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecutionJson;
import de.hft.stuttgart.command.exec.PostCommandJsonExecution;

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
