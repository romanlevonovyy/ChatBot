import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Cinema extends Bot {

    public void cinemaCommand(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        Pattern p = Pattern.compile("\\.+\\sкіно\\s+\\.|кіно|\\.+\\sкіно|кіно\\s+\\.|фільм|\\.фільм|фільм\\.|\\.фільм\\.|" +
                "\\.+\\sКіно\\s+\\.|Кіно|\\.+\\sКіно|Кіно\\s+\\.|Фільм|\\.Фільм|Фільм\\.|\\.Фільм\\.|\\.Жерти|Жерти\\.|\\.Жерти\\.");
        Matcher m = p.matcher(txt);
        ParceKinopalaceButtons parceKinopalaceButtons = new ParceKinopalaceButtons();
        ParcePlanetaButtons parcePlanetaButtons = new ParcePlanetaButtons();
        ParceMultiplexButtons parceMultiplexButtons = new ParceMultiplexButtons();



        if (txt.equals("я задовбався")){
            sendMsg(msg, "Йди спати, придурок!!!");
        }

// Creating a buttons
        if (m.find()) {

            SendMessage message = new SendMessage()
                    .setChatId(msg.getChatId())
                    .setText("Виберіть для себе найзручніший кінотеатр");


                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Планета кіно");
                row.add("Multiplex");
                row.add("Кінопалац");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message);
                } catch (TelegramApiException e2) {
                    e2.printStackTrace();
                }

        }

//binding buttons
        if (txt.equals("Кінопалац")) {

            try {
                parceKinopalaceButtons.button(update);
            } catch (IOException e) {
                e.printStackTrace();
            }

//creating key for more
            long chat_id = update.getMessage().getChatId();
            SendMessage message_for_key = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText("--------");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("morePalace"));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message_for_key.setReplyMarkup(markupInline);
            try {
                execute(message_for_key); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }



        if (txt.equals("Планета кіно")) {

            try {
                parcePlanetaButtons.button(update);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            long chat_id = update.getMessage().getChatId();
            SendMessage message_for_key = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText("--------");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("morePlaneta"));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message_for_key.setReplyMarkup(markupInline);
            try {
                execute(message_for_key); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

        if (txt.equals("Multiplex")) {

            try {
                parceMultiplexButtons.button(update);
            } catch (IOException e) {
                e.printStackTrace();
            }

            long chat_id = update.getMessage().getChatId();
            SendMessage message_for_key = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText("--------");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("moreMultiplex"));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message_for_key.setReplyMarkup(markupInline);
            try {
                execute(message_for_key); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }

        if (txt.equals("Більше фільмів з планети кіно")) {
            try {
                parcePlanetaButtons.moreButtons(update);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (txt.equals("Більше фільмів з кінопалацу")) {
            try {
                parceKinopalaceButtons.moreButtons(update);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        if (txt.equals("Більше фільмів з Multiplex")) {
//            try {
//                parceMultiplexButtons.moreButtons(update);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }




    }







        private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);

        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    private void sendLocation(Message msg, Float latitude, Float longitude) {
        SendLocation sendLoc = new SendLocation();
        sendLoc.setChatId(msg.getChatId());
        sendLoc.setLongitude(longitude);
        sendLoc.setLatitude(latitude);
        try {
            sendLocation(sendLoc);
        } catch (TelegramApiException e1){
            e1.printStackTrace();
        }

    }

}
