package Person;

import MailPakcage.AdminSendMailToUsers;
import Object.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Administrator {
    private String login;
    private String password;
    private String e_mail;

    //////// methods Basic //////////

    public void signInAdmin() {
        System.out.println("*** Sign in Admin ***");

        System.out.println("Login: ");
        String loginAdmin = new Scanner(System.in).nextLine();

        System.out.println("Password: ");
        String passwordAdmin = new Scanner(System.in).nextLine();

        System.out.println("Email: ");
        String eMail = new Scanner(System.in).nextLine();
        eMail = eMail.trim();

        if (loginAdmin.equals(getLogin()) && passwordAdmin.equals(getPassword())) {
            System.out.println("Вход выполнен успешно!\n");
            setE_mail(eMail);

            String separator = File.separator;
            String path = "src" + separator + "Person" + separator + "DataAdmin";
            File file = new File(path);

            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.println(getE_mail());
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Message: Wrong login or password!");
            signInAdmin();
        }
    }

    public void addBook() {
        Book book = new Book();

        System.out.println("Введите название книги: ");
        book.setName(new Scanner(System.in).nextLine());
        System.out.println("Введите автора книги: ");
        book.setAuthor(new Scanner(System.in).nextLine());

        book.setDescription("(нет информации)");

        System.out.println("Выберите тип книги: ");
        boolean bool = false;
        do {
            System.out.println("электронный вариант -> [1]\nбумажный вариант    -> [2]");
            int temp = new Scanner(System.in).nextInt();

            if (temp == 1) {
                book.setType("Электронный вариант");
                bool = true;
            }
            else if (temp == 2) {
                book.setType("Твердый переплет");
                bool = true;
            }
        } while (!bool);

        System.out.println("Год издания: ");
        book.setYearOfPublishing(new Scanner(System.in).nextInt());
        System.out.println("Количество страниц: ");
        book.setNumberOfPages(new Scanner(System.in).nextInt());

        System.out.println("----------------------------------");
        System.out.println("Предпросмотр информации: ");
        System.out.println(book);
        System.out.println("----------------------------------");

        System.out.println("     Выверите действие");
        System.out.println("Сохранить книгу в каталоге  -> [1]");
        System.out.println("Заполнить информацию заново -> [2]");
        int temp = new Scanner(System.in).nextInt();

        switch (temp) {
            case 1:
                String separator = File.separator;
                String path = "src"+ separator +"Object"+ separator +"Catalog";
                File file = new File(path);

                try {
                    PrintWriter printWriter =
                            new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

                    printWriter.println(book.getName() + "_" + book.getAuthor() + "_"
                            + book.getDescription() + "_" + book.getType() + "_"
                            + book.getYearOfPublishing() + "_" + book.getNumberOfPages());

                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Книга успешно сохранена");
                break;
            case 2:
                System.out.println("Повторнный ввод информации");
                addBook();
                break;
            default:
                System.out.println("Ошибка ввода!");
                addBook();
                break;
        }
    }

    public void addDescription() {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String stringNameBook = "";

        System.out.println("Введите название книги: ");
        String strName = new Scanner(System.in).nextLine();
        strName = firstUpperCase(methodTrim(strName));

        boolean bool = false;

        for (int i=0; i<arrayList.size(); i++) {
            String strTemp = arrayList.get(i);
            String[] arrayStrTemp = strTemp.split("_");

            if (strName.equals(arrayStrTemp[0])) {
                bool = true;
                System.out.println("Добавьте описание данной книги: ");


                String description = new Scanner(System.in).nextLine();
                description = firstUpperCase(methodTrim(description));
                arrayStrTemp[2] = description;

                StringBuilder stringBuilder = new StringBuilder();

                for (int j=0; j<arrayStrTemp.length; j++) {
                    stringBuilder.append(arrayStrTemp[j]);
                    if ((arrayStrTemp.length-1) != j) {
                        stringBuilder.append("_");
                    }
                }
                arrayList.set(i, String.valueOf(stringBuilder));

                System.out.println("Описание к книге \"" + arrayStrTemp[0] + "\" было успешно добавлено!");
                stringNameBook = arrayStrTemp[0];
            }
        }

        if (!bool) {
            System.out.println("Книга с таким названием не найдена!");
        } else {
            overWriting(arrayList);
            System.out.println("Введите пароль от почты " + getE_mail() + ": ");
            String accountPassword = new Scanner(System.in).nextLine();
            notificationsInEmail(stringNameBook, e_mail, accountPassword);
        }
    }

    public void editBook() {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Введите название книги которое хотите редактировать:");
        String stringEditName = new Scanner(System.in).nextLine();
        stringEditName = firstUpperCase(methodTrim(stringEditName));

        boolean bool = false;

        for (int i=0; i<arrayList.size(); i++) {
            String strTemp = arrayList.get(i);
            String[] arrayStrTemp = strTemp.split("_");

            if (stringEditName.equals(arrayStrTemp[0])) {
                Scanner scannerNewName = new Scanner(System.in);
                System.out.println("Введите новое название книги: ");
                String strNewName = scannerNewName.nextLine();
                strNewName = firstUpperCase(methodTrim(strNewName));
                arrayStrTemp[0] = strNewName;

                StringBuilder stringBuilder = new StringBuilder();

                for (int j=0; j<arrayStrTemp.length; j++) {
                    stringBuilder.append(arrayStrTemp[j]);
                    if ((arrayStrTemp.length-1) != j) {
                        stringBuilder.append("_");
                    }
                }
                arrayList.set(i, String.valueOf(stringBuilder));

                System.out.println("Книга \"" + stringEditName + "\" успешно сменила название " +
                        "на \"" + strNewName + "\"!");
                bool = true;
            }
        }

        if (!bool) {
            System.out.println("Книга с таким названием не найдена!");
        }
        else overWriting(arrayList);
    }

    public void deleteBook() {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Введите название книги которую хотите удалить: ");
        String stringDelete = new Scanner(System.in).nextLine();
        stringDelete = firstUpperCase(methodTrim(stringDelete));

        boolean bool = false;

        for (int i=0; i<arrayList.size(); i++) {
            String stringTemp = arrayList.get(i);
            String[] arrayStringTemp = stringTemp.split("_");

            for (String s : arrayStringTemp) {
                int index = s.indexOf(stringDelete);
                if (index == 0) {
                    arrayList.remove(i);
                    System.out.println("Книга \"" + stringDelete + "\" была успешно удалена!");
                    bool = true;
                }
            }
        }
        if (!bool) {
            System.out.println("Такая книга не найдена!");
        } else overWriting(arrayList);
    }

    //////// methods Modification //////////

    private void overWriting(ArrayList<String> arrayList) {
        String separator = File.separator;
        String path = "src"+ separator +"Object"+ separator +"Catalog";
        File file = new File(path);

        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(path));
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            for (String s : arrayList) {
                printWriter.println(s);
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String methodTrim(String word) {
        word = word.trim();
        return word;
    }

    private String firstUpperCase(String word) {
        if(word == null || word.isEmpty()) {
            return "";
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void notificationsInEmail(String stringNameBook, String accountEmail, String accountPassword) {
        String separator = File.separator;
        String path = "src" + separator + "Person" + separator + "DataUsers";
        File file = new File(path);

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);

            Pattern pattern = Pattern.compile("(e-mail::).+(@)\\w+\\.\\w+(__)");

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                Matcher matcher = pattern.matcher(string);
                while (matcher.find()) {
                    String s = matcher.group();
                    arrayList.add(s.substring(8,s.length()-2));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String s : arrayList) {
            AdminSendMailToUsers adminSendMailToUsers = new AdminSendMailToUsers(s, stringNameBook, accountEmail, accountPassword);
        }
    }

    //////// methods Getter and Setter //////////

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Override
    public String toString() {
        return "\n * Admin *" +
                "\nlogin: " + login +
                "\npassword: " + password +
                "\ne-mail: " + e_mail;
    }
}