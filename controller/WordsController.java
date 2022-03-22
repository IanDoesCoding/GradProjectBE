
package com.typechecker.typesystem.controller;
import com.typechecker.typesystem.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/words")
@CrossOrigin

public class WordsController {
    @Autowired
    private WordsService wordsService;

    @GetMapping("/getAll")
    public List<String> getAllWords(){
        return wordsService.getAllWords();
    }

    @GetMapping("/getAllSpellingMistakes")
    public List<String> getAllSpellingMistakes(){ return wordsService.getAllSpellingMistakes();}

    @GetMapping("/getAllTypo")
    public  List<String> getAllTypo(){return wordsService.getAllTypo();}

    @GetMapping("/getModeKey")
    public String getModkey(){return wordsService.getModkey();}

    @GetMapping("/getModeSpellingMistake")
    public String getModeSpellingMistake(){return wordsService.getModeSpellingMistake();}

    @GetMapping("/getModeTypo")
    public String getModeTypo(){return wordsService.getModeTypo();}

    @GetMapping("/getInCorrectWord")
    public String getNumInCorrectWords(){return wordsService.getNumInCorrectWords();}

    @GetMapping("/getNumberOfSpellingMistakes")
    public String getNumOfSpellingErrors(){return  wordsService.getNumOfSpellingErrors();}

    @GetMapping("/getNumAllCategories")
    public List<String> getNumAllCategories() {return wordsService.getNumAllCategories();}

    @GetMapping("/getSpellingMistakeScore")
    public List<String> getAllScoreSpellingMistake(){return wordsService.getAllScoreSpellingMistake();}

    @GetMapping("/getTotalHash")
    public Map<String, String> getTotalHash(){return wordsService.getTotalHash();}

    @GetMapping("/getTypoScore")
    public List<String> getAllScoreTypo(){return wordsService.getAllScoreTypo();}

}

