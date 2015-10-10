/**
 * 
 */
package com.digitalreasoning.module3;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.digitalreasoning.thread.ThreadPool;
import com.digitalreasoning.util.NERWordSet;
import com.digitalreasoning.util.NLPConstants;
import com.digitalreasoning.util.UnzipUtility;

/**
 * @author Tandon
 *
 */
public class ApplicationMain3 {
	/**
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {

		Set<String> nerWordSet = NERWordSet.getNERWordSet();
		// TODO Auto-generated method stub
		try {
			Map<String, String> zipFilesMap = new HashMap<String, String>(
					UnzipUtility.unzip(NLPConstants.ZIPFILE_NAME,
							NLPConstants.ZIPOUTPUT_DIR));

			for (Map.Entry<String, String> entry : zipFilesMap.entrySet()) {
				HashMap<String, String> configMap = new HashMap<String, String>();
				configMap.put("inputFolderLocation", entry.getValue());
				configMap.put("inputFileName", entry.getKey());
				configMap.put("outputFolderLocation", entry.getValue());

				new ThreadPool(entry.getValue() + entry.getKey(),
						NLPConstants.locale, nerWordSet, configMap);

			}
			ThreadPool.stopThreadPool();

		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}

	}
}