package com.screensoundmusic.sreensoundmusic.service;

import com.screensoundmusic.sreensoundmusic.config.OpenAiConfig;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGpt {

    public static String consultar(String consulta) {
        OpenAiService service = new OpenAiService(OpenAiConfig.getApiKey());
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Me explique quem é esse artista: " + consulta)
                .model("gpt-3.5-turbo-instruct")
                .echo(false)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(completionRequest);
        return resposta.getChoices().get(0).getText();
    }
}
