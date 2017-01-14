package de.ite.client.cmd.exec.api;

import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * This interfaces provides the method for the execution of the respective HTTP
 * requests. The concrete implementations of this interfaces are defining the
 * strategy of the HTTP requests.
 * 
 * @author stanislawfreund
 *
 */
public interface ICommandExecution {

	static String ADDRESS = "https://192.168.99.100:2376";

	public static String URL_SEPERATOR = "/";

	/**
	 * This method is executing the HTTP request to the given URL path.
	 * 
	 * @param path
	 *            URL path the HTTP request shall be executed to.
	 * @return Returns the response from the server to the HTTP request.
	 */
	CloseableHttpResponse executeCommand(String path);

}
