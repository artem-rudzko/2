package helperClass;


import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailAPI {
    public static void sendMessage(String emailSender, String passwordSender, String emailRecipient, String subject, String message)
            throws MessagingException, UnsupportedEncodingException {
        sendSimpleMessage(emailSender, passwordSender, emailSender, emailRecipient, message, subject);
    }

    public static void sendSimpleMessage(
            final String login,
            final String password,
            String from,
            String to,
            String content,
            String subject
    ) throws MessagingException, UnsupportedEncodingException {

        Session session = Session.getInstance(EmailConfig.getEmailConfig(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);

        appendMessageToOutgoing(msg, session, login, password);
    }

    private static void appendMessageToOutgoing(Message msg, Session session, String login, String password) throws MessagingException {
        IMAPStore store = (IMAPStore) session.getStore(EmailConfig.IMAP_STORE_TYPE);
        store.connect(EmailConfig.IMAP_HOST, login, password);
        IMAPFolder folder = (IMAPFolder) store.getDefaultFolder().getFolder(EmailConfig.IMAP_SENT_FOLDER_NAME);
        if (folder.exists() || folder.create(IMAPFolder.READ_WRITE)) {
            if (folder.isOpen() == false) {
                folder.open(Folder.READ_WRITE);
            }

            Message message = new MimeMessage((MimeMessage) msg);
            message.setFlag(Flags.Flag.SEEN, true);
            folder.appendMessages(new Message[]{message});
            folder.close(false);
            store.close();
        }
    }
}
