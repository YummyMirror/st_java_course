package ru.anatoli.mantis.models;

/**
 * Created by anatoli.anukevich on 5/30/2017.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
