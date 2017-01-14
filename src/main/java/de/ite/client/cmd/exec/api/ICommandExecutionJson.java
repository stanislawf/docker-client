package de.ite.client.cmd.exec.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;

/**
 * This interfaces provides the method for the execution of the respective HTTP
 * requests which include a JSON. The concrete implementations of this
 * interfaces are defining the strategy of the HTTP requests.
 * 
 * @author stanislawfreund
 *
 */
public interface ICommandExecutionJson {

	String ADDRESS = "https://192.168.99.100:2376";

	String URL_SEPERATOR = "/";

	String APPLICATION_JSON = "application/json";

	/**
	 * This method is adding the JSON entity to the HTTP request and is
	 * executing the request to the given URL path.
	 * 
	 * @param path
	 *            URL path the HTTP request shall be executed to.
	 * @param input
	 *            StringEntity which contains the JSON.
	 * @return Returns the response from the server to the HTTP request.
	 */
	CloseableHttpResponse executeCommand(String path, StringEntity input);
}
/**
 * This method is executing the HTTP request to the given URL path.
 * 
 * @param path
 *            URL path the HTTP request shall be executed to.
 * @return Returns the response from the server to the HTTP request.
 */