package com.screensoundmusic.sreensoundmusic.config;

public class OpenAiConfig {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public static String getApiKey() {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("Chave da API não configurada. Configure a variável de ambiente OPEN_API+KEY");
        }
        return API_KEY;
    }
}
