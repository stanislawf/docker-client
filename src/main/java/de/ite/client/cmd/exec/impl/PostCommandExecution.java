package de.ite.client.cmd.exec.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecution;

/**
 * This class is defining the strategy of a HTTP POST requests.
 * 
 * @author stanislawfreund
 *
 */
public class PostCommandExecution implements ICommandExecution {

	private CloseableHttpClient client;

	public PostCommandExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(ADDRESS + URL_SEPERATOR + path);
			response = client.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
