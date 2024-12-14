package com.example.yaml;

import com.example.yaml.util.YmlReader;

import java.io.*;
import java.util.Map;

/**
 * Main class - project execution
 * What is this project about: Read yml file using jackson library (V-2.18.2)
 *
 * @author ypdev19
 */
public class YamlWithJacksonDemo {

	public static void main(String[] args) {
		try {
			Map<String, Object> fileContent = YmlReader.getYmlFileContent("application.yml");
			Map<String, Object> data = (Map<String, Object>) YmlReader.getNestedKey(fileContent, "spring", "security", "oauth2", "resourceServer", "jwt");
			Map<String, Object> data2 = (Map<String, Object>) YmlReader.getNestedKey(fileContent, "spring", "datasource");

			System.out.println("The yaml file map => " + fileContent);
			System.out.println("JWT content => " + data);
			System.out.println("issuer-client-url => " + data2.get("url"));
		} catch (IOException e) {
			System.err.println("Error reading YAML file: " + e.getMessage());
		}
	}

}
