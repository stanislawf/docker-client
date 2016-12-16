package de.hft.client.cmd.exec.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecutionJson;

public class PostCommandJsonExecution implements ICommandExecutionJson {

	private CloseableHttpClient client;

	public PostCommandJsonExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path, StringEntity input) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(ADDRESS + "/" + path);
			input.setContentType("application/json");
			post.setEntity(input);
			response = client.execute(post);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
