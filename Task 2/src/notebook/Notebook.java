package notebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Notebook {

    //////// Base methods ////////

    public void addNote() {
        Note note = new Note();

        System.out.println("Тема заметки: ");
        String theme = new Scanner(System.in).nextLine();
        theme = deleteUnderscore(theme);
        theme = firstUpperCase(wordTrim(theme));
        theme = checkIsEmpty(theme);
        note.setTheme(theme);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy / HH:mm");
        Date date = new Date();
        note.setDateOfCreation(simpleDateFormat.format(date));

        System.out.println("e-mail: ");
        String email = new Scanner(System.in).nextLine();
        email = checkEmail(email);
        email = checkIsEmpty(email);
        note.setEmail(email);

        System.out.println("Сообщение: ");
        String message = new Scanner(System.in).nextLine();
        message = deleteUnderscore(message);
        message = firstUpperCase(wordTrim(message));
        message = checkIsEmpty(message);
        note.setMessage(message);

        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);
        noteArrayList.add(note);
        writingToFile(noteArrayList);
    }

    public void searchWordToNote() {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        System.out.println("Введите слово которое хотите найти: ");
        System.out.print("> ");
        String string = new Scanner(System.in).nextLine();
        string = wordTrim(string);

        Pattern pattern1 = Pattern.compile("\\S+");
        Pattern pattern2 = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]+");

        for (Note note : noteArrayList) {
            boolean bool = false;

            Matcher matcher1 = pattern1.matcher(note.getTheme());
            while (matcher1.find()) {
                if (string.equalsIgnoreCase(matcher1.group())) {
                    bool = true;
                }
            }

            Matcher matcher2 = pattern1.matcher(note.getMessage());
            while (matcher2.find()) {
                if (string.equalsIgnoreCase(matcher2.group())) {
                    bool = true;
                }
            }

            Matcher matcher3 = pattern2.matcher(note.getTheme());
            while (matcher3.find()) {
                if (string.equalsIgnoreCase(matcher3.group())) {
                    bool = true;
                }
            }

            Matcher matcher4 = pattern2.matcher(note.getMessage());
            while (matcher4.find()) {
                if (string.equalsIgnoreCase(matcher4.group())) {
                    bool = true;
                }
            }

            if (bool) {
                System.out.println("Заметка со словом \"" + string + "\" найдена");
                System.out.println(note);
            }
        }
    }

    public void showNotesAfterDate() throws ParseException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        Pattern pattern = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");

        String stringScan = "";
        boolean bool = false;

        while (!bool) {
            System.out.println("Введите дату в формате (DD.MM.YYYY)");
            System.out.print("> ");
            String string = new Scanner(System.in).nextLine();
            string = wordTrim(string);
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                bool = true;
                stringScan = matcher.group();
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateScan = simpleDateFormat.parse(stringScan);
        Date dateArray;

        boolean check = false;
        ArrayList<Note> noteArrayTemp = new ArrayList<>();

        for (Note note : noteArrayList) {
            dateArray = simpleDateFormat.parse(note.getDateOfCreation());
            int temp = dateArray.compareTo(dateScan);
            if (temp >= 0) {
                noteArrayTemp.add(note);
                check = true;
            }
        }

        if (check) {
            sortAlphabetTheme(noteArrayTemp);
            for (Note note : noteArrayTemp) {
                System.out.println(note);
            }
        }
        else System.out.println("Нет заметок за данный период времени");
    }

    public void showDateMouthYearAlphabet() throws ParseException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        boolean bool1 = true;
        int month = 0;
        while (bool1) {
            System.out.println("Введите месяц: ");
            System.out.print("> ");
            month = new Scanner(System.in).nextInt();
            if (month > 0 && month < 13) {
                bool1 = false;
            }
        }

        boolean bool2 = true;
        int year = 0;
        while (bool2) {
            System.out.println("Введите год: ");
            System.out.print("> ");
            year = new Scanner(System.in).nextInt();
            if (year > 999 && year < 10000) {
                bool2 = false;
            }
        }

        String stringScan = month + "." + year;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.yyyy");
        Date dateScan = simpleDateFormat.parse(stringScan);

        Date dateArray;

        boolean check = false;
        ArrayList<Note> arrayList = new ArrayList<>();

        for (Note note : noteArrayList) {
            Pattern pattern = Pattern.compile("\\d{2}\\.\\d{4}");
            Matcher matcher = pattern.matcher(note.getDateOfCreation());
            String string = "";
            while (matcher.find()) {
                string = matcher.group();
            }
            dateArray = simpleDateFormat.parse(string);
            if (dateArray.compareTo(dateScan) == 0) {
                arrayList.add(note);
                check = true;
            }
        }

        if (check) {
            sortAlphabetTheme(arrayList);
            for (Note note : arrayList) {
                System.out.println(note);
            }
        }
        else System.out.println("Нет заметок за данный период времени");
    }

    public void showDateYear() throws ParseException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        boolean bool = true;
        int year = 0;
        while (bool) {
            System.out.println("Введите год: ");
            System.out.print("> ");
            year = new Scanner(System.in).nextInt();
            if (year > 999 && year < 10000) {
                bool = false;
            }
        }

        String stringScan = String.valueOf(year);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date dateScan = simpleDateFormat.parse(stringScan);

        Date dateArray;

        boolean check = false;
        ArrayList<Note> arrayList = new ArrayList<>();

        for (Note note : noteArrayList) {
            Pattern pattern = Pattern.compile("\\d{4}");
            Matcher matcher = pattern.matcher(note.getDateOfCreation());
            String string = "";
            while (matcher.find()) {
                string = matcher.group();
            }
            dateArray = simpleDateFormat.parse(string);
            if (dateArray.compareTo(dateScan) == 0) {
                arrayList.add(note);
                check = true;
            }
        }

        if (check) {
            sortAlphabetTheme(arrayList);
            for (Note note : arrayList) {
                System.out.println(note);
            }
        }
        else System.out.println("Нет заметок за данный период времени");
    }

    public void showNoteSortTheme() {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);
        sortAlphabetTheme(noteArrayList);

        for (Note note : noteArrayList) {
            System.out.println(note);
        }
    }

    public void showAllNotes() throws ParseException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        sortDateNote(noteArrayList, noteArrayList.size());

        for (Note note : noteArrayList) {
            System.out.println(note);
        }
    }

    public void showRecentNotes() throws ParseException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        readingFromFile(noteArrayList);

        int size = noteArrayList.size();
        int n;

        do {
            System.out.println("Введите количество заметок: ");
            System.out.print("> ");
            n = new Scanner(System.in).nextInt();

        } while (n <= 0);

        if (size >= n) {
            sortDateNote(noteArrayList,n);
        }
        else {
            System.out.println("У вас всего " + size + " заметки");
            sortDateNote(noteArrayList,noteArrayList.size());
        }
    }

    //////// Other methods ////////

    private void readingFromFile(ArrayList<Note> noteArrayList) {
        String separator = File.separator;
        String path = "src" + separator + "notebook" + separator + "Note";
        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String stringScan = scanner.nextLine();
                String[] strings = stringScan.split("_");

                Note note = new Note();
                note.setTheme(strings[0]);
                note.setDateOfCreation(strings[1]);
                note.setEmail(strings[2]);
                note.setMessage(strings[3]);

                noteArrayList.add(note);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writingToFile(ArrayList<Note> noteArrayList) {
        String separator = File.separator;
        String path = "src" + separator + "notebook" + separator + "Note";
        File file = new File(path);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Note note : noteArrayList) {
                printWriter.println(note.getTheme() + "_" + note.getDateOfCreation() + "_" +
                        note.getEmail() + "_" + note.getMessage());
            }

            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sortAlphabetTheme(ArrayList<Note> noteArrayTemp) {
        noteArrayTemp.sort(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getTheme().compareTo(o2.getTheme());
            }
        });
    }

    private void sortDateNote(ArrayList<Note> noteArrayList, int n) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        noteArrayList.sort(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                try {
                    return simpleDateFormat.parse(o2.getDateOfCreation()).compareTo(simpleDateFormat.parse(o1.getDateOfCreation()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        for (int i=0; i<n; i++) {
            System.out.println(noteArrayList.get(i));
        }
    }

    private String checkIsEmpty(String word) {
        if (word.isEmpty()) {
            word = null;
        }
        return word;
    }

    private String firstUpperCase(String word) {
        if(word == null || word.isEmpty()) {
            return "";
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private String wordLowerCase(String word) {
        word = word.toLowerCase();
        return word;
    }

    private String wordTrim(String word) {
        word = word.trim();
        return word;
    }

    private String deleteUnderscore(String word) {
        word = word.replaceAll("_","");
        return word;
    }

    private String checkEmail(String email) {
        boolean bool = false;

        // Удаление всех Underscore
        email = deleteUnderscore(email);

        // Убрать лишние пробелы и перевод на нижний регистр
        email = wordLowerCase(wordTrim(email));

        // Удаление всех пробелов
        email = email.replaceAll("\\s", "");

        // Проверка на адрес
        Pattern pattern = Pattern.compile("\\w+(\\.?)\\w+(@)\\w{2,}\\.\\w{2,3}");
        Matcher matcher = pattern.matcher(email);
        while (matcher.find()) {bool = true;}

        while (!bool) {
            System.out.println("В данном тексте присутствуют недопустимые символы");
            System.out.println("Введите текст заново: ");
            System.out.print("> ");
            String text = new Scanner(System.in).nextLine();

            Matcher matcher1 = pattern.matcher(text);

            while (matcher1.find()) {
                bool = true;
                email = matcher1.group();
            }
        }

        return email;
    }
}