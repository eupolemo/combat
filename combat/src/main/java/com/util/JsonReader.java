package com.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonReader {
	protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
	
	public String loadJson(String filename) throws IOException {
		String metadata = null;
		BufferedInputStream inputStream;
		try {

		    ClassLoader loader = Thread.currentThread().getContextClassLoader();
		    inputStream = (BufferedInputStream) loader.getResourceAsStream("json/".concat(filename));
		    metadata = IOUtils.toString(inputStream);
		    inputStream.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return metadata;
	}

	public Object serialize(String json, Class<?> class_) {
		return GSON.fromJson(json, class_);
	}
}
