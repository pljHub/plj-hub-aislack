package com.plj.hub.ai_slack.application.service.slack;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plj.hub.ai_slack.application.dto.slack.response.DeleteMessageResponseDto;
import com.plj.hub.ai_slack.application.dto.slack.response.SendMessageResponseDto;
import com.plj.hub.ai_slack.application.exception.AccessDeniedException;
import com.plj.hub.ai_slack.application.exception.slack.SlackIdNotExistsException;
import com.plj.hub.ai_slack.domain.model.Slack;
import com.plj.hub.ai_slack.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlackService {

    private final SlackRepository slackRepository;

    @Value("${slack.api.base.url}")
    private String SLACK_API_BASE_URL;

    @Value("${bot.token}")
    private String BOT_TOKEN;
    private final WebClient webClient;

    @Transactional
    public SendMessageResponseDto sendMessage(String email, String message) {

        log.info("메세지 전송 요청 email: {}", email);

        String slackIdByEmail = findSlackIdByEmail(email);

        String slackMessage = webClient.post()
                .uri(SLACK_API_BASE_URL + "chat.postMessage")
                .header("Authorization", "Bearer " + BOT_TOKEN)
                .header("Content-Type", "application/json")
                .bodyValue(createMessagePayload(slackIdByEmail, message))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Slack slack = Slack.create(slackMessage);
        slackRepository.save(slack);

        return new SendMessageResponseDto(slackMessage);
    }
    private String createMessagePayload(String slackId, String message) {
        return "{ \"channel\": \"" + slackId + "\", \"text\": \"" + message + "\" }";
    }

    public String findSlackIdByEmail(String slackEmail) {
        // 쿼리 파라미터로 이메일 전달
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("slack.com")
                        .path("/api/users.lookupByEmail")
                        .queryParam("email", slackEmail)
                        .build())
                .header("Authorization", "Bearer " + BOT_TOKEN)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 응답을 블로킹하여 바로 받아옴

        // 응답에서 Slack ID를 추출하는 부분
        return extractSlackIdFromResponse(response);
    }


    private String extractSlackIdFromResponse(String response) {
        try {
            // ObjectMapper를 사용하여 JSON 응답을 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            // "ok" 필드가 true인지 확인하여 요청이 성공했는지 확인
            if (root.path("ok").asBoolean()) {
                // "user" 객체에서 "id" 필드를 찾아서 Slack ID를 추출
                JsonNode userNode = root.path("user");
                String slackId = userNode.path("id").asText();
                return slackId;
            } else {
                throw new SlackIdNotExistsException();
            }
        } catch (Exception e) {
            // 예외 처리 로직 추가 (로그를 남기거나 적절한 예외 처리)
            throw new SlackIdNotExistsException();
        }
    }


    @Transactional
    public DeleteMessageResponseDto deleteMessage(Long currentUserId, String currentUserRole, UUID slackId) {
        if (!currentUserRole.equals("ADMIN")) {
            throw new AccessDeniedException();
        }

        Slack slack = findSlackById(slackId);
        slack.deleteSlack(currentUserId);
        slackRepository.save(slack);

        return new DeleteMessageResponseDto(slackId);
    }

    private Slack findSlackById(UUID slackId) {
        return slackRepository.findByIdAndDeletedAtIsNull(slackId).orElseThrow(SlackIdNotExistsException::new);
    }
}
