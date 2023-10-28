package ru.mail.polis.homework.collections.mail;

/**
 * 1 тугрик
 */
public class MailMessage<T> extends Mail<T>{

    private T message;
    public MailMessage(T sender, T receipent, T message) {
        super(sender, receipent, message);
        this.message = message;
    }

    public T getMessage() {
        return getContent();
    }



}
