package controller;

import java.io.File;

public class Controller {
	
	FileIO f = new FileIO();
	File inputFile;
	
	public Controller(String filePath) {
		inputFile = new File(filePath);
		
		f.parseFile(inputFile);
	}

}
