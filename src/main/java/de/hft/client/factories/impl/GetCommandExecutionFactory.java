package de.hft.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;
import de.hft.client.cmd.exec.api.ICommandExecutionJson;
import de.hft.client.cmd.exec.impl.GetCommandExecution;
import de.hft.client.factories.api.CommandExecutionFactory;

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
