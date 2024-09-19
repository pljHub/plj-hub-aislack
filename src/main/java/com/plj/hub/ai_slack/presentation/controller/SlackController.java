package com.plj.hub.ai_slack.presentation.controller;

import com.plj.hub.ai_slack.application.dto.slack.request.DeleteSlackMessage;
import com.plj.hub.ai_slack.application.dto.slack.request.SendSlackMessageRequestDto;
import com.plj.hub.ai_slack.application.dto.slack.response.DeleteMessageResponseDto;
import com.plj.hub.ai_slack.application.dto.slack.response.SendMessageResponseDto;
import com.plj.hub.ai_slack.application.service.slack.SlackService;
import com.plj.hub.ai_slack.global.dto.ResponseDto;
import com.plj.hub.ai_slack.global.login.CurrentUser;
import com.plj.hub.ai_slack.global.login.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/slacks")
public class SlackController {

    private final SlackService slackService;

    @PostMapping("/message")
    public ResponseEntity<ResponseDto<SendMessageResponseDto>> sendSlackMessage(@RequestBody SendSlackMessageRequestDto sendSlackMessageRequestDto) {
        SendMessageResponseDto sendMessageResponse = slackService.sendMessage(sendSlackMessageRequestDto.getEmail(), sendSlackMessageRequestDto.getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.success(HttpStatus.OK.name(), sendMessageResponse));
    }

    @DeleteMapping("/{slackId}")
    public ResponseEntity<ResponseDto<DeleteMessageResponseDto>> deleteSlackMessage(@RequestBody DeleteSlackMessage deleteSlackMessage, @Login CurrentUser currentUser) {
        DeleteMessageResponseDto deleteMessageResponse = slackService.deleteMessage(currentUser.getCurrentUserId(), currentUser.getCurrentUserRole(), deleteSlackMessage.getSlackId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.success(HttpStatus.OK.name(), deleteMessageResponse));
    }
}
