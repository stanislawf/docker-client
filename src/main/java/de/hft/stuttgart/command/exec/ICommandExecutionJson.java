package de.hft.stuttgart.command.exec;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;

public interface ICommandExecutionJson {

	public static String ADDRESS = "https://192.168.99.100:2376";

	public CloseableHttpResponse executeCommand(String path, StringEntity input);
}
