package com.plj.hub.ai_slack.application.dto.slack.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeleteMessageResponseDto {
    private UUID slackId;
}
