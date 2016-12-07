package de.hft.stuttgart.factories;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.stuttgart.command.exec.DeleteCommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecutionJson;

public class DeleteCommandExecutionFactory extends CommandExecutionFactory {

	public DeleteCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new DeleteCommandExecution(client);
	}

	@Override
	public ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client) {
		return null;
	}

}
