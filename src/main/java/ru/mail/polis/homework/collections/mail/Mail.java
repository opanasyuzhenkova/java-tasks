package ru.mail.polis.homework.collections.mail;

/**
 * 1 тугрик
 */
public class Mail<T> {

    private T sender;
    private T receipent;
    private T content;

//    public Mail() {
//        this.sender = sender;
//        this.receipent = receipent;
//        this.content = content;
//    }

    public Mail(T sender, T receipent, T content) {
        this.sender = sender;
        this.receipent = receipent;
        this.content = content;
    }

    public T getSender() {
        return sender;
    }

    public T getReceipent() {
        return receipent;
    }

    public T getContent() {
        return content;
    }


}
