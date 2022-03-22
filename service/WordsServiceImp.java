package com.typechecker.typesystem.service;
import com.typechecker.typesystem.Spellchecker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class WordsServiceImp implements WordsService {
    String[] myArray = {"hello","how","are"};

    Spellchecker sc = new Spellchecker();
    String[] words =   sc.readKey("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\keys");

    String spellingMistakeFilePath = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\spellingMistakes";
    String keyFile = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\keys";
    String typoFilePath = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\typo";
    String ScoreSpFile = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\scorefileSpMistake";
    String ScoreTypoFile = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\scorefileTypo";
    String TotalWords = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalWords";
    String TotalSp = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalSpellingMistakes";



    @Override
    public List<String> getAllWords() {
        return Arrays.stream(words).toList() ; /// send list
    }

    @Override
    public List<String> getAllSpellingMistakes(){
        return  Arrays.stream(sc.readFile(spellingMistakeFilePath)).toList();
    }

    @Override
    public List<String> getAllTypo(){
        return  Arrays.stream(sc.readFile(typoFilePath)).toList();
    }

    @Override
    public String getModkey(){
        return sc.getMode(sc.readFile(keyFile));
    }

    @Override
    public String getModeSpellingMistake(){return sc.getMode(sc.readFile(spellingMistakeFilePath));}

    @Override
    public String getModeTypo(){return sc.getMode(sc.readFile(typoFilePath));}

    @Override
    public String getNumInCorrectWords(){return sc.getNumWords(sc.readFile(typoFilePath)); }

    @Override
    public String getNumOfSpellingErrors(){return sc.getNumWords(sc.readFile(spellingMistakeFilePath));}

    @Override
    public List<String> getNumAllCategories(){return sc.getNumAllCategories(sc.getNumWords(sc.readFile(keyFile)),sc.getNumWords(sc.readFile(spellingMistakeFilePath)),sc.getNumWords(sc.readFile(typoFilePath)));}

    @Override
    public List<String> getAllScoreSpellingMistake(){return Arrays.stream(sc.readFile(ScoreSpFile)).toList();
    }

    @Override
    public Map<String, String> getTotalHash(){return sc.returnObj(sc.readFile(TotalWords),sc.readFile(TotalSp));}

    @Override
    public List<String> getAllScoreTypo(){return Arrays.stream(sc.readFile(ScoreTypoFile)).toList();
    }
}
