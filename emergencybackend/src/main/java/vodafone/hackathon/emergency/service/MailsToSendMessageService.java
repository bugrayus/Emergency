package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.model.MailsToSendMessage;
import vodafone.hackathon.emergency.model.User;
import vodafone.hackathon.emergency.model.request.CreateMailToSendMessageRequestModel;
import vodafone.hackathon.emergency.repository.MailsToSendMessageRepository;
import vodafone.hackathon.emergency.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MailsToSendMessageService {
    private final MailsToSendMessageRepository mailsToSendMessageRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    public boolean createMailToSendMessage(CreateMailToSendMessageRequestModel requestModel) {
        if (mailsToSendMessageRepository.existsByMail(requestModel.getMail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Mail already exists.");
        MailsToSendMessage mailsToSendMessage = mapper.map(requestModel, MailsToSendMessage.class);
        User user = authenticationService.getCurrentUser();
        mailsToSendMessage.setUser(user);
        mailsToSendMessageRepository.save(mailsToSendMessage);
        user.getMailsToSendMessage().add(mailsToSendMessage);
        userRepository.save(user);
        return true;
    }
}
