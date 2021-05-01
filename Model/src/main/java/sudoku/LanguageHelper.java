package sudoku;

public class LanguageHelper {
    private static String lang;

    public LanguageHelper() {
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static String fabric(int choice) {
        if (lang.equals("Polski")) {
            if (choice == 1) {
                return "Zle wartosci pol";
            }
            if (choice == 2) {
                return "Blad odczytu pliku";
            }
            if (choice == 3) {
                return "Zla wielkosc modelu danych";
            }
        } else if (lang.equals("Angielski")) {
            if (choice == 1) {
                return "Bad field value";
            }
            if (choice == 2) {
                return "File open problem";
            }
            if (choice == 3) {
                return "Bad size of data";
            }
        } else {
            return "Er";
        }
        return " ";
    }
}
