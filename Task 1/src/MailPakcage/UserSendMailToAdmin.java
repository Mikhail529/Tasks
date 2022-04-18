package MailPakcage;

public class UserSendMailToAdmin {
    public UserSendMailToAdmin(String eMail, String myAccountEmail, String password, String pathToFile) {
        UserMail userMail = new UserMail();
        userMail.sendMail(eMail, myAccountEmail, password, pathToFile);
    }
}