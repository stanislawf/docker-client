package de.ite.client.setup;

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
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;

/**
 * This class is creating the necessary KeyStores and is filling them with the
 * certificates needed for the connection between the HTTP client and the Docker
 * daemon.
 * 
 * @author stanislawfreund
 *
 */
public class KeyStoreUtils {

	private static final String CERT_PATH = "/Users/stanislawfreund/.docker/machine/certs";

	/**
	 * This method is creating the TrustStore and is filling it with the
	 * certificates from the pem-file.
	 * 
	 * @return Return of the KeyStore.
	 */
	protected static KeyStore createTrustStore() {
		File certificateFile = new File(CERT_PATH, "ca.pem");
		KeyStore trustStore = null;
		try {
			CertificateFactory certFact = CertificateFactory.getInstance("X.509");
			Certificate generateCertificate = certFact.generateCertificate(new FileInputStream(certificateFile));
			trustStore = KeyStore.getInstance("JKS");
			trustStore.load(null);
			trustStore.setCertificateEntry("ca", generateCertificate);
		} catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return trustStore;
	}

	/**
	 * This method is creating the KeyStore and is filling it with the
	 * certificates from the pem-file.
	 * 
	 * @return Return of the KeyStore.
	 */
	protected static KeyStore createKeyStore() {
		KeyPair keyPair;
		List<Certificate> privateCertificates;
		KeyStore keyStore = null;
		try {
			keyPair = loadKeyPair();
			privateCertificates = CertificateUtils.loadCertificates(CERT_PATH, "cert.pem");
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

	private static KeyPair loadKeyPair()
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, NoSuchProviderException {
		KeyFactory factory = KeyFactory.getInstance("RSA");
		File certificateFile = new File(CERT_PATH, "key.pem");
		PublicKey publicKey = createPublicKey(factory, certificateFile);
		PrivateKey privateKey = createPrivateKey(factory, certificateFile);
		return new KeyPair(publicKey, privateKey);
	}

	private static PrivateKey createPrivateKey(KeyFactory factory, File file) throws IOException {
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

	private static PublicKey createPublicKey(KeyFactory factory, File file) throws IOException {
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
}
