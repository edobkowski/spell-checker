package com.codecool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public Set<String> check(String word) {
        Set<String> suggestions = new HashSet<>();
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

    private Set<String> misspellCheck(String word) {
        Set<String> suggestions = new HashSet<>();
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

    private Set<String> multiplyCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int i = 0; i < word.length(); i++) {
            StringBuilder modifiedWord = new StringBuilder();
            String wordStart = word.substring(0,i);
            String wordEnd = word.substring(i + 1);
            modifiedWord.append(wordStart)
                    .append(wordEnd);
            String suggestion = words.get(modifiedWord.toString());
            if(suggestion != null) {
                suggestions.add(suggestion);

            }
        }

        return suggestions;
    }

    private Set<String> missCheck(String word) {
        Set<String> suggestions = new HashSet<>();

        return suggestions;
    }
}
