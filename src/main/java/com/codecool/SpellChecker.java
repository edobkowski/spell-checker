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
        for(int i = 0; i < word.length(); i++) {
            for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
                String insertion = String.valueOf((char) substitutionLetter);
                String modifiedWord = modifyString(word, i, i + 1, insertion);
                String suggestion = words.get(modifiedWord);
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
            String modifiedWord = modifyString(word, i, i + 1, "");
            String suggestion = words.get(modifiedWord);
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

    private String modifyString(String word, int startSplitIndex, int endSplitIndex, String insertion) {
        String wordStart = word.substring(0,startSplitIndex);
        String wordEnd = word.substring(endSplitIndex);

        return wordStart + insertion + wordEnd;
    }
}
