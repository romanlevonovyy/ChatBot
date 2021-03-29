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

public class ParcePlanetaButtons extends Cinema {

    public int value = 6;


    public void button(Update update) throws IOException {

        Document docPlaneta = Jsoup.connect("https://planetakino.ua/lvov/showtimes").get();
        Elements pElements = docPlaneta.getElementsByAttributeValue("class", "movie-title");

        for (Element pElement : pElements.subList(0, 5)) {
            Element bElement = pElement.child(0);
            String urlPlaneta = bElement.attr("href");
            String titlePlaneta = bElement.text();


            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(titlePlaneta);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl("https://planetakino.ua" + urlPlaneta).setCallbackData("morePlaneta"));
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

    public void moreButtons(Update update) throws IOException {


        Document docPlaneta = Jsoup.connect("https://planetakino.ua/lvov/showtimes").get();
        Elements pElements = docPlaneta.getElementsByAttributeValue("class", "movie-title");

            for (Element pElement : pElements.subList(6, 12)) {
                Element bElement = pElement.child(0);
                String urlPlaneta = bElement.attr("href");
                String titlePlaneta = bElement.text();

                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(update.getCallbackQuery().getMessage().getChatId())
                        .setText(titlePlaneta);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl("https://planetakino.ua" + urlPlaneta).setCallbackData("update_msg_text"));
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
    }


