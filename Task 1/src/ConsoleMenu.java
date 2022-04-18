import Person.Administrator;
import Person.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ConsoleMenu {
    public ConsoleMenu() throws NoSuchAlgorithmException {
        System.out.println("Выберите пользователя:");
        System.out.println("Admin           -> [1]");
        System.out.println("User            -> [2]");
        System.out.println("New User        -> [3]");
        System.out.println("----------------------");
        System.out.println("To exit         -> [0]");
        System.out.print("> ");
        int s = new Scanner(System.in).nextInt();

        switch (s) {
            case 1:
                Administrator administrator = new Administrator();
                logInAdmin(administrator);
                menuAdmin(administrator);
                break;
            case 2:
                User user = new User();
                logInUser(user);
                menuUser(user);
                break;
            case 3:
                newUser();
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Input Error");
        }
    }

    private int underMenu() {
        System.out.println("\nChoose an action: ");
        System.out.println("Back to menu -> [1]");
        System.out.println("Exit         -> [0]");

        System.out.print("> ");
        int s = new Scanner(System.in).nextInt();

        while (s != 0 && s != 1) {
            System.out.print(">");
            s = new Scanner(System.in).nextInt();
        }

        return s;
    }

    private void logInAdmin(Administrator administrator) {
        administrator.setLogin("admin");
        administrator.setPassword("12345");
        administrator.signInAdmin();
    }

    private void menuAdmin(Administrator administrator) {
        System.out.println("        *** Menu Admin ***");
        System.out.println("----------------------------------");
        System.out.println("Add book                    -> [1]");
        System.out.println("Add a description to a book -> [2]");
        System.out.println("Edit Book                   -> [3]");
        System.out.println("Delete book                 -> [4]");
        System.out.println("----------------------------------");
        System.out.println("To exit                     -> [0]");

        System.out.print("> ");
        int s = new Scanner(System.in).nextInt();

        switch (s) {
            case 1:
                administrator.addBook();
                int t1 = underMenu();
                if (t1 == 1) {
                    menuAdmin(administrator);
                }
                else if (t1 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 2:
                administrator.addDescription();
                int t2 = underMenu();
                if (t2 == 1) {
                    menuAdmin(administrator);
                }
                else if (t2 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 3:
                administrator.editBook();
                int t3 = underMenu();
                if (t3 == 1) {
                    menuAdmin(administrator);
                }
                else if (t3 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 4:
                administrator.deleteBook();
                int t4 = underMenu();
                if (t4 == 1) {
                    menuAdmin(administrator);
                }
                else if (t4 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                menuAdmin(administrator);
        }
    }

    private void logInUser(User user) throws NoSuchAlgorithmException {
        user.signInUser();
    }

    private void menuUser(User user) {
        System.out.println("     *** Menu User ***");
        System.out.println("Show catalog          -> [1]");
        System.out.println("Search book           -> [2]");
        System.out.println("Suggest adding a book -> [3]");
        System.out.println("----------------------------");
        System.out.println("To exit               -> [0]");

        System.out.print("> ");
        int s = new Scanner(System.in).nextInt();

        switch (s) {
            case 1:
                user.showCatalog();
                int t1 = underMenu();
                if (t1 == 1) {
                    menuUser(user);
                }
                else if (t1 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 2:
                user.searchBook();
                int t2 = underMenu();
                if (t2 == 1) {
                    menuUser(user);
                }
                else if (t2 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 3:
                user.offerToSendBook();
                int t3 = underMenu();
                if (t3 == 1) {
                    menuUser(user);
                }
                else if (t3 == 0) {
                    System.out.println("Exit");
                }
                break;
            case 0:
                System.out.println("Exit");
            default:
                menuUser(user);
                break;
        }
    }

    private void newUser() {
        User user = new User();
        user.signNewUser();
    }
}