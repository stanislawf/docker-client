package de.ite.client.cmd.exec.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecution;

/**
 * This class is defining the strategy of a HTTP DELETE requests.
 * 
 * @author stanislawfreund
 *
 */
public class DeleteCommandExecution implements ICommandExecution {

	private CloseableHttpClient client;

	public DeleteCommandExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path) {
		CloseableHttpResponse response = null;
		try {
			HttpDelete delete = new HttpDelete(ADDRESS + URL_SEPERATOR + path);
			response = client.execute(delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
