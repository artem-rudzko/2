package helperClass;
import java.util.Properties;

public class EmailConfig {
    public static final String SMTP_HOST = "smtp.yandex.ru";
    public static final String IMAP_HOST = "imap.yandex.ru";
    public static final Integer SMTP_PORT = 465;
    public static final Integer IMAP_PORT = 465;
    public static final String IMAP_SENT_FOLDER_NAME = "Отправленные";
    public static final String IMAP_STORE_TYPE = "imaps";
    public static final String ENCODING = "UTF-8";

    public static Properties getEmailConfig(){
        Properties props = System.getProperties();

        props.put("mail.smtp.port", SMTP_PORT.toString());
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", SMTP_PORT.toString());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        props.put("mail.imap.port", IMAP_PORT.toString());
        props.put("mail.imap.host", IMAP_HOST);
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.ssl.enable", "true");

        return props;
    }

}
