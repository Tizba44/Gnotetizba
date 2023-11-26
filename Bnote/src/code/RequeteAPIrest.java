package code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequeteAPIrest {
    private List<Map<String, String>> profsMap;
    private ObjectMapper mapper = new ObjectMapper();

    public RequeteAPIrest() {
        // console log ca joue
        System.out.println("allo");
        try {
            Map<String, List<Map<String, String>>> usersMap = mapper.readValue(new File("src/code/data.json"), new TypeReference<Map<String, List<Map<String, String>>>>() {});
            profsMap =  usersMap.get("profs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPostRequest() throws Exception {
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);


        // console log ca joue
        System.out.println("allo");



        // Convert your data to JSON String
        String jsonInputString = mapper.writeValueAsString(profsMap);

        // Write JSON input string to output stream
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response from input stream
        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
