package com.codecool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class App
{
    public static void main( String[] args) {
        try {
            StringHashTable correctWords = new StringHashTable(new DefaultHasher());
            Path wordsListPath = new File("assets/wordlist.txt").toPath();
            List<String> words = Files.readAllLines(wordsListPath);

            for(String word : words) {
                correctWords.add(word, word);
            }

            SpellChecker spellChecker = new SpellChecker(correctWords);

            Path testWordsPath = new File("assets/big-test.txt").toPath();
            List<String> testWords = Files.readAllLines(testWordsPath);

            for(String word : testWords) {
                Set<String> suggestions = spellChecker.check(word);
                if(!suggestions.isEmpty()) {
                    System.out.println("\nWord not found: " + word.toUpperCase());
                    System.out.println("Perhabs you meant:\n");
                    for(String suggestion : suggestions) {
                        System.out.println(suggestion.toUpperCase());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
