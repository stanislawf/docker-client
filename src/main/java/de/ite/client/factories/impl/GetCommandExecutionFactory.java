package de.ite.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecution;
import de.ite.client.cmd.exec.impl.GetCommandExecution;
import de.ite.client.factories.api.CommandExecutionFactory;

/**
 * This class is the factory class for the executing object of a HTTP GET.
 * 
 * @author stanislawfreund
 *
 */
public class GetCommandExecutionFactory extends CommandExecutionFactory {

	public GetCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new GetCommandExecution(client);
	}

}
