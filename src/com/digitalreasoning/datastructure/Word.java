/**
 * 
 */
package com.digitalreasoning.datastructure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tandon
 *
 */
@XmlRootElement(name = "Word")
public class Word {

	String wordType = "";
	String word = "";

	public String getWordType() {
		return wordType;
	}

	@XmlElement(name = "Word-Type")
	public void setWordType(String wordType) {
		this.wordType = wordType;
	}

	public String getWord() {
		return word;
	}

	@XmlElement(name = "Word-Value")
	public void setWord(String word) {
		this.word = word;
	}

}
