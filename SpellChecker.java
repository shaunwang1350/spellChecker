import java.io.*;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface {

    private Set<String> dictSet = new HashSet<>();

    public SpellChecker(String filename){
        
        File newFile = new File(filename);
        
        try{
            
            Scanner scanner = new Scanner(new FileReader(filename));
            String currentLine = scanner.nextLine();
            
            while(currentLine != null){
                
               String linePunctuationRemoved = currentLine.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
               String[] wordsSplit = linePunctuationRemoved.split("\\s+");
                
                for (String word: wordsSplit) {
                    dictSet.add(word);
                }
                currentLine = scanner.nextLine();
            }
            
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public List<String> getIncorrectWords(String filename) {
        
        List<String> incorrectWords = new ArrayList<>();
        File inputFile = new File(filename);
        
        try {
            Scanner scanner = new Scanner(new FileReader(filename));
            String currentLine = scanner.nextLine();
            
            while(currentLine != null){
                
                if (!currentLine.equals("")){
                    
                    String[] wordsWithinLine = currentLine.split("\\s+");
                    
                    for (String currentWord: wordsWithinLine) {

                        String punctuationRemoved = currentWord.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                        
                        if (!dictSet.contains(punctuationRemoved)) {
                            incorrectWords.add(punctuationRemoved);
                        }
                    }
                }
                currentLine = scanner.nextLine();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return incorrectWords;
    }

    public Set<String> getSuggestions(String word) {
        
        String potentialCorrection = "";
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Set<String> suggestionSet = new HashSet<>(); 
        String lowerCasedWord = word.toLowerCase();
        
        potentialCorrection = lowerCasedWord.replaceAll("\\d", "");
        if (dictSet.contains(potentialCorrection)) {
                suggestionSet.add(potentialCorrection);
        }                                          
        
        for(int i = 0; i<lowerCasedWord.length(); i++) {
            potentialCorrection = lowerCasedWord.substring(0, i) + lowerCasedWord.substring(i+1);

            if (dictSet.contains(potentialCorrection)) {
                suggestionSet.add(potentialCorrection);

            }
        }
            
        for(int i = 0; i<lowerCasedWord.length(); i++) {
            potentialCorrection = lowerCasedWord.substring(0, i) + lowerCasedWord.substring(i + 1, lowerCasedWord.length());

            if (dictSet.contains(potentialCorrection)) {
                suggestionSet.add(potentialCorrection);
            }
        }

        for(int j = 0; j<lowerCasedWord.length(); j++){
            for (char alphab: alphabet) {
                potentialCorrection = lowerCasedWord.substring(0, j) + alphab + lowerCasedWord.substring(j, lowerCasedWord.length());

                if (dictSet.contains(potentialCorrection)) {
                    suggestionSet.add(potentialCorrection);
                }

                if (j == lowerCasedWord.length() - 1) {
                    potentialCorrection = lowerCasedWord + alphab;
                    if (dictSet.contains(potentialCorrection)) {
                        suggestionSet.add(potentialCorrection);
                    }
                }
            }
        }

        for(int k = 1; k < lowerCasedWord.length(); k++){
            if (k != lowerCasedWord.length() - 1) {
                potentialCorrection = lowerCasedWord.substring(0, k - 1) + lowerCasedWord.substring(k, k + 1) + lowerCasedWord.substring(k - 1, k) + lowerCasedWord.substring(k + 1, lowerCasedWord.length());
            }
            else {
                potentialCorrection = lowerCasedWord.substring(0, k - 1) + lowerCasedWord.substring(k, k + 1) + lowerCasedWord.substring(k - 1, k);
            }
            
            if (dictSet.contains(potentialCorrection)) {
                suggestionSet.add(potentialCorrection);
            }
        }
        
        return suggestionSet;
    }

}