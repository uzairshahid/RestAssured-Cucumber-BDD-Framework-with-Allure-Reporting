package Utilities.RestAssured;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
public class JSONReader {
    static File file;
    static JsonNode rootNode;

    private static JsonNode jsonFileReader(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        if ((file != null && !filePath.contains(file.getName())) || rootNode == null) {
            file = new File(filePath);
            rootNode = mapper.readTree(file);
        }
        return rootNode;
    }

    private static ArrayNode jsonRootNodeReader(String filePath, String rootNode) throws IOException
    {
        if (((jsonFileReader(filePath)).get(rootNode)).isArray()) {
            return (ArrayNode) (jsonFileReader(filePath)).get(rootNode);
        } else {
            return null;
        }
    }

    public static JsonNode jsonNodeReader(String filePath, String rootNode, String flowIdentifier) throws IOException
    {
        ArrayNode arrayNode = jsonRootNodeReader(filePath, rootNode);
        assert arrayNode != null;

        if (arrayNode.isArray()) {
            for (JsonNode jsonNode : arrayNode) {
                String nameFieldNode = jsonNode.get("NAME_DATA_SET").asText();
                if (flowIdentifier.equals(nameFieldNode)) {
                    return jsonNode;
                }
            }
        }
        return null;
    }

    public static String jsonEnvNodeReader(String filePath, String rootNode, String envURL) throws IOException
    {
        ArrayNode arrayNode = jsonRootNodeReader(filePath, rootNode);
        assert arrayNode != null;
        String nameFieldNode="";

        if (arrayNode.isArray())
        {
            for (JsonNode jsonNode : arrayNode)
            {
                nameFieldNode = jsonNode.get(envURL).asText();
            }
        }
        return nameFieldNode;
    }
}
