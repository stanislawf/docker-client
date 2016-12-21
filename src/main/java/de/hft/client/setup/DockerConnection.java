package de.hft.client.setup;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

/**
 * This class is providing a http client which enables the connection to the
 * Docker daemon.
 * 
 * @author stanislawfreund
 *
 */

public class DockerConnection {

	private static final String TLSV1 = "TLSv1";
	private CloseableHttpClient httpClient;

	public DockerConnection() {
		SSLContext sslContext = null;
		SSLConnectionSocketFactory sslsf = null;
		sslContext = getSSLContext();
		if (sslContext != null) {
			sslsf = new SSLConnectionSocketFactory(sslContext);
		}
		if (sslsf != null) {
			this.httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManagerShared(true).build();
		}
	}

	private SSLContext getSSLContext() {

		SSLContextBuilder sslContextBuilder = SSLContexts.custom();

		try {
			KeyStore keyStore = KeyStoreUtils.createKeyStore();
			if (keyStore != null) {
				sslContextBuilder.loadKeyMaterial(keyStore, "docker".toCharArray());
			}
			KeyStore trustStore = KeyStoreUtils.createTrustStore();
			if (trustStore != null) {
				TrustStrategy trustStrategy = new TrustSelfSignedStrategy();
				sslContextBuilder.loadTrustMaterial(trustStore, trustStrategy);
			}
			return sslContextBuilder.build();
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Returning a http client for the execution of http requests.
	 * 
	 * @return http client
	 */
	public CloseableHttpClient getHttpConnection() {
		return this.httpClient;
	}
}