package de.hft.stuttgart.command.exec;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public class PostCommandExecution implements ICommandExecution {

	private CloseableHttpClient client;

	public PostCommandExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(ADDRESS + "/" + path);
			response = client.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
