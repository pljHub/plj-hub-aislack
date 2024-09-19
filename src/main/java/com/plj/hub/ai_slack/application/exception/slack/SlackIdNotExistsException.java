package com.plj.hub.ai_slack.application.exception.slack;

import com.plj.hub.ai_slack.global.exception.PljHubException;
import com.plj.hub.ai_slack.global.exception.SlackErrorCode;

public class SlackIdNotExistsException extends PljHubException {
    public SlackIdNotExistsException() {
        super(SlackErrorCode.SLACK_NOT_EXISTS);
    }
}
