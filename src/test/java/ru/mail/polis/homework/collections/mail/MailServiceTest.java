package ru.mail.polis.homework.collections.mail;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServiceTest {

    @Test
    public void accept() {
        MailService<Mail<?>> mailService = new MailService<>();
        MailMessage<String> mailMsg = new MailMessage<>("Alice", "Bob", "Hello");
        mailService.accept(mailMsg);

        Map<String, List<Mail<?>>> mailBox = mailService.getMailBox();
        List<Mail<?>> receivedMails = mailBox.get("Bob");

        assertEquals(1, receivedMails.size());
        assertEquals(mailMsg, receivedMails.get(0));
    }

    @Test
    public void getMailBox() {
        MailService<Mail<?>> mailService = new MailService<>();
        MailMessage<String> mailMsg1 = new MailMessage<>("Alice", "Bob", "Hello");
        MailMessage<String> mailMsg2 = new MailMessage<>("Charlie", "Bob", "Hi");

        mailService.accept(mailMsg1);
        mailService.accept(mailMsg2);

        Map<String, List<Mail<?>>> mailBox = mailService.getMailBox();

        assertEquals(2, mailBox.size());
        assertEquals(new ArrayList<>(List.of(mailMsg1, mailMsg2)), mailBox.get("Bob"));
        assertEquals(new ArrayList<>(List.of(mailMsg1)), mailBox.get("Charlie"));
    }

    @Test
    public void getPopularSender() {
        MailService<Mail<?>> mailService = new MailService<>();
        MailMessage<String> mailMsg1 = new MailMessage<>("Alice", "Bob", "Hello");
        MailMessage<String> mailMsg2 = new MailMessage<>("Alice", "Charlie", "Hi");

        mailService.accept(mailMsg1);
        mailService.accept(mailMsg2);

        assertEquals("Alice", mailService.getPopularSender());
    }

    @Test
    public void getPopularRecipient() {
        MailService<Mail<?>> mailService = new MailService<>();
        MailMessage<String> mailMsg1 = new MailMessage<>("Alice", "Bob", "Hello");
        MailMessage<String> mailMsg2 = new MailMessage<>("Alice", "Bob", "Hi");

        mailService.accept(mailMsg1);
        mailService.accept(mailMsg2);

        assertEquals("Bob", mailService.getPopularRecipient());
    }

    @Test
    public void process() {

        MailMessage<String> mailMsg1 = new MailMessage<>("Alice", "Bob", "Hello");
        MailMessage<String> mailMsg2 = new MailMessage<>("Charlie", "Bob", "Hi");


        List<Mail<?>> mails = new ArrayList<>(Arrays.asList(mailMsg1, mailMsg2));
        MailService<Mail<?>> mailService = new MailService<>();
        MailService.process(mailService, mails);

        Map<String, List<Mail<?>>> mailBox = mailService.getMailBox();

        assertEquals(2, mailBox.size());
        assertEquals(new ArrayList<>(List.of(mailMsg1)), mailBox.get("Bob"));
        assertEquals(new ArrayList<>(List.of(mailMsg2)), mailBox.get("Charlie"));
    }
}
