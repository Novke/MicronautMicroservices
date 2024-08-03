package fon.mas.novica.micronaut.service;

import java.util.List;

public interface MyEmail {
    String getRecipient();
    List<String> getCc();
    List<String> getBcc();
    String getSubject();
    String getHtmlBody();
    String getTextBody();
    String getReplyTo();
}