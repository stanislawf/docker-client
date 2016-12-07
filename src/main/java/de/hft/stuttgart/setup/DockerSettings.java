package de.hft.stuttgart.setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.glassfish.jersey.SslConfigurator;

public class DockerSettings {

	private static final String CERT_PATH = "/Users/stanislawfreund/.docker/machine/certs";
	private static final String TLSV1 = "TLSv1";
	private CloseableHttpClient httpClient;
	private static DockerSettings dockerSettings;

	public DockerSettings() {
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

		Security.addProvider(new BouncyCastleProvider());

		String httpProtocols = System.getProperty("https.protocols");
		if (!TLSV1.equals(httpProtocols)) {
			System.setProperty("https.protocols", TLSV1);
		}
		SslConfigurator sslConfig = SslConfigurator.newInstance(true);

		KeyStore keyStore = createKeyStore();
		if (keyStore != null) {
			sslConfig.keyStore(keyStore);
		}
		sslConfig.keyStorePassword("docker");
		try {
			KeyStore trustStore = createTrustStore();
			if (trustStore != null) {
				sslConfig.trustStore(trustStore);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return sslConfig.createSSLContext();
	}

	private static KeyStore createKeyStore() {
		KeyPair keyPair;
		KeyStore keyStore = null;
		List<Certificate> privateCertificates;
		try {
			keyPair = loadPrivateKey();
			privateCertificates = loadCertificates();
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(null);
			if ((keyPair != null) && (privateCertificates.size() > 0)) {
				keyStore.setKeyEntry("docker", keyPair.getPrivate(), "docker".toCharArray(),
						privateCertificates.toArray(new Certificate[privateCertificates.size()]));
			}
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}

		return keyStore;
	}

	private static KeyPair loadPrivateKey() throws FileNotFoundException {
		File certificate = new File(CERT_PATH, "key.pem");
		BufferedReader reader = new BufferedReader(new FileReader(certificate));

		PEMParser pemParser = null;
		KeyPair keyPair = null;
		try {
			pemParser = new PEMParser(reader);

			PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();

			byte[] pemPrivateKeyEncoded = pemKeyPair.getPrivateKeyInfo().getEncoded();
			byte[] pemPublicKeyEncoded = pemKeyPair.getPublicKeyInfo().getEncoded();

			KeyFactory factory = KeyFactory.getInstance("RSA");

			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pemPublicKeyEncoded);
			PublicKey publicKey = factory.generatePublic(publicKeySpec);

			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(pemPrivateKeyEncoded);
			PrivateKey privateKey = factory.generatePrivate(privateKeySpec);

			keyPair = new KeyPair(publicKey, privateKey);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(pemParser);
			IOUtils.closeQuietly(reader);
		}
		return keyPair;
	}

	private static List<Certificate> loadCertificates() throws FileNotFoundException {
		File certificate = new File(CERT_PATH, "cert.pem");
		BufferedReader reader = new BufferedReader(new FileReader(certificate));
		PEMParser pemParser = null;
		List<Certificate> certificates = new ArrayList<>();

		try {
			pemParser = new PEMParser(reader);
			JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter().setProvider("BC");
			Object certObj = pemParser.readObject();

			while (certObj != null) {
				X509CertificateHolder certificateHolder = (X509CertificateHolder) certObj;
				certificates.add(certificateConverter.getCertificate(certificateHolder));

				certObj = pemParser.readObject();
			}
		} catch (IOException | CertificateException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(pemParser);
			IOUtils.closeQuietly(reader);
		}
		return certificates;
	}

	private static KeyStore createTrustStore() throws FileNotFoundException {
		File caPath = new File(CERT_PATH, "ca.pem");
		BufferedReader reader = new BufferedReader(new FileReader(caPath));
		PEMParser pemParser = null;
		KeyStore trustStore = null;

		try {
			pemParser = new PEMParser(reader);
			X509CertificateHolder certificateHolder = (X509CertificateHolder) pemParser.readObject();
			Certificate caCertificate = new JcaX509CertificateConverter().setProvider("BC")
					.getCertificate(certificateHolder);

			trustStore = KeyStore.getInstance("JKS");
			trustStore.load(null);
			trustStore.setCertificateEntry("ca", caCertificate);
		} catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(pemParser);
			IOUtils.closeQuietly(reader);
		}
		return trustStore;

	}

	public CloseableHttpClient getHttpConnection() {

		return this.httpClient;
	}
}