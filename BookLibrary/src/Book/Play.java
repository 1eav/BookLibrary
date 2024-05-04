package Book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Play {
    public static void main(String[] args) {
        Persons person = new Persons();
        person.client1 = "Екатерина";
        person.client2 = "Юлия";
        person.client3 = "Алексей";
        person.client4 = "Георгий";
        person.client5 = "Вадим";
        person.client6 = "Зинаида";
        person.client7 = "Светлана";
        person.client8 = "Петр";
        person.client9 = "Валентин";
        person.client10 = "Василий";
        String name, info;
        TreeMap<String, String> book = new TreeMap<>();
        String data_file_name = "data.txt";
        File dataFile = new File("data\\book" + data_file_name);
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                System.out.println("======= Обработка системы =======");
                System.out.println("Папка обнаружена: 'data'");
                System.out.println("Файл успешно создан: 'bookdata.txt'");
            } else {
                System.out.println("======= Обработка системы =======");
                System.out.println("Загрузка данных: " + dataFile.getAbsolutePath() + "...");
                System.out.println("Загрузка данных завершена");
                try (Scanner scanner = new Scanner(dataFile)) {
                    while (scanner.hasNextLine()) {
                        String bookEntry = scanner.nextLine();
                        int separatorPosition = bookEntry.indexOf('%');
                        if (separatorPosition == -1) throw new IOException("Произошла ошибка в измененении данных");
                        {
                            name = bookEntry.substring(0, separatorPosition);
                            info = bookEntry.substring(separatorPosition + 1);
                            book.put(name, info);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("======= Обработка системы данных =======");
            System.out.println("Отсутствует папка " + '"' + "data" + '"');
            System.out.println("Файл не найден: " + '"' + dataFile.getName() + '"');
            System.out.println("Путь файла: " + dataFile.getAbsolutePath());
        }
        Scanner in = new Scanner(System.in);
        boolean changed = false;
        int command;
        mainLoop:
        do {
            System.out.println("\n======= Вход в систему =======");
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите логин: ");
            String username = sc.nextLine();
            System.out.print("Введите пароль: ");
            String password = sc.nextLine();
            if (username.equals("админ") && password.equals("123")) {
                while (true) {
                    System.out.println(" ");
                    System.out.println("======= Добро пожаловать в Магазин книг =======");
                    System.out.println(" ");
                    System.out.println("1. Добавить книгу");
                    System.out.println("2. Удалить книгу");
                    System.out.println("3. Редактировать книгу");
                    System.out.println("4. Продать книгу");
                    System.out.println("5. Списать книгу");
                    System.out.println("6. Внести книгу в акцию");
                    System.out.println("7. Отложить книгу для покупателя");
                    System.out.println("8. Поиск книги");
                    System.out.println("9. Просмотреть список новинок");
                    System.out.println("10. Просмотреть список самых продаваемых книг");
                    System.out.println("11. Просмотреть список популярных авторов");
                    System.out.println("12. Просмотреть список популярных жанров");
                    System.out.println("0. Выход");
                    System.out.print("\nВыбор категории: ");
                    if (in.hasNextInt()) {
                        command = in.nextInt();
                        in.nextLine();
                    } else {
                        System.out.println("\nНекорректный ввод");
                        in.nextLine();
                        continue;
                    }
                    switch (command) {
                        case 1:
                            System.out.println("\n======= Добавление новой книги =======");
                            System.out.print("\nВведите название книги: ");
                            name = in.nextLine().trim();
                            if (name.length() == 0) {
                                System.out.println("\nДанные не могут быть пустыми");
                            } else {
                                System.out.print("Введите ФИО автора: ");
                                info = in.nextLine().trim();
                                book.put(name, info);
                                changed = true;
                                System.out.println("\nКнига успешно добавлена!");
                            }
                            break;
                        case 2:
                            System.out.println("======= Удалить книгу =======");
                            System.out.print("Введите название книги, которую вы хотите удалить: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nОтсуствует книга <" + name + ">");
                            } else {
                                book.remove(name);
                                changed = true;
                                System.out.println("\nКнига " + '"' + name + '"' + " успешно удалена");
                            }
                            break;
                        case 3:
                            System.out.println("======= Редактировать книгу =======");
                            System.out.print("Введите название книги, который вы хотите изменить: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nКнига <" + name + "> не существует");
                            } else {
                                book.remove(name);
                                System.out.print("Введите новое название книги: ");
                                name = in.nextLine().trim();
                                if (name.length() == 0) {
                                    System.out.println("\nДанные не могут быть пустыми");
                                } else {
                                    System.out.print("Введите ФИО автора: ");
                                    info = in.nextLine().trim();
                                    book.put(name, info);
                                    changed = true;
                                    System.out.println("\nКнига успешно изменена!");
                                }
                            }
                            break;
                        case 4:
                            Balance balance1 = new Balance();
                            System.out.println("======= Продать книгу =======");
                            System.out.print("Введите название книги, которую вы хотите продать: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nОтсуствует книга <" + name + ">");
                            } else {
                                book.remove(name);
                                System.out.print("Введите сумму для продажи: ");
                                balance1.put(in.nextInt());
                                changed = true;
                                System.out.println("\nКнига " + '"' + name + '"' + " успешно продана");
                                System.out.println(balance1 + " Р");
                            }
                            break;
                        case 5:
                            System.out.println("======= Списать книгу =======");
                            System.out.print("Введите название книги, которую вы хотите списать: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nОтсуствует книга <" + name + ">");
                            } else {
                                book.remove(name);
                                changed = true;
                                System.out.println("\nКнига " + '"' + name + '"' + " успешно списана из ассортимента");
                            }
                            break;
                        case 6:
                            Balance balance2 = new Balance();
                            System.out.println("======= Внести книгу в акцию =======");
                            System.out.print("Введите название книги, которую вы хотите продать со скидкой: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nОтсуствует книга <" + name + ">");
                            } else {
                                book.remove(name);
                                System.out.print("Введите сумму для продажи: ");
                                balance2.discount(in.nextInt());
                                changed = true;
                                System.out.println("\nКнига " + '"' + name + '"' + " успешно продана со скидкой в 10%");
                                System.out.println(balance2 + " Р");
                            }
                            break;
                        case 7:
                            System.out.println("======= Отложить книгу для покупателя =======");
                            System.out.print("Введите название книги, которую вы хотите отложить для покупателя: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nОтсуствует книга <" + name + ">");
                            } else {
                                int choice;
                                choiceLoop:
                                while (true){
                                    System.out.println("Выберите покупателя:");
                                    System.out.println("1. Екатерина");
                                    System.out.println("2. Юлия");
                                    System.out.println("3. Алексей");
                                    System.out.println("4. Георгий");
                                    System.out.println("5. Вадим");
                                    System.out.println("6. Зинаида");
                                    System.out.println("7. Светлана");
                                    System.out.println("0. Отменить выбор");
                                    System.out.print("\nВыбор: ");
                                    if (in.hasNextInt()) {
                                        choice = in.nextInt();
                                        in.nextLine();
                                    } else {
                                        System.out.println("\nНекорректный ввод");
                                        in.nextLine();
                                        continue;
                                    }
                                    switch (choice) {
                                        case 1:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client1);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 2:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client2);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 3:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client3);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 4:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client4);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 5:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client5);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 6:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client6);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 7:
                                            System.out.println("\nКнига " + '"' + name + '"' + " отложена для покупателя: " + person.client7);
                                            book.remove(name);
                                            break choiceLoop;
                                        case 0:
                                            System.out.println("\nВыбор отменен");
                                            break choiceLoop;
                                        default:
                                            System.out.println("\nНекорректный ввод");
                                    }
                                }
                            }
                            break;
                        case 8:
                            System.out.println("======= Поиск книги =======");
                            System.out.print("Введите название книги, который вы хотите найти: ");
                            name = in.nextLine().trim();
                            info = book.get(name);
                            if (info == null) {
                                System.out.println("\nКнига <" + name + "> не существует");
                            } else {
                                System.out.println("\nРезультаты поиска:");
                                System.out.println("Название: " + name);
                                System.out.println("ФИО автора: " + info);
                            }
                            break;
                        case 9:
                            System.out.println("======= Список новинок =======");
                            for (Map.Entry<String, String> entry : book.entrySet()) {
                                System.out.println("Название: " + entry.getKey() + " - ФИО автора: " + entry.getValue());
                            }
                            break;
                        case 10:
                            System.out.println("======= Список самых продаваемых книг =======");
                            String[] titles = {"Руслан и Людмила", "Маугли", "Война и мир", "Сказка о рыбаке и рыбке", "Три медведя", "Вий", "Преступление и наказание"};
                            int number1 = (int) (Math.random() * 7);
                            System.out.println("\nПопулярное произведение: " + titles[number1]);
                            break;
                        case 11:
                            System.out.println("======= Список популярных авторов =======");
                            String[] authors = {"А.С.Пушкин", "Ф.М.Достоевский", "А.П.Чехов", "Л.Н.Толстой", "Н.В.Гоголь", "М.Ю.Лермонтов", "А.М.Горький"};
                            int number2 = (int) (Math.random() * 7);
                            System.out.println("\nПопулярный автор: " + authors[number2]);
                            break;
                        case 12:
                            System.out.println("======= Список популярных жанров =======");
                            String[] genres = {"Повесть", "Поэма", "Сказка", "Комедия", "Трагедия", "Драма", "Рассказ"};
                            int number3 = (int) (Math.random() * 7);
                            System.out.println("\nПопулярный жанр: " + genres[number3]);
                            break;
                        case 0:
                            System.out.println("\nВыход из приложения. Спасибо за использование!");
                            break mainLoop;
                        default:
                            System.out.println("\nНекорректный ввод");
                    }
                }
            } else {
                System.out.println("\nНеверный логин или пароль.");
                break mainLoop;
            }
        } while (command != 0);
        if (changed) {
            System.out.println("Сохраненение данных " + dataFile.getAbsolutePath() + "...");
            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(dataFile));
            } catch (IOException e) {
                System.out.println("Ошибка. Не удается сохранить файл " + '"' + "bookdata.txt");
                return;
            }
            for (Map.Entry<String, String> entry : book.entrySet())
                out.println(entry.getKey() + "%" + entry.getValue());
            out.flush();
            out.close();
            if (out.checkError()) {
                System.out.println("Произошла ошибка при сохранения файла");
            } else {
                System.out.println("Изменение успешно сохранены");
            }
        }
    }
}