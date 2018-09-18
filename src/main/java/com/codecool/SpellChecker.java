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
        suggestions.addAll(spaceMissCheck(word));
        suggestions.addAll(swapCheck(word));

        return suggestions;
    }

    private Set<String> misspellCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
            String insertion = String.valueOf((char) substitutionLetter);
            suggestions.addAll(getSuggestions(word, 1, insertion));
        }

        return suggestions;
    }

    private Set<String> multiplyCheck(String word) {
        return getSuggestions(word, 1, "");
    }

    private Set<String> missCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int substitutionLetter = 'a'; substitutionLetter <= 'z'; substitutionLetter++) {
            String insertion = String.valueOf((char) substitutionLetter);
            suggestions.addAll(getSuggestions(word, 0, insertion));
        }

        return suggestions;
    }

    private Set<String> spaceMissCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int i = 1; i < word.length(); i++) {
            String modifiedWord = modifyWord(word, i, 0, " ");
            if(isMissingSpace(modifiedWord)) {
                suggestions.add(modifiedWord);
            }
        }

        return suggestions;
    }

    private Set<String> swapCheck(String word) {
        Set<String> suggestions = new HashSet<>();
        for(int i = 0; i < word.length() - 1; i++){
            String modifiedWord = swapLetters(word, i, i + 1);
            if(words.get(modifiedWord) != null) {
                suggestions.add(modifiedWord);
            }
        }

        return suggestions;
    }

    private String swapLetters(String word, int i, int j) {
        String swappedLetters = String.valueOf(word.charAt(j)) + String.valueOf(word.charAt(i));
        return modifyWord(word, i, 2, swappedLetters);
    }

    private boolean isMissingSpace(String word) {
        String[] splittedWords = word.split(" ");
        return words.get(splittedWords[0]) != null
            && words.get(splittedWords[1]) != null;
    }

    private Set<String> getSuggestions(String word, int splitShift, String insertion) {
        Set<String> suggestions = new HashSet<>();
        for(int i = 0; i < word.length(); i++) {
            String modifiedWord = modifyWord(word, i, splitShift, insertion);
            String suggestion = words.get(modifiedWord);
            if(suggestion != null) {
                suggestions.add(suggestion);
            }
        }

        return suggestions;
    }

    private String modifyWord(String word, int startIndex, int splitShift, String insertion) {
        String wordStart = word.substring(0, startIndex);
        String wordEnd = word.substring(startIndex + splitShift);
        return wordStart + insertion + wordEnd;
    }
}
