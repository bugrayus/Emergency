package vodafone.hackathon.emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vodafone.hackathon.emergency.model.MailsToSendMessage;

@Repository
public interface MailsToSendMessageRepository extends JpaRepository<MailsToSendMessage, Long> {
    boolean existsByMail(String mail);
}
