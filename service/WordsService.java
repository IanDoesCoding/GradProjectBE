package com.typechecker.typesystem.service;


import com.typechecker.typesystem.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface WordsService {
    public List<String> getAllWords();
    public List<String> getAllSpellingMistakes();
    public List<String> getAllTypo();
    public String getModkey();
    public String getModeSpellingMistake();
    public String getModeTypo();
    public String getNumInCorrectWords();
    public String getNumOfSpellingErrors();
    public List<String> getNumAllCategories();
    public List<String> getAllScoreSpellingMistake();
    public List<String> getAllScoreTypo();
    public Map<String, String> getTotalHash();
}
