/**
 * 
 */
package com.digitalreasoning.module1;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.digitalreasoning.util.FileToString;
import com.digitalreasoning.util.FileToSentences;
import com.digitalreasoning.util.NLPConstants;
import com.digitalreasoning.util.Sentence;
import com.digitalreasoning.util.SentenceToWords;
import com.digitalreasoning.util.Sentences;
import com.digitalreasoning.datastructure.Word;

/**
 * @author Tandon
 *
 */
public class ApplicationMain1 {
	/**
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub

		String inputFolderLocation = new String(NLPConstants.INPUT_DIR);
		String inputFileName = new String(NLPConstants.INPUT_FILE);
		String outputFolderLocation = new String(NLPConstants.OUTPUT_DIR);
		String inputFileLocation = new String(inputFolderLocation
				+ inputFileName);
		String stringFile = new String();
		String outputFileName = new String();

		List<String> fileToSentenceList = new ArrayList<String>();
		List<String> sentenceToWordList = new ArrayList<String>();
		List sentenseList = new ArrayList<String>();

		FileToString fts = new FileToString();

		if (inputFileName.contains(".")) {
			outputFileName = inputFileName.substring(0,
					inputFileName.lastIndexOf('.'));
		}

		File outputfile = new File(outputFolderLocation + outputFileName
				+ ".xml");

		Sentences sentences = new Sentences();
		sentences.setFileName(outputFileName);

		try {
			stringFile = fts.fileToString(inputFileLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileToSentenceList = FileToSentences.fileToSentences(stringFile,
				NLPConstants.locale);

		List sentenceList = new ArrayList<String>();

		int sentenceId = 0;

		for (String sentence : fileToSentenceList) {

			Sentence newSentence = new Sentence();
			sentenceId = sentenceId + 1;
			sentenceToWordList = SentenceToWords.sentenceToWords(sentence,
					NLPConstants.locale);
			newSentence.setSentenceId(sentenceId);

			List wordList = new ArrayList<String>();
			for (String word : sentenceToWordList) {
				Word newWord = new Word();
				newWord.setWordType("Other");
				newWord.setWord(word);
				wordList.add(newWord);
			}
			newSentence.setWords(wordList);
			sentenceList.add(newSentence);
		}
		sentences.setSentences(sentenceList);
		JAXBContext jaxbContext = JAXBContext.newInstance(Sentences.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(sentences, outputfile);
		// jaxbMarshaller.marshal(sentences, System.out);
	}
}