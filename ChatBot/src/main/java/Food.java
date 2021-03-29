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

public class Food extends Bot {

    public void foodCommand(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        Pattern p = Pattern.compile("\\.+поїсти\\.|поїсти|\\.+поїсти|поїсти+\\.|\\.+їсти|\\.+їсти+\\.|їсти+\\.|їсти|" +
                "жерти|\\.жерти|жерти\\.|\\.жерти\\.|\\.+Поїсти\\.|Поїсти|\\.+Поїсти|Поїсти+\\.|\\.+Їсти|\\.+Їсти+\\.|Їсти+\\.|Їсти|" +
                "\\.Жерти|Жерти\\.|\\.Жерти\\.");
        Matcher m = p.matcher(txt);

        Pattern pForClose = Pattern.compile("ближчі|\\.ближ|ближ\\.|\\.ближ\\.|Ближчі|\\.Ближ|Ближ\\.|\\.Ближ\\.");
        Matcher mForClose = pForClose.matcher(txt);

        Pattern pForBest = Pattern.compile("найкращі|\\.найкр|найкр\\.|\\.найкр\\.|Найкращі|\\.Найкр|Найкр\\.|\\.Найкр\\.");
        Matcher mForBest = pForBest.matcher(txt);

        FoodParce foodParce = new FoodParce();


        if (m.find()) {
            sendMsg(msg, "Проголодався? Окей, ти хочеш список найкращих закладів міста," +
                    " чи ті заклади, що ближче до тебе?");
        }

        if (mForClose.find()) {
            SendMessage message = new SendMessage()
                    .setChatId(msg.getChatId())
                    .setText("Виберіть район");


            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("Сихівський район");//ПРОПИСУЙТЕ ТУТ НАЗВИ КНОПОК РАЙОНІВ
            row.add("Шевченківський район");
            row.add("Личаківський район");
            row.add("Залізничний район");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e2) {
                e2.printStackTrace();
            }
        }

        if (mForBest.find()) {
            try {
                foodParce.parceBest(update);
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

            rowInline.add(new InlineKeyboardButton().setText("Ще").setCallbackData("moreFood"));
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
        private void sendMsg (Message msg, String text){
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



