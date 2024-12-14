package com.example.yaml.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * YmlReader utility class
 *
 * @author ypdev19
 */
public class YmlReader {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Retrieves the value of a nested parameter(attribute) from a map,
     * navigates through the nested keys using the provided key array.
     *
     * The map is the data from the YAML file.
     *
     * @param map  The map from which to retrieve the nested value
     * @param keys The sequence of keys to navigate through
     * @return The value of the last key in the array, or null if any key is not found.
     */
    public static Object getNestedKey(Map<String, Object> map, String... keys) {
        Object current = map;
        for (String key : keys) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(key);
            } else {
                return null;
            }
        }
        return current;
    }

    /**
     * Reads the content of a YAML file and returns it as a map.
     *
     * @return A Map containing the key-value pairs from the YAML file.
     * @throws IOException If an error occurs while reading the file.
     */
    public static Map<String, Object> getYmlFileContent(final String ymlFile) throws IOException {
        ClassPathResource resource = new ClassPathResource(ymlFile);
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        }
        return mapper.readValue(fileContents.toString(), Map.class);
    }
}

