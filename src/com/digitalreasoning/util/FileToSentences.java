/**
 * 
 */
package com.digitalreasoning.util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Tandon
 *
 */
public class FileToSentences {
	
	public static List<String> fileToSentences(String sentencesString, Locale locale) {

		List<String> sentenceList = new ArrayList<String>();

		BreakIterator sentensceIterator = BreakIterator
				.getSentenceInstance(locale);
		sentensceIterator.setText(sentencesString);

		int start = sentensceIterator.first();
		for (int end = sentensceIterator.next(); end != BreakIterator.DONE; start = end, end = sentensceIterator
				.next()) {
			//System.out.println(sentencesString.substring(start, end));
			sentenceList.add(sentencesString.substring(start, end));
		}
		return sentenceList;
	}
}

