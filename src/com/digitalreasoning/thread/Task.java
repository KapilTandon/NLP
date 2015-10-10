/**
 * 
 */
package com.digitalreasoning.thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.digitalreasoning.datastructure.Word;
import com.digitalreasoning.util.FileToSentences;
import com.digitalreasoning.util.FileToString;
import com.digitalreasoning.util.Sentence;
import com.digitalreasoning.util.SentenceToWords;
import com.digitalreasoning.util.Sentences;

/**
 * @author Tandon
 *
 */
public class Task implements Runnable {

	private HashMap<String, String> configMap;
	private String TaskId;
	private Locale locale;
	private HashSet nerWordSet;

	public Task(String Id, Locale locale, Set nerWordSet,
			Map<String, String> configurationMap) {
		this.TaskId = Id;
		this.locale = locale;
		this.nerWordSet = (HashSet<String>) nerWordSet;
		this.configMap = (HashMap<String, String>) configurationMap;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("Task ID : " + TaskId + " performed by "
		// + Thread.currentThread().getName());

		String stringFile = new String();
		String inputFolderLocation = new String(
				configMap.get("inputFolderLocation"));
		String inputFileName = configMap.get("inputFileName");
		String inputFileLocation = new String(inputFolderLocation
				+ inputFileName);
		String outputFolderLocation = configMap.get("outputFolderLocation");
		String outputFileName = new String();

		List<String> fileToSentenceList = new ArrayList<String>();
		List<String> sentenceToWordList = new ArrayList<String>();
		List sentenceList = new ArrayList<String>();

		int sentenceId = 0;
		
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
		fileToSentenceList = FileToSentences
				.fileToSentences(stringFile, locale);

		for (String sentence : fileToSentenceList) {

			Sentence newSentence = new Sentence();
			sentenceId = sentenceId + 1;
			sentenceToWordList = SentenceToWords.sentenceToWords(sentence,
					locale);
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
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Sentences.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(sentences, outputfile);
			// jaxbMarshaller.marshal(sentences, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
