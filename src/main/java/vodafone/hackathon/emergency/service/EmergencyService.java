package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.model.Emergency;
import vodafone.hackathon.emergency.model.request.CreateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.request.SendEmergencyMessageRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.response.EmergencyResponseModel;
import vodafone.hackathon.emergency.repository.EmergencyRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmergencyService {
    private final EmergencyRepository emergencyRepository;
    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    public boolean createEmergency(CreateEmergencyRequestModel requestModel) {
        if (emergencyRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Emergency already exists.");
        Emergency emergency = mapper.map(requestModel, Emergency.class);
        emergencyRepository.save(emergency);
        return true;
    }

    public EmergencyResponseModel findEmergencyById(long id) {
        if (!emergencyRepository.existsById(id))
            return null;
        return mapper.map(emergencyRepository.getById(id), EmergencyResponseModel.class);
    }

    public List<EmergencyResponseModel> findEmergencies() {
        List<Emergency> emergencies = emergencyRepository.findAll();
        return emergencies.stream().map(x -> mapper.map(x, EmergencyResponseModel.class)).collect(Collectors.toList());
    }

    public boolean deleteEmergency(long id) {
        if (!emergencyRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found  by that id.");
        emergencyRepository.delete(emergencyRepository.getById(id));
        return true;
    }

    public boolean updateEmergency(UpdateEmergencyRequestModel requestModel) {
        if (!emergencyRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found by that id");
        Emergency emergency = emergencyRepository.getById(requestModel.getId());
        mapper.map(requestModel, emergency);
        emergencyRepository.save(emergency);
        return true;
    }

    public boolean sendEmergencyMessage(SendEmergencyMessageRequestModel sendEmergencyMessageRequestModel) {
        Emergency emergency = emergencyRepository.getById(sendEmergencyMessageRequestModel.getEmergencyId());
        sendMail(emergency.getEmergencyMail(), emergency.getMailContent(), sendEmergencyMessageRequestModel.getLongitude(), sendEmergencyMessageRequestModel.getLatitudes());
//        User user = authenticationService.getCurrentUser();
//        List<MailsToSendMessage> mailsToSendMessages = user.getMailsToSendMessage();
//        for (MailsToSendMessage mailsToSendMessage : mailsToSendMessages) {
//            sendMail(mailsToSendMessage.getMail(), emergency.getMailContent(), sendEmergencyMessageRequestModel.getLongitude(), sendEmergencyMessageRequestModel.getLatitudes());
//        }
        return true;
    }

    public void sendMail(String to, String content, BigDecimal longitude, BigDecimal latitude) {
        final String username = "bugrayus@gmail.com";
        final String password = "x8dg49vh13";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("ACILLLLLLL");
            message.setText(content + longitude + "," + latitude);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
