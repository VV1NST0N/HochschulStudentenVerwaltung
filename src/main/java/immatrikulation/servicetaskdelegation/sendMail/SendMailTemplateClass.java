package immatrikulation.servicetaskdelegation.sendMail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendMailTemplateClass {
    private static final String HOST = "smtp.googlemail.com";
    private static final String USER = "bernd.camunda@gmail.com";
    private static final String PWD = "12345camunda";
    private static final Integer PORT = 465;

    public void doSendMail(String gender, String nachname, String body) throws EmailException {
        Email email = new SimpleEmail();

        email.setCharset("utf-8");
        email.setHostName(HOST);
        email.setAuthentication(USER, PWD);
        email.setSmtpPort(PORT);
        email.setSSLOnConnect(true);
        email.setFrom("noreply@learnbpm.org");
        email.setSubject("Hochschule Riedtal");
        email.setMsg("Sehr "+ getformOfAddress(gender) + " " + nachname +  body + "\n Mit freundlichen Grüßen \n Studiensekretariat Hochschule Riedberg");
        email.addTo("bernd.camunda@gmail.com");
        email.send();
    }

    private String getformOfAddress(String gender) {
        if (gender.equals("Frau")) {
            return "geehrte";
        }else if (gender.equals("Herr")){
            return "geehrter";
        }else{
            return "geehrte*r";
        }
    }
}
