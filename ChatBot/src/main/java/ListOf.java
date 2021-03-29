import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListOf extends Bot {

    public void listCommand(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        Pattern p = Pattern.compile("\\.+робити\\.|робити|\\.+робити|робити+\\.|Куди піти\\.|куди піти\\." +
                "|\\.куди піти\\.|\\.куди піти|Куди піти|куди піти|куди сходити|куди сходити\\.|\\.куди сходити\\.|" +
                "\\.куди сходити|Куди сходити\\.|нудно|\\.нудно|нудно\\.|\\.нудно\\.|\\.Куди сходити|\\.Куди сходити\\.|" +
                "Куди сходити");
        Matcher m = p.matcher(txt);



        if (m.find()){
            sendMsg(msg, "Hear will be list");
        } else {

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
