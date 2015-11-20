package com.bountive.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.bountive.graphics.shader.AbstractShader;

public abstract class FileUtil {

	public static String readFile(String src) {
		
		StringBuilder fileContent = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(AbstractShader.class.getClassLoader().getResourceAsStream(src)));
			String line;
			
			while ((line = reader.readLine()) != null) {
				fileContent.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileContent.toString();
	}
}
