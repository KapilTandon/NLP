/**
 * 
 */
package com.digitalreasoning.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author Tandon
 *
 */
public class FileToString{
	

	public String fileToString(String file) throws IOException{		
		String s =	new String(Files.readAllBytes(
            Paths.get(file)));
		return s;
	}
	
}
