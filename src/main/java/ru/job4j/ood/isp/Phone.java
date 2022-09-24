package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP: прием и отправка SMS на стационарных
 * телефонах невозможны.
 */
public interface Phone {
    boolean dial(String number);

    boolean take(String number);

    boolean sendSMS(String number, String message);

    String receiveSMS(String number);
}
