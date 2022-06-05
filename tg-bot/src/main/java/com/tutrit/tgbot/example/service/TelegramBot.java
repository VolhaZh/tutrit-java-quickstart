package com.tutrit.tgbot.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var sendMessage = createResponse(update);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage createResponse(final Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("got your message: " + update.getMessage().getText());
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
