package com.plj.hub.ai_slack.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AiErrorCode implements ErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청 혹은 일시적인 문제가 발생했습니다. 잠시 후 다시 시도해주세요."),
    AI_NOT_WORKING(HttpStatus.INTERNAL_SERVER_ERROR, "Ai 서버가 일시적으로 문제가 발생했습니다. 잠시 후 다시 시도해주세요.")
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
