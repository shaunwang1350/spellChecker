import java.io.*;
import java.util.*;

public class SpellCheckerTest{
    
    public static void main(String args[]) {
        
        SpellChecker spellCheckerTest = new SpellChecker("words.txt");
        List<String> incorrectListOfWords = spellCheckerTest.getIncorrectWords("test.txt");
        
        System.out.println(">---------------------------------------<");
        
        for(String word: incorrectListOfWords){
            Set<String> suggestionSet = spellCheckerTest.getSuggestions(word);
            System.out.println("---------------------");
            System.out.println("Incorrection word: " + word);
            System.out.println("Suggestions: " + suggestionSet);
        }
        
        System.out.println(">---------------------------------------<");
    }
    
}