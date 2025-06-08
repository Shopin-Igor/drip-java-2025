package com.example.news;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static void ShowLegend() {
        System.out.println("\n 0. Выход \t\t\t\t\t 1. Сколько отображать новостей \t  2. Сортировать по дате \t\t\t 3. Сортировать по источнику");
        System.out.println(" 5. Собрать новости \t\t 6. Показать последние \t\t\t\t  7. Показать последние (все поля) \t 8. Экспорт в CSV");
        System.out.println("10. Поиск по слову (везде)\t11. Поиск в заголовке\t\t\t\t 12. Поиск в тексте \t\t\t\t13. Поиск по дате (формат YYYY-MM-DD) \t14. Поиск по категории\t\t\t15. Поиск по источнику");
        System.out.println("16. Список источников \t\t17. Добавить RBC и Lenta \t\t\t 18. Добавить свой источник \t\t19. Удалить источник \t\t\t\t\t20. Аналитика-статистика\t\t");
        System.out.print("Выберите опцию: ");
    }


    public static void main(String[] args) {
        NewsAggregator aggregator = new NewsAggregator();
        Scanner scanner = new Scanner(System.in);
        Object lock = new Object();


        Timer timer = new Timer(); // Поток таймера, который выводит сообщение раз в минуту
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run () {

                synchronized (lock) {
                    System.out.println("\n\nАвто обновление:");
                    aggregator.fetchAndStore();
                    System.out.println("Обновлённая лента:");
                    aggregator.ShowNews();
                    ShowLegend();
                }
            }
        }, 20 * 1000, 60 * 1000); // 60 * 1000 = 1 минута

        // Поток, который слушает ввод пользователя
        Thread inputThread = new Thread(() -> {

            while (true) {
                ShowLegend();
                String input = scanner.nextLine();
                synchronized (lock) {
                    switch (input) {
                        case "0" -> {
                            timer.cancel(); // Остановить таймер
                            aggregator.CloseDB();
                            System.out.println("До скорых встреч!");
                            return;
                        }
                        case "1" -> {
                            System.out.print("Введите количество новостей: ");
                            try {
                                aggregator.SetСountRows(Integer.parseInt(scanner.nextLine()));
                            } catch (NumberFormatException e) {
                                System.out.println("Это не число!");
                            }
                        }
                        case "2" -> aggregator.SetSortByDate();
                        case "3" -> aggregator.SetSortBySource();
                        case "5" -> aggregator.fetchAndStore();
                        case "6" -> aggregator.ShowNews();
                        case "7" -> aggregator.ShowNewsFull();
                        case "8" -> aggregator.exportToCSV();
                        case "10" -> {
                            System.out.print("Ключевое слово: ");
                            aggregator.FindNews_Title_And_Text(scanner.nextLine());
                        }
                        case "11" -> {
                            System.out.print("Ключевое слово: ");
                            aggregator.FindNews_Title(scanner.nextLine());
                        }
                        case "12" -> {
                            System.out.print("Ключевое слово: ");
                            aggregator.FindNews_Text(scanner.nextLine());
                        }
                        case "13" -> {
                            System.out.print("Введите дату (формат YYYY-MM-DD): ");
                            aggregator.FindNews_Date(scanner.nextLine());
                        }
                        case "14" -> {
                            System.out.print("Введите категорию (Политика, Экономика, Спорт, Культура, СВО, Отдых или Общее): ");
                            aggregator.FindNews_Category(scanner.nextLine());
                        }
                        case "15" -> {
                            System.out.print("Имя Источника (RBC, Lenta.ru...): ");

                            aggregator.FindNews_Source(scanner.nextLine());

                        }
                        case "16" -> aggregator.listSources();
                        case "17" -> {
                            aggregator.insertSource("title", "publish_date_t", "body", "fronturl", "picture", "RBC", "id", "https://www.rbc.ru/search/ajax/?dateFrom=", "items");
                            aggregator.insertSource("title", "pubdate", "text", "url", "image_url", "Lenta.ru", "docid", "https://lenta.ru/search/v2/process?modified%2Cfrom=", "matches");
                        }
                        case "18" -> {
                            System.out.print("Название поля для Title: ");
                            String title = scanner.nextLine();

                            System.out.print("Название поля для Date: ");
                            String date = scanner.nextLine();

                            System.out.print("Название поля для Summary: ");
                            String summary = scanner.nextLine();

                            System.out.print("Название поля для URL: ");
                            String url = scanner.nextLine();

                            System.out.print("Название поля для Video/Photo URL: ");
                            String media = scanner.nextLine();

                            System.out.print("Имя источника: ");
                            String source = scanner.nextLine();

                            System.out.print("Название поля для Source ID: ");
                            String sourceId = scanner.nextLine();

                            System.out.print("URl источника для Json: ");
                            String json_url = scanner.nextLine();

                            System.out.print("Название корня для JSON: ");
                            String root_tag = scanner.nextLine();

                            aggregator.insertSource(title, date, summary, url, media, source, sourceId, json_url, root_tag);
                        }
                        case "19" -> {
                            System.out.print("Введите ID источника для удаления: ");
                            try {
                                int id = Integer.parseInt(scanner.nextLine());
                                aggregator.deleteSourceById(id);
                            } catch (NumberFormatException e) {
                                System.out.println("Некорректный ID");
                            }
                        }
                        case "20" -> {
                            aggregator.Analitics();
                        }
                        default -> System.out.println("Неверный выбор");
                    }
                }
            }

        });

        inputThread.setDaemon(false); // основной поток не завершится, пока этот жив
        inputThread.start();
    }
}