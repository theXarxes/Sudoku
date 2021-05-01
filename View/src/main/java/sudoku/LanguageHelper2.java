package sudoku;

public class LanguageHelper2 {
    private static String lang;

    public static String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static String fabric(int choice) {
        if (lang.equals("Polski")) {
            if (choice == 1) {
                return "Zly jezyk";
            }
            if (choice == 2) {
                return "Blad planszy";
            }

        } else if (lang.equals("Angielski")) {
            if (choice == 1) {
                return "Bad language";
            }
            if (choice == 2) {
                return "Board issue";
            }
        } else {
            return "Er";
        }
        return " ";
    }
}
