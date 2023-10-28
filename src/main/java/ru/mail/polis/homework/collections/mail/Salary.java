package ru.mail.polis.homework.collections.mail;

/**
 * 1 тугрик
 */
public class Salary<T> extends Mail<T> {
    private T amount;
    public Salary(T sender, T receipent, T amount) {
        super(sender,receipent, amount);
        this.amount = amount;
    }

    public T getAmount() {
        return getContent();
    }



}
