package com.typechecker.typesystem;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service

public class Main implements NativeKeyListener ,NativeMouseInputListener {

    public String keyFile ="C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\keys";
    public String SpellingMistakeFile =  "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\spellingMistakes";
    public String TypoFile = "C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\typo";

    private static final Path file = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\keys");
    private static final Path scorefileSpMistake = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\scorefileSpMistake");
    private static final Path scorefileTypo = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\scorefileTypo");
    private static final Path totalSpellingfile = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalSpellingMistakes");
    private static final Path totalWordsfile = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalWords");
    @PostConstruct
    public void runM() {
        Spellchecker sc = new Spellchecker();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                sc.writeScoreTofile(sc.getSessionScore(sc.getNumWords(sc.readFile(keyFile)),sc.getNumWords(sc.readFile(SpellingMistakeFile))),scorefileSpMistake);
                sc.writeScoreTofile(sc.getSessionScore(sc.getNumWords(sc.readFile(keyFile)),sc.getNumWords(sc.readFile(TypoFile))),scorefileTypo);
                sc.writeTotalTofile(sc.getNumWords(sc.readFile(keyFile)),totalWordsfile);
                sc.writeTotalTofile(sc.getNumWords(sc.readFile(SpellingMistakeFile)),totalSpellingfile);
                sc.DeleteFileContent(SpellingMistakeFile);
                sc.DeleteFileContent(TypoFile);
            }
        }));


        String[] wordList = sc.readDictionary("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\words.txt");

        String input =sc.GetTextInput(sc.readKey("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\keys"));

        System.out.println(input);

        if(sc.Spellcheck(input, wordList))
        {
            System.out.println(("Errors"));
        }else{
            System.out.println("No Errors");
        }




        try{
            GlobalScreen.registerNativeHook();

        }catch (NativeHookException e){
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(new Main());
        GlobalScreen.addNativeMouseListener(new Main());


    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent arg0) {


        String keyText = NativeKeyEvent.getKeyText(arg0.getKeyCode());

        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer = new PrintWriter(os)){





            if (keyText.length() > 1) {

                //  writer.println(keyText);

                if (keyText =="Backspace")
                {

                    writer.print("¬");
                }
                else
                {
                    writer.println();
                }

                //   writer.println("\n[" + keyText + "]");

            }


            else{

                writer.print(keyText);


            }


        }catch (IOException ex) {

            System.exit(-1);
        }


    }



    @Override
    public void nativeKeyReleased(NativeKeyEvent arg0) {


    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent arg0)  {


    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent arg0) {
        System.out.println("Mouse Clicked: " + arg0.getClickCount());


        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer = new PrintWriter(os))
        {
            writer.println("");
        }
        catch (IOException ex) {

            System.exit(-1);
        }

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}

