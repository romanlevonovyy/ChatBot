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
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FoodParce extends Food {




    public void parceBest(Update update) throws IOException {


        Document docMultiplex = Jsoup.connect("http://lviv-online.com/ua/rest/restaurant/").get();
        Elements aElements = docMultiplex.getElementsByAttributeValue("class", "places--title");
        Elements lElements = docMultiplex.getElementsByAttributeValue("class", "places--logo");
        Elements tElements = docMultiplex.getElementsByAttributeValue("itemprop", "address");

        Message msg = update.getMessage();
        String txt = msg.getText();


        for (int i = 1; i <= 6; i++) {


            for (Element aElement : aElements.subList(i - 1, i)) {

                Element cElement = aElement;
                String title = cElement.text();


                for (Element tElement : tElements.subList(i - 1, i)) {
                    Element kElement = tElement;
                    String info = kElement.text();

                    for (Element nElement : lElements.subList(i - 1, i)) {
                        Element bElement = nElement;
                        String url = bElement.attr("href");

                        long chat_id = update.getMessage().getChatId();
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setText(title + "\n" + info);

                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();

                        rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl(url).setCallbackData("update_msg_text"));
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
    }


    public void moreFood(Update update) throws IOException {



        Document docMultiplex = Jsoup.connect("http://lviv-online.com/ua/rest/restaurant/").get();
        Elements aElements = docMultiplex.getElementsByAttributeValue("class", "places--title");
        Elements lElements = docMultiplex.getElementsByAttributeValue("class", "places--logo");
        Elements tElements = docMultiplex.getElementsByAttributeValue("itemprop", "address");



        for (int i = 7; i <= 10; i++) {


            for (Element aElement : aElements.subList(i - 1, i)) {

                Element cElement = aElement;
                String title = cElement.text();


                for (Element tElement : tElements.subList(i - 1, i)) {
                    Element kElement = tElement;
                    String info = kElement.text();

                    for (Element nElement : lElements.subList(i - 1, i)) {
                        Element bElement = nElement;
                        String url = bElement.attr("href");

                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                                .setText(title + "\n" + info);

                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();

                        rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl(url).setCallbackData("" +
                                "update_msg_text"));
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
    }

    public void foodInSykhiv(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();



        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(update.getMessage().getChatId())
                .setText("Медобори\n" +
                        "Кафе\n" +
                        "Адреса: проспект Червоної Калини, 102, Львів, Львівська область, 79000\n" +
                        "Години роботи: \n" +
                        "вівторок\t09–23\n" +
                        "середа\t09–23\n" +
                        "четвер\t09–23\n" +
                        "пʼятниця\t09–23\n" +
                        "субота\t09–23\n" +
                        "неділя\n" +
                        "(День Святої Трійці)\n" +
                        "09–23\n" +
                        "Розклад роботи може бути іншим\n" +
                        "понеділок\t09–23\n" +
                        "Запропонувати зміну\n" +
                        "Телефон: 063 015 8838");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        rowInline.add(new InlineKeyboardButton().setText("Детальніше").setUrl("http://restaurant-cervonaruta.business.site/"));
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

        sendLocation(msg, 49.784657f, 24.058768f);

        sendMsg(msg, "Оазис\n" +
                "Ресторан\n" +
                "Адреса: проспект Червоної Калини, 35, Львів, Львівська область, 79000\n" +
                "Години роботи:  \n" +
                "вівторок\t12–00\n" +
                "середа\t12–00\n" +
                "четвер\t12–00\n" +
                "пʼятниця\t12–00\n" +
                "субота\t12–00\n" +
                "неділя\n" +
                "12–00\n" +
                "понеділок\t12–00" +
                "Телефон: 03222 24554");

        sendMsg(msg, "Кримська перепічка\n" +
                "Кафе\n" +
                "Адреса: проспект Червоної Калини, 62А, Львів, Львівська область, 79000\n" +
                "Години роботи:  \n" +
                "вівторок\t08:30–20\n" +
                "середа\t08:30–20\n" +
                "четвер\t08:30–20\n" +
                "пʼятниця\t08:30–20\n" +
                "субота\t10–20\n" +
                "неділя\n" +
                "10–20\n" +
                "понеділок\t08:30–20" +
                "Телефон: 068 701 2644");

        sendMsg(msg, "Ресторан Червона Рута\n" +
                "Ресторан\n" +
                "Адреса: вулиця Хуторівка, 26, Львів, Львівська область, 79000\n" +
                "Години роботи:  \n" +
                "вівторок\t11–23\n" +
                "середа\t11–23\n" +
                "четвер\t11–23\n" +
                "пʼятниця\t11–23\n" +
                "субота\t11–23\n" +
                "неділя\n" +
                "11–23\n" +
                "понеділок\t11–23" +
                "Телефон: 068 227 4040");

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

