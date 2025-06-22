package app.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that provides centralised access to the JSON data
 * loaded from a Cowrie honeypot log file.
 */
public class JsonStore {

    /**
     * The single instance of the JsonStore class.
     */
    private static final JsonStore jsonInstance = new JsonStore();

    /**
     * List of entries containing the parsed contents of the uploaded JSON file.
     */
    private final List<JsonNode> jsonData = new ArrayList<>();

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
        jsonData.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                JsonNode node = mapper.readTree(line);
                jsonData.add(node);
            }
        }
    }

    /**
     * Function to return an ArrayList containing all source IPs present in the JSON
     * @return ArrayList containing all source IPs present in the JSON
     */
    public List<String> getSourceIPs() {
        List<String> sourceIPList = new ArrayList<>();
        for (JsonNode node : jsonData) {
            JsonNode ipNode = node.get("src_ip");
            if (ipNode != null && !ipNode.isNull()) {
                String ip = ipNode.asText();
                if (!sourceIPList.contains(ip)) {
                    sourceIPList.add(ip);
                }
            }
        }
        return sourceIPList;
    }

    /**
     * Function to return an ArrayList containing all session IDs present in the JSON
     * @return ArrayList containing all unique session IDs present in the JSON
     */
    public List<String> getSessions() {
        List<String> sessionList = new ArrayList<>();
        for (JsonNode node : jsonData) {
            JsonNode sessionNode = node.get("session");
            if (sessionNode != null) {
                String sessionID = sessionNode.asText();
                if (!sessionList.contains(sessionID)) {
                    sessionList.add(sessionID);
                }
            }
        }
        return sessionList;
    }
}
