package ru.mail.polis.homework.collections.mail;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Нужно создать сервис, который умеет обрабатывать письма и зарплату.
 * Письма состоят из получателя, отправителя, текста сообщения
 * Зарплата состоит из получателя, отправителя и суммы.
 *
 * В реализации нигде не должно быть классов Object и коллекций без типа. Используйте дженерики.
 * Всего 7 тугриков за пакет mail
 */
public class MailService <T extends Mail <?>> implements Consumer<T>{

    private final Map<String, List<T>> mailBox = new HashMap<>();
    private final Map<String, Integer> senderCount = new HashMap<>();
    private final Map<String, Integer> recepCount = new HashMap<>();

    /**
     * С помощью этого метода почтовый сервис обрабатывает письма и зарплаты
     * 1 тугрик
     */
    @Override
    public void accept(T o) {
        String sender = (String) o.getSender();
        String receipent = (String) o.getReceipent();

        List<T> receipMails = mailBox.computeIfAbsent(receipent, k -> new ArrayList<>());
        receipMails.add(o);

        senderCount.put(sender, senderCount.getOrDefault(sender, 0) + 1);
        recepCount.put(receipent, recepCount.getOrDefault(sender, 0) + 1);


    }

    /**
     * Метод возвращает мапу получатель -> все объекты которые пришли к этому получателю через данный почтовый сервис
     * 1 тугрик
     */
    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }

    /**
     * Возвращает самого популярного отправителя
     * 1 тугрик
     */
    public String getPopularSender() {
        String popSender = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : senderCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popSender = entry.getKey();
            }
        }
        return popSender;
    }

    /**
     * Возвращает самого популярного получателя
     * 1 тугрик
     */
    public String getPopularRecipient() {
        String popRecep = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : recepCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popRecep = entry.getKey();
            }
        }
        return popRecep;
    }

    /**
     * Метод должен заставить обработать service все mails.
     * 1 тугрик
     */
    public static  <T extends Mail<?>> void process(MailService<T> service, List<T>mails) {
        for (T mail : mails) {
            service.accept(mail);
        }
    }
}
