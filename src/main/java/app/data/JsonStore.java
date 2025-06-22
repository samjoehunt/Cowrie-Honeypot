package app.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Singleton class that provides centralized access to the JSON data
 * loaded from a Cowrie honeypot log file.
 */
public class JsonStore {

    /**
     * The single instance of the JsonStore class.
     */
    private static final JsonStore jsonInstance = new JsonStore();

    /**
     * The parsed contents of the uploaded JSON file.
     */
    private JsonNode jsonData;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private JsonStore() {}

    /**
     * Returns the single instance of JsonStore.
     * @return the singleton instance of JsonStore
     */
    public static JsonStore getInstance() {
        return jsonInstance;
    }

    /**
     * Loads and parses the contents of the provided JSON file.
     * @param file the uploaded JSON file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void loadJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.jsonData = mapper.readTree(file);
    }

    /**
     * Returns the parsed JSON data from the uploaded file.
     * @return a {@link JsonNode} representing the contents of the JSON file
     */
    public JsonNode getJsonData() {
        return jsonData;
    }
}
