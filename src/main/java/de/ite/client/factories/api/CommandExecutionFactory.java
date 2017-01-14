package de.ite.client.factories.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecution;

/**
 * This abstract class is the factory class for the respective executing
 * objects.
 * 
 * @author stanislawfreund
 *
 */
public abstract class CommandExecutionFactory {

	private ICommandExecution commandExecution;

	public CommandExecutionFactory(CloseableHttpClient client) {
		this.commandExecution = getCommandExection(client);
	}

	/**
	 * This method is defining the executing object for the HTTP request. Be it
	 * a HTTP POST, GET or DELETE.
	 * 
	 * @param client
	 *            The HTTP client for the execution of the HTTP request.
	 * @return Returns the defined HTTP request.
	 */
	public abstract ICommandExecution getCommandExection(CloseableHttpClient client);

	/**
	 * This method is delegating the HTTP request to the respective executing
	 * object.
	 * 
	 * @param path
	 *            URL path for the request.
	 * @param input
	 *            StringEntity with the JSON.
	 * @return Returns the response from the server.
	 */
	public CloseableHttpResponse executeCommand(String path) {
		return commandExecution.executeCommand(path);
	}

}
