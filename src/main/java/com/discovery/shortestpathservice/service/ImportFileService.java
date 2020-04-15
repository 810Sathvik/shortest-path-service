package com.discovery.shortestpathservice.service;


import java.io.IOException;
import java.nio.file.Path;

/**
 * @author SheshidharReddy.Amireddy
 */
public interface ImportFileService {

	public boolean importFile(Path filePath) throws IOException;

}
