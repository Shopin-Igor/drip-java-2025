package com.example.news.util;

import java.util.*;
import java.util.regex.Pattern;



public class KeywordExtractor {
    private static final Set<String> STOP_WORDS = Set.of(
        "и", "в", "на", "с", "по", "что", "как", "а", "но", "за", "из", "к", "для", "это", "то", "же", "не", "о", "от", "до"
    );

    public static List<String> extractKeywords(String text, int topN) {
        Map<String, Integer> freq = new HashMap<>();
        Pattern wordPattern = Pattern.compile("[а-яА-Яa-zA-ZёЁ]+");

        var matcher = wordPattern.matcher(text.toLowerCase());
        while (matcher.find()) {
            String word = matcher.group();
            if (!STOP_WORDS.contains(word) && word.length() > 3) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }

        return freq.entrySet()
                   .stream()
                   .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                   .limit(topN)
                   .map(Map.Entry::getKey)
                   .toList();
    }
}
