package com.plj.hub.ai_slack.application.dto.slack.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteSlackMessage {
    private UUID slackId;
}
