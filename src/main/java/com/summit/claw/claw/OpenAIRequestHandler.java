package com.summit.claw.claw;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAIRequestHandler {

    private static String API_KEY;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    // Assuming this is called somewhere in plugin enable or statically initialized
    public static void initialize(JavaPlugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "api_key.yml");
        if (!configFile.exists()) {
            plugin.getLogger().warning("API key configuration file does not exist.");
            return;
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        API_KEY = config.getString("openai.api_key", "defaultKey");
        if (API_KEY.equals("defaultKey")) {
            plugin.getLogger().warning("OpenAI API key is not set in api_key.yml.");
        }
    }

    public static String generateChallenge() {
        if (API_KEY == null || API_KEY.isEmpty() || API_KEY.equals("defaultKey")) {
            return "API Key not configured.";
        }
        JSONObject requestBody = getJsonObject();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            String challenge = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");
            return challenge;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "An error occurred while generating the challenge.";
        }
    }

    @NotNull
    private static JSONObject getJsonObject() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4-turbo-preview");
        requestBody.put("prompt",
                "1. Must be realistically achievable within a few hours." +
                "2. Do not assume the player to already has the necessary materials." +
                "3. Compatible with multiple players to do at the same time in a vanilla survival setting." +
                "4. Able to be judged on how good of a job each player did." +
                "5. State the challenge short and sweet, only brushing on key points." +
                "6. When done stating the challenge, end with the word \"wins the challenge. for survival\".");
                requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 121);
        requestBody.put("top_p", 0.75);
        requestBody.put("frequency_penalty", 0.88);
        requestBody.put("presence_penalty", 0.0);
        return requestBody;
    }
}
