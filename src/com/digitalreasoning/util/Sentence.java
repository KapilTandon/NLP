/**
 * 
 */
package com.digitalreasoning.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.digitalreasoning.datastructure.Word;

/**
 * @author Tandon
 *
 */
@XmlRootElement(name = "Sentence")
public class Sentence {

	private Integer sentenceId;
	
	List<Word> words;

	public Integer getSentenceId() {
		return sentenceId;
	}

	public void setSentenceId(Integer sentenceId) {
		this.sentenceId = sentenceId;
	}

	public List<Word> getWords() {
		return words;
	}

	@XmlElement(name = "Word")
	public void setWords(List<Word> words)

	{	
		this.words = words;
	}

}
