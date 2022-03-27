package vodafone.hackathon.emergency.model.response;

import lombok.Data;
import vodafone.hackathon.emergency.model.MailsToSendMessage;

import java.util.List;

@Data
public class UserResponseModel {
    Long id;
    String mail;
    List<MailsToSendMessage> mailsToSendMessage;
}
