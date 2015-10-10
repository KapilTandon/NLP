/**
 * 
 */
package com.digitalreasoning.module2;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.digitalreasoning.datastructure.Word;
import com.digitalreasoning.util.FileToSentences;
import com.digitalreasoning.util.FileToString;
import com.digitalreasoning.util.NERWordSet;
import com.digitalreasoning.util.NLPConstants;
import com.digitalreasoning.util.Sentence;
import com.digitalreasoning.util.SentenceToWords;
import com.digitalreasoning.util.Sentences;

/**
 * @author Tandon
 *
 */
public class ApplicationMain2 {
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
		String outputFileName = new String();
		String stringFile = new String();
		FileToString fts = new FileToString();

		List<String> fileToSentenceList = new ArrayList<String>();
		List<String> sentenceToWordList = new ArrayList<String>();
		List sentenceList = new ArrayList<String>();

		Sentences sentences = new Sentences();

		int sentenceId = 0;

		if (inputFileName.contains(".")) {
			outputFileName = inputFileName.substring(0,
					inputFileName.lastIndexOf('.'));
		}
		File outputfile = new File(outputFolderLocation + outputFileName
				+ ".xml");
		sentences.setFileName(outputFileName);

		HashSet<String> nerWordSet = NERWordSet.getNERWordSet();

		try {
			stringFile = fts.fileToString(inputFileLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileToSentenceList = FileToSentences.fileToSentences(stringFile,
				NLPConstants.locale);

		for (String sentence : fileToSentenceList) {

			Sentence newSentence = new Sentence();
			sentenceId = sentenceId + 1;
			sentenceToWordList = SentenceToWords.sentenceToWords(sentence,
					NLPConstants.locale);
			newSentence.setSentenceId(sentenceId);

			List wordList = new ArrayList<String>();
			for (String word : sentenceToWordList) {
				Word newWord = new Word();
				if (nerWordSet.contains(word)) {
					newWord.setWordType("NER");
				} else {
					newWord.setWordType("Other");
				}
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