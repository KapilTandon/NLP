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
public class SentenceToWords {

	public static List<String> sentenceToWords(String wordsString, Locale locale) {

		List<String> wordList = new ArrayList<String>();

		BreakIterator wordIterator = BreakIterator.getWordInstance(locale);
		wordIterator.setText(wordsString);

		int start = wordIterator.first();
		for (int end = wordIterator.next(); end != BreakIterator.DONE; start = end, end = wordIterator
				.next()) {
			wordList.add(wordsString.substring(start, end));
		}
		return wordList;
	}
}

