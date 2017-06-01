package ru.anatoli.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.anatoli.mantis.models.MailMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anatoli.anukevich on 5/30/2017.
 */
public class MailHelper {
    private Wiser wiser;
    private ApplicationManager applicationManager;

    public MailHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail(int count, long timeout) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error();
    }

    public static MailMessage toModelMail(WiserMessage message) {
        try {
            MimeMessage mm = message.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}