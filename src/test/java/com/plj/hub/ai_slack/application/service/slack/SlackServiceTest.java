package com.plj.hub.ai_slack.application.service.slack;

import com.plj.hub.ai_slack.application.dto.slack.response.SendMessageResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlackServiceTest {

    @Autowired
    private SlackService slackService;

    @Test
    void sendMeesageTest() {

        SendMessageResponseDto sendMessageResponse = slackService.sendMessage("tkdgns5817@gmail.com", "test용 메시지");
        System.out.println("sendMessage= " + sendMessageResponse.getMessage());
    }

    @Test
    void findSlackIdByEmail() {
        String slackIdByEmail = slackService.findSlackIdByEmail("tkdgns5817@gmail.com");
        System.out.println("slackIdByEmail = " + slackIdByEmail);
    }
}