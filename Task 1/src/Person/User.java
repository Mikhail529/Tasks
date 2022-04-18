package Person;

import MailPakcage.UserSendMailToAdmin;

import java.io.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String login;
    private String password;
    private String e_mail;

    //////// methods Basic //////////

    public void signInUser() throws NoSuchAlgorithmException {
        System.out.println("*** Sign in User ***");
        System.out.println("login: ");
        setLogin(new Scanner(System.in).nextLine());

        System.out.println("Password: ");
        setPassword(new Scanner(System.in).nextLine());

        String separator = File.separator;
        String path = "src" + separator + "Person" + separator + "DataUsers";
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

        Pattern pattern1 = Pattern.compile("(__login::).{5,15}(__)");
        Pattern pattern2 = Pattern.compile("(__password::)\\w{32}");
        Pattern pattern3 = Pattern.compile("(__e-mail::).+(__)");

        String stringLogin = "__login::" + getLogin() + "__";
        String stringPassword = "__password::" + cryptoMD5(password);

        boolean loginBoll = false;
        boolean passwordBool = false;

        for (String s : arrayList) {
            Matcher matcher1 = pattern1.matcher(s);
            while (matcher1.find()) {
                String tempLogin = matcher1.group();
                if (tempLogin.equals(stringLogin)) {
                    Matcher matcher2 = pattern2.matcher(s);
                    while (matcher2.find()) {
                        String tempPassword = matcher2.group();
                        if (tempPassword.equals(stringPassword)) {
                            passwordBool = true;

                            Matcher matcher3 = pattern3.matcher(s);
                            while (matcher3.find()) {
                                String str = matcher3.group();
                                str = str.substring(10,str.length()-2);
                                setE_mail(str);
                            }

                        }
                    }
                    loginBoll = true;
                }
            }
        }
        if (passwordBool) {
            System.out.println("Вход успешно выполнен!");
        }
        else if (!passwordBool && loginBoll) {
            System.out.println("Не верный пароль!");
        }
        else {
            System.out.println("Такого логина не существует!");
        }
    }

    public void signNewUser() {
        System.out.println("\t\tAttention!");
        System.out.println("Логин и пароль не должны быть:");
        System.out.println("- меньше 5 и больше 15 символов");
        System.out.println("- не должен содержать пробелы");
        System.out.println("Введите логин:");
        setLogin(new Scanner(System.in).nextLine());

        System.out.println("Введите e-mail: ");
        setE_mail(new Scanner(System.in).nextLine());

        System.out.println("Введите пароль:");
        setPassword(new Scanner(System.in).nextLine());

        if (!verificationPassword(login, password)) {
            signNewUser();
        }
        else if (duplicatePassword(login)) {
            System.out.println("Такой логин уже существует!");
            signNewUser();
        }
        else {
            saveUserData(login, e_mail, password);
            System.out.println("Регистрация прошла успешно!");
        }
    }

    //////// methods Verification //////////

    private void saveUserData(String login, String e_mail, String password) {
        String separator = File.separator;
        String path = "src" + separator + "Person" + separator + "DataUsers";
        File file = new File(path);

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

            printWriter.println("__login::" + login + "__e-mail::" + e_mail + "__password::" + cryptoMD5(password));
            printWriter.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private boolean verificationPassword(String login, String password) {
        boolean bool = true;

        if (getLogin().length() > 15 || getLogin().length() < 5) {
            bool = false;
        }
        if (getPassword().length() > 15 || getPassword().length() < 5) {
            bool = false;
        }

        char[] arrayLogin = login.toCharArray();
        String probel= " ";
        String s = "";
        for (char c : arrayLogin) {
            s = String.valueOf(c);
            if (s.equals(probel)) {
                bool = false;
            }
        }

        char[] arrayPassword = password.toCharArray();
        for (char c : arrayPassword) {
            s = String.valueOf(c);
            if (s.equals(probel)) {
                bool = false;
            }
        }
        return bool;
    }

    private boolean duplicatePassword(String login) {
        boolean tempBoolean = false;

        String separator = File.separator;
        String path = "src" + separator + "Person" + separator + "DataUsers";
        File file = new File(path);

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                arrayList.add(stringScan);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pattern pattern1 = Pattern.compile("(__login::).{5,15}(__)");

        String temp = "__login::" + login + "__";

        for (String s : arrayList) {
            Matcher matcher = pattern1.matcher(s);
            while (matcher.find()) {
                if (matcher.group().equals(temp)) {
                    tempBoolean = true;
                }
            }
        }
        return tempBoolean;
    }

    private String cryptoMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] byteData = md.digest();

        //конвертируем байт в шестнадцатеричный формат первым способом
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    //////// methods Basic //////////

    public void showCatalog() {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                System.out.println("Название книги:\t\t" + strings[0]);
                System.out.println("Автор:\t\t\t\t" + strings[1]);
                System.out.println("Описание:\t\t\t" + strings[2]);
                System.out.println("Тип:\t\t\t\t" + strings[3]);
                System.out.println("Год публикации:\t\t" + strings[4]);
                System.out.println("Количество страниц:\t" + strings[5]);
                System.out.println();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchBook() {
        System.out.println("      Поиск книг по:");
        System.out.println("по названию           -> [1]");
        System.out.println("по автору             -> [2]");
        System.out.println("по типу               -> [3]");
        System.out.println("по году публикации    -> [4]");
        System.out.println("по количеству страниц -> [5]");

        int temp = new Scanner(System.in).nextInt();

        switch (temp) {
            case 1:
                System.out.println("Введите название книги:");
                String var1 = new Scanner(System.in).nextLine();
                searchName(var1);
                break;
            case 2:
                System.out.println("Введите автора:");
                String var2 = new Scanner(System.in).nextLine();
                searchAuthor(var2);
                break;
            case 3:
                System.out.println("Выберите тип:\nэлектронный вариант -> [1]\nбумажный вариант    -> [2]");
                String var3 = "";

                boolean bool = false;
                do {
                    int t = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (t == 1) {
                        var3 = "Электронный вариант";
                        bool = true;
                    }
                    else if (t == 2) {
                        var3 = "Твердый переплет";
                        bool = true;
                    }
                    else System.out.println("Message: Ошибка выбора! Выберите тип:" +
                                "\nэлектронный вариант -> [1]\nбумажный вариант    -> [2]");
                } while (!bool);

                searchType(var3);
                break;
            case 4:
                System.out.println("Введите год публикации:");
                String var4 = new Scanner(System.in).nextLine();
                searchYearOfPublishing(var4);
                break;
            case 5:
                System.out.println("Введите примерное количество страниц:");
                String var5 = new Scanner(System.in).nextLine();
                searchNumberOfPages(var5);
                break;
        }
    }

    public void offerToSendBook() {
        String separator = File.separator;
        String path = "src" + separator + "Person" + separator + "DataAdmin";
        File file = new File(path);

        String string = null;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                string = scanner.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("\\w+.*@\\w+.\\w+");
        assert string != null;
        Matcher matcher = pattern.matcher(string);

        boolean bool = false;

        while (matcher.find()) {
            bool = true;
        }

        if (bool) {
            System.out.println("\nВведите пароль от почты " + getE_mail() +": ");
            String stringPass = new Scanner(System.in).next();
            stringPass = stringPass.trim();
            System.out.println("Скопируйте полный путь до файла: ");
            String pathToFile = new Scanner(System.in).nextLine();

            UserSendMailToAdmin userSendMailToAdmin = new UserSendMailToAdmin(string, getE_mail(), stringPass, pathToFile);
        }
        else System.out.println("Email НЕ найден!");
    }

    //////// methods Search //////////

    private void searchName(String var) {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                String scanVar = strings[0].trim();
                String tempVar = var.toLowerCase().trim();

                if (scanVar.equalsIgnoreCase(tempVar)) {
                    System.out.println("\n\t\tНайденые совпадения:");
                    systemMethod(strings);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Совпадения не найдены, попробуйте еще раз!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchAuthor(String var) {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                String scanVar = strings[1].trim();
                String tempVar = var.toLowerCase().trim();

                if (scanVar.equalsIgnoreCase(tempVar)) {
                    System.out.println("\n\t\tНайденые совпадения:");
                    systemMethod(strings);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Совпадения не найдены, попробуйте еще раз!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchType(String var) {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                String scanVar = strings[3].trim();
                String tempVar = var.toLowerCase().trim();

                if (scanVar.equalsIgnoreCase(tempVar)) {
                    System.out.println("\n\t\tНайденые совпадения:");
                    systemMethod(strings);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Совпадения не найдены, попробуйте еще раз!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchYearOfPublishing(String var) {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                String scanVar = strings[4].trim();
                String tempVar = var.toLowerCase().trim();

                if (scanVar.equalsIgnoreCase(tempVar)) {
                    System.out.println("\n\t\tНайденые совпадения:");
                    systemMethod(strings);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Совпадения не найдены, попробуйте еще раз!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchNumberOfPages(String var) {
        String separator = File.separator;
        String path = "src" + separator + "Object" + separator + "Catalog";
        File file = new File(path);
        try {
            int count = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                String scanVar = strings[5].trim();
                String tempVar = var.toLowerCase().trim();

                if (scanVar.equalsIgnoreCase(tempVar)) {
                    System.out.println("\n\t\tНайденые совпадения:");
                    systemMethod(strings);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Совпадения не найдены, попробуйте еще раз!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void systemMethod(String[] strings) {
        System.out.println("Название книги:\t\t" + strings[0]);
        System.out.println("Автор:\t\t\t\t" + strings[1]);
        System.out.println("Описание:\t\t\t" + strings[2]);
        System.out.println("Тип:\t\t\t\t" + strings[3]);
        System.out.println("Год публикации:\t\t" + strings[4]);
        System.out.println("Количество страниц:\t" + strings[5]);
        System.out.println();
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
        return "\n* User * " +
                "\nlogin: " + login +
                "\npassword: " + password +
                "\ne-mail:" + e_mail;
    }
}