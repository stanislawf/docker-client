package de.hft.client.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;

public class CertificateUtils {

	protected static List<Certificate> loadCertificates(String path, String cetName)
			throws IOException, CertificateException, NoSuchProviderException {
		File certificateFile = new File(path, cetName);
		FileReader fileReader = new FileReader(certificateFile);
		try {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			return (List<Certificate>) certFactory.generateCertificates(new FileInputStream(certificateFile));
		} finally {
			fileReader.close();
		}
	}

}