package de.hft.client.cmd.exec.api;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface ICommandExecution {

	static String ADDRESS = "https://192.168.99.100:2376";

	CloseableHttpResponse executeCommand(String path);

}
