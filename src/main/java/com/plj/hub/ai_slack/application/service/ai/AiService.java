package com.plj.hub.ai_slack.application.service.ai;

import com.plj.hub.ai_slack.application.dto.ai.request.AiCreateRequestDto;
import com.plj.hub.ai_slack.application.dto.ai.response.AiCreateResponseDto;
import com.plj.hub.ai_slack.application.dto.ai.response.AiResponseDto;
import com.plj.hub.ai_slack.application.exception.ai.AiBadRequestException;
import com.plj.hub.ai_slack.application.exception.ai.AiNotWorkingException;
import com.plj.hub.ai_slack.domain.model.Ai;
import com.plj.hub.ai_slack.domain.repository.AiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiService {

    private final AiRepository aiRepository;
    private final WebClient webClient;

    @Value("${gemini.uri.prefix}")
    private String GEMINI_URI_PREFIX;

    @Value("${gemini.key}")
    private String GEMINI_KEY;

    @Transactional
    public AiResponseDto create(String question) {
        AiCreateRequestDto requestDto = createRequestDto(question);
        AiCreateResponseDto responseFromAi = getResponseFromAi(requestDto);
        String answer = getAnswer(responseFromAi);

        Ai ai = Ai.create(question, answer);
        aiRepository.save(ai);
        return new AiResponseDto(answer);
    }

    private static String getAnswer(AiCreateResponseDto responseFromAi) {
        return responseFromAi.getCandidates().get(0).getContent().getParts().get(0).getText();
    }

    private AiCreateResponseDto getResponseFromAi(AiCreateRequestDto requestDto) {
        return webClient.post()
                .uri(GEMINI_URI_PREFIX + GEMINI_KEY)
                .header("Content-Type", "application/json")
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(AiBadRequestException::new))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(AiNotWorkingException::new))
                .bodyToMono(AiCreateResponseDto.class)
                .block();
    }

    private AiCreateRequestDto createRequestDto(String prompt) {
        AiCreateRequestDto.Part part = new AiCreateRequestDto.Part(prompt);
        AiCreateRequestDto.Content content = new AiCreateRequestDto.Content(List.of(part));
        return new AiCreateRequestDto(List.of(content));
    }


}
