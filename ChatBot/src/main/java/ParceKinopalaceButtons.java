import net.sf.saxon.query.UpdateAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class ParceKinopalaceButtons extends Bot {

    public int pages = 0;

    public void button(Update update) throws IOException {

      //  if (update.hasMessage() && update.getMessage().hasText()) {
////            String message_text = update.getMessage().getText();
////            long chat_id = update.getMessage().getChatId();
////            if (update.getMessage().getText().equals("/start")) {
////
////
////                SendMessage message = new SendMessage() // Create a message object object
////                        .setChatId(chat_id)
////                        .setText("You send /start");
////                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
////                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
////                List<InlineKeyboardButton> rowInline = new ArrayList<>();
////
////                rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
////                // Set the keyboard to the markup
////                rowsInline.add(rowInline);
////                // Add it to the message
////                markupInline.setKeyboard(rowsInline);
////                message.setReplyMarkup(markupInline);
////                try {
////                    execute(message); // Sending our message object to user
////                } catch (TelegramApiException e) {
////                    e.printStackTrace();
////                }
////            } else {
////
////            }
//
//        } else if (update.hasCallbackQuery()) {
//            // Set variables
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//
//
//            if (call_data.equals("update_msg_text"))  {
//
//
//
//
//            }
//
//        }


        if (update.getMessage().getText().equals("Кінопалац")) {



            Document docMultiplex = Jsoup.connect("http://kinopalace.lviv.ua").get();
            Elements aElements = docMultiplex.getElementsByAttributeValue("class", "poster-title");


            for (Element aElement : aElements.subList(0, 6)) {
                Element cElement = aElement.child(0);
                String urlMultiplex = cElement.attr("href");
                String titleMultiplex = cElement.text();

                long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("--------");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                rowInline.add(new InlineKeyboardButton().setText(titleMultiplex).setUrl(urlMultiplex).setCallbackData("update_msg_text"));
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



    public void moreButtons (Update update) throws IOException {




        Document docMultiplex = Jsoup.connect("http://kinopalace.lviv.ua").get();
        Elements aElements = docMultiplex.getElementsByAttributeValue("class", "poster-title");


        for (Element aElement : aElements.subList(7, 15)) {

                Element cElement = aElement.child(0);
                String urlMultiplex = cElement.attr("href");
                String titleMultiplex = cElement.text();


                SendMessage message = new SendMessage()
                        .setChatId(update.getCallbackQuery().getMessage().getChatId())
                        .setText("--------");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText(titleMultiplex).setUrl(urlMultiplex));
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
        InlineKeyboardMarkup markupInline2 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        rowInline2.add(new InlineKeyboardButton().setText("Ще").setCallbackData("morePalace"));
        // Set the keyboard to the markup
        rowsInline2.add(rowInline2);
        // Add it to the message
        markupInline2.setKeyboard(rowsInline2);
        message_for_key.setReplyMarkup(markupInline2);
        try {
            execute(message_for_key); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        }


//        public void MovieKey(Update update) throws IOException {
//
//            Document docMultiplex = Jsoup.connect("http://kinopalace.lviv.ua/films/mesniki-viina-neskincsennosti").get();
//            Elements aElements = docMultiplex.getElementsByAttributeValue("class", "title-data-film");
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//
//            List answerlist = new LinkedList();
//            aElements.forEach(aElement -> {
//                Element cElement = aElement;
//                String answer = cElement.text();
//
//
//
//                answerlist.add(answer);
//
//
//
//                });
//
//            EditMessageText new_message = new EditMessageText()
//                    .setChatId(chat_id)
//                    .setMessageId(toIntExact(message_id))
//                    .setText(answerlist.toString());
//            try {
//                execute(new_message);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//        public void more(Update update) throws IOException {
//
//
//
//        }


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

