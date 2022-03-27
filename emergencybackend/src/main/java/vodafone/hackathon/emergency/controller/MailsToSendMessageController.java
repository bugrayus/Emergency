package vodafone.hackathon.emergency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vodafone.hackathon.emergency.core.model.ApiResponse;
import vodafone.hackathon.emergency.model.request.CreateMailToSendMessageRequestModel;
import vodafone.hackathon.emergency.service.MailsToSendMessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mails")
public class MailsToSendMessageController {
    private final MailsToSendMessageService createMailToSendMessage;

    @PostMapping("/mailToSendMessage")
    public ResponseEntity<ApiResponse<Boolean>> createMailToSendMessage(@RequestBody CreateMailToSendMessageRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                createMailToSendMessage.createMailToSendMessage(requestModel)
        ));
    }
}
