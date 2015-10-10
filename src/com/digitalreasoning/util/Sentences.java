/**
 * 
 */
package com.digitalreasoning.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tandon
 *
 */
@XmlRootElement(name = "Sentences")
public class Sentences {

	String fileName = "";
	List sentences;

	@XmlElement(name = "Sentence")
	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String getFileName() {
		return fileName;
	}

	@XmlElement(name = "FileName")
	public void setFileName(String fileName)

	{
		this.fileName = fileName;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}
}
