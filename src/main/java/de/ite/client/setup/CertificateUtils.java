package de.ite.client.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;

/**
 * This class is providing the certificates which are needed to create the
 * connection between the HTTP client and the Docker daemon.
 * 
 * @author stanislawfreund
 *
 */
public class CertificateUtils {

	/**
	 * This method returns the a list of all certificates within the given
	 * pem-file.
	 * 
	 * @param path
	 *            Path of the pem-file.
	 * @param cetName
	 *            Name of the pem-file
	 * @return a list with all Certificates within the given path
	 */
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