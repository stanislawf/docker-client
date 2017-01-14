package de.ite.client.factories.impl;

import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecutionJson;
import de.ite.client.cmd.exec.impl.PostCommandJsonExecution;
import de.ite.client.factories.api.CommandExecutionJsonFactory;

/**
 * This class is the factory class for the executing object of a HTTP POST,
 * which contains a JSON. a JSON.
 * 
 * @author stanislawfreund
 *
 */
public class PostCommandJsonExecutionFactory extends CommandExecutionJsonFactory {

	public PostCommandJsonExecutionFactory(CloseableHttpClient client) {
		super(client);
	}

	@Override
	public ICommandExecutionJson getCommandExectionJson(CloseableHttpClient client) {
		return new PostCommandJsonExecution(client);
	}

}
