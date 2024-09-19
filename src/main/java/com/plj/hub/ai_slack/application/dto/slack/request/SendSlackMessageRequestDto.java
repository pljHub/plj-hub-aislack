package com.plj.hub.ai_slack.application.dto.slack.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendSlackMessageRequestDto {

    private String email;
    private String message;

}
