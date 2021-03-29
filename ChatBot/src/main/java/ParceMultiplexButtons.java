import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParceMultiplexButtons extends Cinema {




    public void button (Update update) throws IOException{
        Document docMultiplex = Jsoup.connect("https://multiplex.ua/ua/cinema/lviv/victoriagardens").get();
        Elements aElements = docMultiplex.getElementsByAttributeValue("class", "info");



        for (Element aElement : aElements.subList(0, 5)) {
            Element cElement = aElement.child(0);
            String urlMultiplex = cElement.attr("href");
            String title = cElement.text();


            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage()
                    .setChatId(chat_id);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText(title).setUrl("https://multiplex.ua" + urlMultiplex).setCallbackData("update_msg_text"));
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void moreButtons(Update update) throws IOException {
        Document docMultiplex = Jsoup.connect("https://multiplex.ua/ua/cinema/lviv/victoriagardens").get();
        Elements aElements = docMultiplex.getElementsByAttributeValue("class", "info");


        for (Element aElement : aElements.subList(6, 13)) {
            Element cElement = aElement.child(0);
            String urlMultiplex = cElement.attr("href");
            String title = cElement.text();



            SendMessage message = new SendMessage()
                    .setChatId(update.getCallbackQuery().getMessage().getChatId());
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(new InlineKeyboardButton().setText(title).setUrl("https://multiplex.ua" + urlMultiplex).setCallbackData("moreMultiplex"));
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message);
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



}
