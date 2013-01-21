package org.adamk33n3r.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {

	private static void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}

	public static void unzip(File file) {
		String toDirectory = file.getParent();
		System.out.println("Extracting to: " + toDirectory);
		String sep = System.getProperty("file.separator");
		try {
			ZipFile zipFile = new ZipFile(file);
			for (ZipEntry entry : Collections.list(zipFile.entries())) {
				if (entry.isDirectory()) {
					System.out.println("Extracting directory: " + entry.getName());
					new File(toDirectory + sep + entry.getName()).mkdir();
					continue;
				}
				System.out.println("Extracting file: " + entry.getName());
				copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(toDirectory + sep + entry.getName())));
			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
