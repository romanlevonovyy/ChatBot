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

public class ParsOpera extends Theatres {

    public void parce(Update update) throws IOException {
        Message msg = update.getMessage();


        Document docTeatrKurbasa = Jsoup.connect("https://opera.lviv.ua/afisha/").get();
        Elements divElements = docTeatrKurbasa.getElementsByAttributeValue("class", "btn btn--type4");
        Elements divElementsForTitle = docTeatrKurbasa.getElementsByAttributeValue("class", "a-face__title");


        for (int i = 1; i <= 5; i++) {
            for (Element divElement1 : divElementsForTitle.subList(i - 1, i)) {
                Element aElement = divElement1;
                String titleTeatrKurbasa = aElement.text();

                for (Element divElement : divElements.subList(i - 1, i)) {
                    Element bElement = divElement;
                    String urlTeatrKurbasa = bElement.attr("href");
                    long chat_id = update.getMessage().getChatId();
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(titleTeatrKurbasa);
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();

                    rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl(urlTeatrKurbasa));
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

        }
    }

    public void parceMore(Update update) throws IOException {
        Message msg = update.getMessage();


        Document docTeatrKurbasa = Jsoup.connect("https://opera.lviv.ua/afisha/").get();
        Elements divElements = docTeatrKurbasa.getElementsByAttributeValue("class", "btn btn--type4");
        Elements divElementsForTitle = docTeatrKurbasa.getElementsByAttributeValue("class", "a-face__title");


        for (int i = 6; i <= 11; i++) {
            for (Element divElement1 : divElementsForTitle.subList(i - 1, i)) {
                Element aElement = divElement1;
                String titleTeatrKurbasa = aElement.text();

                for (Element divElement : divElements.subList(i - 1, i)) {
                    Element bElement = divElement;
                    String urlTeatrKurbasa = bElement.attr("href");

                    long chat_id = update.getCallbackQuery().getMessage().getChatId();
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(titleTeatrKurbasa);
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();

                    rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl(urlTeatrKurbasa));
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

        }

        long chat_id = update.getCallbackQuery().getMessage().getChatId();
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