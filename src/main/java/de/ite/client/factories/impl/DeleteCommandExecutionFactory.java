package de.ite.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecution;
import de.ite.client.cmd.exec.impl.DeleteCommandExecution;
import de.ite.client.factories.api.CommandExecutionFactory;

/**
 * This class is the factory class for the executing object of a HTTP DELETE.
 * 
 * @author stanislawfreund
 *
 */
public class DeleteCommandExecutionFactory extends CommandExecutionFactory {

	public DeleteCommandExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecution getCommandExection(CloseableHttpClient client) {
		return new DeleteCommandExecution(client);
	}

}
