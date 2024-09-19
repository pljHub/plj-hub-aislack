package com.plj.hub.ai_slack.presentation.controller;

import com.plj.hub.ai_slack.application.dto.ai.request.AiCreateRequestDto;
import com.plj.hub.ai_slack.application.dto.ai.request.AiRequestDto;
import com.plj.hub.ai_slack.application.dto.ai.response.AiResponseDto;
import com.plj.hub.ai_slack.application.service.ai.AiService;
import com.plj.hub.ai_slack.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;
    @PostMapping
    public ResponseEntity<ResponseDto<AiResponseDto>> create(@RequestBody AiRequestDto aiRequestDto) {
        AiResponseDto aiResponseDto = aiService.create(aiRequestDto.getQuestion());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.success(HttpStatus.OK.name(), aiResponseDto));
    }


}
