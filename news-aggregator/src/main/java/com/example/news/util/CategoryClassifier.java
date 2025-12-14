package com.example.news.util;

public class CategoryClassifier {

    public static String classify(String text) {
        // Приведение к нижнему регистру и удаление знаков препинания
        text = text.toLowerCase().replaceAll("[^а-яa-z0-9\\s]", "");

        if (text.contains("политик") || text.contains("выборы") || text.contains("парламент") ||
                text.contains("путин") || text.contains("трамп") || text.contains("собянин") ||
                text.contains("калифорни")) {
            return "Политика";

        } else if (text.contains("рубль") || text.contains("банк") || text.contains("экономик") ||
                text.contains("доллар") || text.contains("евро") || text.contains("инвестпроект") ||
                text.contains("apple") || text.contains("цены") || text.contains("жильё") ||
                text.contains("дивиденд") || text.contains("рейтинг") || text.contains("акци") ||
                text.contains("строительств") || text.contains("стоимост")) {
            return "Экономика";

        } else if (text.contains("футбол") || text.contains("спорт") || text.contains("матч") ||
                text.contains("баскетбол") || text.contains("теннис") || text.contains("волейбол")) {
            return "Спорт";

        } else if (text.contains("кино") || text.contains("фильм") || text.contains("актер") ||
                text.contains("театр") || text.contains("мюзикл") || text.contains("временной")) {
            return "Культура";

        } else if (text.contains("миде") || text.contains("сбит") || text.contains("дрон") ||
                text.contains("потуши") || text.contains("танк") || text.contains("экипаж")) {
            return "СВО";

        } else if (text.contains("поездка") || text.contains("петербург") || text.contains("водоём") ||
                text.contains("курорт")) {
            return "Отдых";

        } else {
            return "Общее";
        }
    }
}
