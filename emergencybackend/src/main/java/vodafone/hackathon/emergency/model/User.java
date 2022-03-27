package vodafone.hackathon.emergency.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
@ToString
@RequiredArgsConstructor
public class User extends BaseModel {
    @Email
    private String mail;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @ToString.Exclude
    private List<MailsToSendMessage> mailsToSendMessage;

    public void addChild(MailsToSendMessage mailToSendMessage) {
        this.mailsToSendMessage.add(mailToSendMessage);
    }
}
