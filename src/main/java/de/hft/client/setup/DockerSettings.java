package de.hft.client.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.glassfish.jersey.SslConfigurator;

public class DockerSettings {

	private static final String CERT_PATH = "/Users/stanislawfreund/.docker/machine/certs";
	private static final String TLSV1 = "TLSv1";
	private CloseableHttpClient httpClient;

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
		KeyStore trustStore = createTrustStore2();
		if (trustStore != null) {
			sslConfig.trustStore(trustStore);
		}

		return sslConfig.createSSLContext();
	}

	private KeyStore createTrustStore2() {
		File certificateFile = new File(CERT_PATH, "ca.pem");
		KeyStore trustStore = null;
		try {
			CertificateFactory certFact = CertificateFactory.getInstance("X.509", "BC");
			Certificate generateCertificate = certFact.generateCertificate(new FileInputStream(certificateFile));
			trustStore = KeyStore.getInstance("JKS");
			trustStore.load(null);
			trustStore.setCertificateEntry("ca", generateCertificate);
		} catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| NoSuchProviderException e) {
			e.printStackTrace();
		}
		return trustStore;
	}

	private KeyStore createKeyStore() {
		KeyPair keyPair;
		List<Certificate> privateCertificates;
		KeyStore keyStore = null;
		try {
			keyPair = loadKeyPair();
			privateCertificates = loadCertificates();
			Certificate[] certificateChain = privateCertificates.toArray(new Certificate[privateCertificates.size()]);
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(null);
			if (privateCertificates.size() > 0) {
				keyStore.setKeyEntry("docker", keyPair.getPrivate(), "docker".toCharArray(), certificateChain);
			}
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException
				| NoSuchProviderException e) {
			e.printStackTrace();
		}

		return keyStore;
	}

	private KeyPair loadKeyPair()
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, NoSuchProviderException {
		KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
		File certificateFile = new File(CERT_PATH, "key.pem");
		PublicKey publicKey = createPublicKey(factory, certificateFile);
		PrivateKey privateKey = createPrivateKey(factory, certificateFile);
		return new KeyPair(publicKey, privateKey);
	}

	private PrivateKey createPrivateKey(KeyFactory factory, File file) throws IOException {
		PrivateKey privKey = null;
		FileReader fileReader = new FileReader(file);
		PEMParser pemParser = null;
		byte[] encodedKey = null;
		try {
			pemParser = new PEMParser(fileReader);
			PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
			PrivateKeyInfo privateKeyInfo = pemKeyPair.getPrivateKeyInfo();
			encodedKey = privateKeyInfo.getEncoded();

		} finally {
			pemParser.close();
			fileReader.close();
		}
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encodedKey);
		try {
			privKey = factory.generatePrivate(privKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privKey;
	}

	private PublicKey createPublicKey(KeyFactory factory, File file) throws IOException {
		PublicKey pubKey = null;
		FileReader fileReader = new FileReader(file);
		PEMParser pemParser = null;
		byte[] encodedKey = null;
		try {
			pemParser = new PEMParser(fileReader);
			PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
			SubjectPublicKeyInfo publicKeyInfo = pemKeyPair.getPublicKeyInfo();
			encodedKey = publicKeyInfo.getEncoded();
		} finally {
			pemParser.close();
			fileReader.close();
		}

		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
		try {
			pubKey = factory.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return pubKey;
	}

	private List<Certificate> loadCertificates() throws IOException, CertificateException, NoSuchProviderException {
		File certificateFile = new File(CERT_PATH, "cert.pem");
		FileReader fileReader = new FileReader(certificateFile);
		try {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
			return (List<Certificate>) certFactory.generateCertificates(new FileInputStream(certificateFile));
		} finally {
			fileReader.close();
		}
	}

	public CloseableHttpClient getHttpConnection() {
		return this.httpClient;
	}
}