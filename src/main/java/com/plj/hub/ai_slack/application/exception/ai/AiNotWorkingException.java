package com.plj.hub.ai_slack.application.exception.ai;

import com.plj.hub.ai_slack.global.exception.AiErrorCode;
import com.plj.hub.ai_slack.global.exception.ErrorCode;
import com.plj.hub.ai_slack.global.exception.PljHubException;

public class AiNotWorkingException extends PljHubException {
    public AiNotWorkingException( ) {
        super(AiErrorCode.AI_NOT_WORKING);
    }
}
