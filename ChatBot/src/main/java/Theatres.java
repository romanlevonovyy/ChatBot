import org.telegram.telegrambots.api.methods.send.SendMessage;
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

public class Theatres extends Bot {


    public void theatreCommand(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        Pattern p = Pattern.compile("\\.+театр\\.|театр|\\.+театр|театр+\\.|вистава");
        Matcher m = p.matcher(txt);
        ParsTeatrKurbasa parsTeatrKurbasa = new ParsTeatrKurbasa();
        ParsPuppetShow parsPuppetShow = new ParsPuppetShow();
        ParsHumanAndPuppet parsHumanAndPuppet = new ParsHumanAndPuppet();
        ParsZankovecka parsZankovecka = new ParsZankovecka();
        ParsOpera parsOpera = new ParsOpera();

        if (txt.equals("Theatre")) {

            sendMsg(msg, "https://www.google.com/maps/search/%D1%82%D0%B5%D0%B0%D1%82%D1%80+near+Marii+Zan'kovets'koi+Street," +
                    "+Lviv,+Lviv+Oblast/@49.841414,24.0438235,17z/data=!3m1!4b1?hl=en-US");


            SendMessage message = new SendMessage()
                    .setChatId(msg.getChatId())
                    .setText("Виберіть локацію театру");


            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e2) {
                e2.printStackTrace();
            }


        }


        if (txt.equals("Курбаса")) {

            try {
                parsTeatrKurbasa.parce(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("moreKurbas"));
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






        if (txt.equals("Ляльковий")) {

            try {
                parsPuppetShow.parce(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("morePuppets"));
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


        if (txt.equals("І люди, і ляльки")) {

            try {
                parsHumanAndPuppet.parce(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("morePuppetsAndHuman"));
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


        if (txt.equals("Заньковецька")) {

            try {
                parsZankovecka.parce(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("moreZankovetska"));
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



        if (txt.equals("Оперний")) {

            try {
                parsOpera.parce(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("moreOpera"));
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


        if (m.find()) {


            SendMessage message = new SendMessage()
                    .setChatId(msg.getChatId())
                    .setText("Виберіть для себе найзручніший театр");


            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("Курбаса");
            row.add("Ляльковий");
            row.add("І люди, і ляльки");
            row.add("Заньковецька");
            row.add("Оперний");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e2) {
                e2.printStackTrace();
            }


        }


    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}