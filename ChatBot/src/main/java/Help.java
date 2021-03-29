import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Help extends Bot {

    public void helpCommand(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        if (txt.equals("/help")){
            sendMsg(msg, "Я можу розповідати про різні події у Львові. Можу розвоповісти де поїсти, на який фільм"+
                    " сходити, про спортивні заходи та на яку виставу піти у театр. Щоб побачити інформацію, просто" +
                    " напишіть <Куди сходити>");
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
