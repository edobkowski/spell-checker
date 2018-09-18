package com.codecool;

import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

    private StringHashTable words;

    SpellChecker(StringHashTable words) {
        this.words = words;
    }

    /**
     * Checks if passed string argument exists in hash table with available correct words
     * and return suggestions for misspelled input or an empty list if word was found in a table
     * @param word The string to check for correctness
     * @return List of proposed correct words
     */
    public List<String> check(String word) {
        List<String> suggestions = new ArrayList<>();
        if(word == null || word.length() < 1) {
            return suggestions;
        }

        word = word.toLowerCase();
        String correctValue = words.get(word);
        if(word.equals(correctValue)) {
            return suggestions;
        }

        suggestions.addAll(misspellCheck(word));
        suggestions.addAll(multiplyCheck(word));
        suggestions.addAll(missCheck(word));

        return suggestions;
    }

    private List<String> misspellCheck(String word) {
        List<String> suggestions = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            String wordStart = word.substring(0,i);
            String wordEnd = word.substring(i + 1);
            for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
                StringBuilder modifiedWord = new StringBuilder();
                modifiedWord.append(wordStart)
                            .append((char)substitutionLetter)
                            .append(wordEnd);
                String suggestion = words.get(modifiedWord.toString());
                if(suggestion != null) {
                    suggestions.add(suggestion);
                }
            }
        }

        return suggestions;
    }

    private List<String> multiplyCheck(String word) {
        List<String> suggestions = new ArrayList<>();

        return suggestions;
    }

    private List<String> missCheck(String word) {
        List<String> suggestions = new ArrayList<>();

        return suggestions;
    }
}
