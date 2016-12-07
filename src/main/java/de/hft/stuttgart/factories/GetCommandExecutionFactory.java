package de.hft.stuttgart.factories;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.stuttgart.command.exec.GetCommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecution;
import de.hft.stuttgart.command.exec.ICommandExecutionJson;

public class GetCommandExecutionFactory extends CommandExecutionFactory {

	public GetCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new GetCommandExecution(client);
	}

	@Override
	public ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client) {
		// TODO Auto-generated method stub
		return null;
	}

}
