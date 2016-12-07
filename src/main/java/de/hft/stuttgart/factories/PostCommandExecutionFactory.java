package de.hft.stuttgart.factories;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.stuttgart.command.exec.ICommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecutionJson;
import de.hft.stuttgart.command.exec.PostCommandExecution;

public class PostCommandExecutionFactory extends CommandExecutionFactory {

	public PostCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new PostCommandExecution(client);
	}

	@Override
	public ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client) {
		// TODO Auto-generated method stub
		return null;
	}

}
