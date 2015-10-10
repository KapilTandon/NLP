/**
 * 
 */
package com.digitalreasoning.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Tandon
 *
 */
public class UnzipUtility {

	/**
	 * @param args
	 */

	private static final int BUFFER_SIZE = 512;

	public static Map<String,String> unzip(String zipFilePath, String destDirectory)
			throws IOException {
		
		Map<String,String> zipFilesMap = new HashMap<String,String>();
		
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(
				zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		
		while (entry != null) {
			String filePath = destDirectory +"//"+ entry.getName();
			
			if (!entry.isDirectory()) {				
				extractFile(zipIn, filePath);
				zipFilesMap.put(entry.getName(),destDirectory +"//");
				//System.out.println(filePath);
			} else {				
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
		
		return zipFilesMap;
	}
	
	private static void extractFile(ZipInputStream zipIn, String filePath)
			throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {			
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

}
