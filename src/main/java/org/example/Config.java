package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config extends TelegramLongPollingBot {

    // Возвращает токен бота
    @Override
    public String getBotToken() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("token").toURI());
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Возвращает имя бота
    @Override
    public String getBotUsername() {
        return "forge_universe_roll_bot";
    }

    // Метод для обработки сообщений
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inputMessage = update.getMessage();
            String userMessage = inputMessage.getText().replace("@" + getBotUsername(), "");
            long chatId = inputMessage.getChatId();
            String user = inputMessage.getFrom().getUserName();
            String response =
                    switch (userMessage) {
                        case "/start" -> "Добро пожаловать! \n" + Roll.roll(5);
                        case "/roll" -> Roll.roll();
                        default -> "";
                    };
            // Отправляем ответ пользователю
            SendMessage message = SendMessage.builder().
                    chatId(chatId).text(user + ": " + response)
                    .build();
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
