package com.plj.hub.ai_slack.application.exception.ai;

import com.plj.hub.ai_slack.global.exception.AiErrorCode;
import com.plj.hub.ai_slack.global.exception.ErrorCode;
import com.plj.hub.ai_slack.global.exception.PljHubException;

public class AiBadRequestException extends PljHubException {
    public AiBadRequestException() {
        super(AiErrorCode.BAD_REQUEST);
    }
}
