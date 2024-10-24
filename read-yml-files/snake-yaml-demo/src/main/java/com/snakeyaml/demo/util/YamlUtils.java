package com.snakeyaml.demo.util;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.InputStream;
import java.util.Map;

/**
 * This is a Util class to read yml file in different ways using snakeyaml library.
 *
 * @author ypdev19
 */
public class YamlUtils {

    /**
     * Read directly a YAML File.
     * (This is the function to use if you just need a quick read of your YAML
     * file because it has a really simple structure)
     *
     * @param fileName the name of the file to read.
     * @return Map<String, Object>
     */
    public Map<String, Object> readSimpleYmlFile(final String fileName) {
        InputStream inputStream = getFile(fileName);

        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    /**
     * Reads a YAML file and deserializes it into an object of the specified class using a custom YAML loader with
     * a TagInspector to configure the parser.
     * (If you need to deserialize YAML data into a specific object structure that requires custom handling or
     * specific parsing settings then this is the function to use)
     *
     * @param fileName the name of the YAML file to read
     * @param clazz the class of the object to deserialize into
     * @return an instance of the specified class, deserialized from the YAML file
     */
    public <T> T readYmlWithCustomLoader(final String fileName, Class<T> clazz) {
        LoaderOptions loaderOptions = new LoaderOptions(); // this is to configure the YAML parser
        TagInspector taginspector =
                tag -> tag.getClassName().equals(clazz.getName());
        loaderOptions.setTagInspector(taginspector);

        Yaml yaml = new Yaml(new Constructor(loaderOptions)); //we're using the Constructor class to specify the target Java class

        return yaml.load(getFile(fileName));
    }

    /**
     * Reads a YAML file and deserializes it into an object of the specified class.
     * (This basically does the same as readYmlWithCustomLoader but using the default YAML loader.
     * It's the function to use if your YAML file follows a standard structure and doesn't require custom parsing settings)
     *
     * @param fileName the name of the YAML file to read
     * @param clazz the class of the object to deserialize into
     * @return an instance of the specified class, deserialized from the YAML file
     */
    public <T> T readYmlWithDefaultLoader(String fileName, Class<T> clazz) {
        InputStream inputStream = getFile(fileName);
        Yaml yaml = new Yaml();

        // The loadAs method takes an InputStream and a target class as input, and returns an instance of the target class.
        return yaml.loadAs(inputStream, clazz);
    }

    /**
     * Retrieves an InputStream for a file with the given name.
     *
     * @param fileName the name of the file to retrieve
     * @return an InputStream for the file, or null if the file is not found
     * @throws IllegalArgumentException if the file is not found
     */
    private InputStream getFile(final String fileName) {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }

        return inputStream;
    }
}
