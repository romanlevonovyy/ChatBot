import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsPuppetShow extends Theatres {

    public void parce(Update update) throws IOException {
        Message msg = update.getMessage();


        Document docTeatrKurbasa = Jsoup.connect("https://ticketclub.com.ua/?cg=2&ct=1&org=139").get();
        Elements divElements = docTeatrKurbasa.getElementsByAttributeValue("class", "title");

        for (Element divElement : divElements.subList(0, 5)) {
            Element aElement = divElement;
            String urlTeatrKurbasa = aElement.attr("href");
            String titleTeatrKurbasa = aElement.text();

            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(titleTeatrKurbasa);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl("https://ticketclub.com.ua" + urlTeatrKurbasa));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }



    public void parceMore(Update update) throws IOException {
        Message msg = update.getMessage();


        Document docTeatrKurbasa = Jsoup.connect("https://ticketclub.com.ua/?cg=2&ct=1&org=139").get();
        Elements divElements = docTeatrKurbasa.getElementsByAttributeValue("class", "title");

        for (Element divElement : divElements.subList(6, 11)) {
            Element aElement = divElement;
            String urlTeatrKurbasa = aElement.attr("href");
            String titleTeatrKurbasa = aElement.text();

            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(titleTeatrKurbasa);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl("https://ticketclub.com.ua" + urlTeatrKurbasa));
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        long chat_id = update.getCallbackQuery().getMessage().getChatId();
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
}