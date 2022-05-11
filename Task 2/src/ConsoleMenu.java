import notebook.Notebook;

import java.text.ParseException;
import java.util.Scanner;

public class ConsoleMenu {
    public void menuMain() throws ParseException {
        System.out.println("            *** NoteBook ***");
        System.out.println("Add new note                     -> [1]");
        System.out.println("Show all notes                   -> [2]");
        System.out.println("Search by word to note           -> [3]");
        System.out.println("Show recent notes                -> [4]");
        System.out.println("Show notes alphabetically        -> [5]");
        System.out.println("Show notes after date            -> [6]");
        System.out.println("Show notes for the year          -> [7]");
        System.out.println("Show notes for month/year period -> [8]");
        System.out.println("---------------------------------------");
        System.out.println("Exit                             -> [0]");

        System.out.print("> ");
        int s = new Scanner(System.in).nextInt();

        Notebook notebook = new Notebook();

        switch (s) {
            case 1:
                notebook.addNote();
                if (menuOther() == 1) {menuMain();}
                break;
            case 2:
                notebook.showAllNotes();
                if (menuOther() == 1) {menuMain();}
                break;
            case 3:
                notebook.searchWordToNote();
                if (menuOther() == 1) {menuMain();}
                break;
            case 4:
                notebook.showRecentNotes();
                if (menuOther() == 1) {menuMain();}
                break;
            case 5:
                notebook.showNoteSortTheme();
                if (menuOther() == 1) {menuMain();}
                break;
            case 6:
                notebook.showNotesAfterDate();
                if (menuOther() == 1) {menuMain();}
                break;
            case 7:
                notebook.showDateYear();
                if (menuOther() == 1) {menuMain();}
                break;
            case 8:
                notebook.showDateMouthYearAlphabet();
                if (menuOther() == 1) {menuMain();}
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                if (menuOther() == 1) {menuMain();}
        }
    }

    private int menuOther() {
        System.out.println();
        System.out.println("   -- Menu -- ");
        System.out.println("Back to menu -> [1]");
        System.out.println("Log off      -> [0]");
        int menu = 0;
        boolean bool = true;

        while (bool) {
            System.out.print("> ");
            menu = new Scanner(System.in).nextInt();
            if (menu == 0 || menu == 1) {bool = false;}
        }
        return menu;
    }
}