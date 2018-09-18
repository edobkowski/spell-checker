package com.codecool;

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
        for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
            String insertion = String.valueOf((char) substitutionLetter);
            suggestions.addAll(modifyString(word, 1, insertion));
        }

        return suggestions;
    }

    private Set<String> multiplyCheck(String word) {
        return modifyString(word, 1, "");
    }

    private Set<String> missCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
            String insertion = String.valueOf((char) substitutionLetter);
            suggestions.addAll(modifyString(word, 0, insertion));
        }

        return suggestions;
    }

    private Set<String> modifyString(String word, int splitShift, String insertion) {
        Set<String> suggestions = new HashSet<>();

        for(int i = 0; i < word.length(); i++) {
            String wordStart = word.substring(0, i);
            String wordEnd = word.substring(i + splitShift);
            String modifiedWord = wordStart + insertion + wordEnd;
            String suggestion = words.get(modifiedWord);
            if(suggestion != null) {
                suggestions.add(suggestion);
            }
        }

        return suggestions;
    }
}
