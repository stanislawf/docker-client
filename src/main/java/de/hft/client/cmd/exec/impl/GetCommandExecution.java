package de.hft.client.cmd.exec.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import de.hft.client.cmd.exec.api.ICommandExecution;

public class GetCommandExecution implements ICommandExecution {

	private CloseableHttpClient client;

	public GetCommandExecution(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public CloseableHttpResponse executeCommand(String path) {
		CloseableHttpResponse response = null;
		try {
			HttpGet get = new HttpGet(ADDRESS + "/" + path);
			response = client.execute(get);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
