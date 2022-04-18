package MailPakcage;

public class AdminSendMailToUsers {
    public AdminSendMailToUsers(String eMail, String stringNameBook, String accountEmail, String accountPassword) {
        AdminMail adminMail = new AdminMail();
        adminMail.sendMail(eMail, stringNameBook, accountEmail, accountPassword);
    }
}