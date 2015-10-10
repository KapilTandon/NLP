/**
 * 
 */
package com.digitalreasoning.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Tandon
 *
 */
public class NERWordSet {
	
	public static HashSet<String> getNERWordSet() {
		
		HashSet<String> nerWordSet = new HashSet<String>();
		FileToString fts = new FileToString();
		List<String> fileToNERSentenceList = new ArrayList<String>();
		List<String> sentenceToNERWordList = new ArrayList<String>();
		
		String stringNERFile = new String();
		
		String inputNERFolderLocation = NLPConstants.NER_DIR;
		String inputNERFileName = NLPConstants.NER_FILE;
		String inputNERFileLocation = new String(inputNERFolderLocation+inputNERFileName);
		
		try {
			 stringNERFile = fts.fileToString(inputNERFileLocation);						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fileToNERSentenceList = FileToSentences.fileToSentences(stringNERFile,  NLPConstants.locale);

		for (String sentenceNER : fileToNERSentenceList) {
			sentenceToNERWordList = SentenceToWords.sentenceToWords(
					sentenceNER, NLPConstants.locale);
			for (String wordNER : sentenceToNERWordList) {
				// System.out.println(wordNER);
				nerWordSet.add(wordNER.trim());
			}

		}
		return nerWordSet;
	}

}
