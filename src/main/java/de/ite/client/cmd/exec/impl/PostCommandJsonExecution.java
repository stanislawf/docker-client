package de.ite.client.cmd.exec.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import de.ite.client.cmd.exec.api.ICommandExecutionJson;

/**
 * This class is defining the strategy of a HTTP POST requests which includes a
 * JSON.
 * 
 * @author stanislawfreund
 *
 */
public class PostCommandJsonExecution implements ICommandExecutionJson {

	private CloseableHttpClient client;

	public PostCommandJsonExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path, StringEntity input) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(ADDRESS + URL_SEPERATOR + path);
			input.setContentType(APPLICATION_JSON);
			post.setEntity(input);
			response = client.execute(post);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
