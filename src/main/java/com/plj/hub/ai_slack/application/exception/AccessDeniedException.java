package com.plj.hub.ai_slack.application.exception;

import com.plj.hub.ai_slack.global.exception.ErrorCode;
import com.plj.hub.ai_slack.global.exception.PljHubException;
import com.plj.hub.ai_slack.global.exception.SlackErrorCode;

public class AccessDeniedException extends PljHubException {
    public AccessDeniedException() {
        super(SlackErrorCode.ACCESS_DENIED);
    }
}
