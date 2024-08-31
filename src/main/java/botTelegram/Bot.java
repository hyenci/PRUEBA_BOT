package botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "madi_H_bot";
    }

    @Override
    public String getBotToken() {
        return "7317069659:AAEq4fiBs1cYHsnszwmE_sCEIqvoPV4J8bU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickname = update.getMessage().getFrom().getUserName();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String mensaje1 = update.getMessage().getText();
            long who1
                    = update.getMessage().getChatId();

            System.out.println(who1);
            if (mensaje1.equals("/Info")) {
                enviarInformacion(who1);


            } else if (mensaje1.equals("/progra")) {
                prograEnviar(who1);
            } else if (mensaje1.equals("/Holi")) {
                holi(who1, nombre);
            } else if (mensaje1.startsWith("/cambio")) {
                cambio(who1,mensaje1);
            } else if (mensaje1.startsWith("/grupal")) {
               enviarMensaje(mensaje1);
            }

        }





    }




    private void enviarInformacion(long who) {
        String informacion = "Yenci María Hernández Martínez 0905-23-6756 cuarto semestre";
        sendText(who, informacion);
    }

    private void prograEnviar(long who) {

        String comentario = "En programación, una clase es como ese plano. Es una plantilla o molde que define cómo serán los objetos que vamos a crear. En otras palabras, una clase especifica los atributos (características) y los métodos (acciones) que tendrán los objetos que se creen a partir de ella.";
      sendText(who, comentario);
    }

    private void holi (long who, String nombre) {


        String fecha = new SimpleDateFormat("EEEE d 'de' MMMM", new Locale("es", "ES")).format(new Date());
        String saludarXD ="HOLI "+""+nombre+"GUSTO DE VERTE"+""+fecha;

        sendText(who, saludarXD);
    }

    private void cambio (long who, String mensajeS) {

        String[] seccion = mensajeS.split(" ");
        try {
            double euros = Double.parseDouble(seccion[1]);
            double conversion = 8.54;
            double quetzales = euros * conversion;
            sendText(who, String.format("La conversion es %.2f", quetzales));
        } catch (NumberFormatException e) {
            sendText(who, "Por favor, introduce un cantidad válida.");
        }



    }

    private void enviarMensaje(String mensaje) {
        List<Long> whos = List.of(
                817649647L,
                6956666969L,
                6421826691L,
                6082604734L);

        String[] seccion = mensaje.split(" ", 2);
        String mensaje2 = seccion[1];
        for (Long who : whos) {
            sendText(who, mensaje2);
        }
    }












    public void sendText(Long who, String what){
        SendMessage mensaje = new SendMessage();
        mensaje.setChatId(who);
        mensaje.setText(what);
        try {
            execute(mensaje);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}



