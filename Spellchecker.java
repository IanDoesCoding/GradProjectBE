package com.typechecker.typesystem;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Spellchecker {
    private static final Path file = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\spellingMistakes");
    private static final Path Typofile = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\typo");
    private static final Path scorefileSpMistake = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\scorefileSpMistake");
    private  static final Path totalMistake = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalSpellingMistakes");
    private static final Path totalWords = Paths.get("C:\\SoloProject\\Project_BE\\typesystem\\typesystem\\src\\main\\java\\com\\typechecker\\typesystem\\totalWords");



    public  boolean  Spellcheck(String input,String[] words){

        String currentCheck = "";

        boolean errors = false;
        Scanner  Spellchecker = new Scanner(input);

        Spellchecker.useDelimiter("\\s+");

        // if(grammerCheck(input, input.length()))
        // {
        //     errors = true;
        // }

        while (Spellchecker.hasNext())

        {
            currentCheck = Spellchecker.next();
            if (!isSpecial(currentCheck))
            {
                if (!checkWord(currentCheck,words))
                {

                    try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer = new PrintWriter(os)){
                        writer.println(currentCheck);

                    }
                    catch(IOException ex)
                    {
                        System.exit(-1);
                    }

                    System.out.println(currentCheck+" "+ "is spelt wrong");
                    errors=true;
                }
            }
        }
        return errors;
    }


    public static  boolean isSpecial(String input)
    {
        Pattern patter = Pattern.compile("[^a-z0-9 ]",Pattern.CASE_INSENSITIVE);
        Matcher match = patter.matcher(input);
        return match.find();
    }

    public static  boolean checkWord(String input,String[] words )
    {
        boolean valid = false;
        int length = words.length;
        int i=0;
        while ( !valid && i < length)
        {
            if(input.trim().equalsIgnoreCase(words[i]))
            {
                if(input.trim().equalsIgnoreCase(words[i]))   // CHECK NB why 2
                {
                    valid = true;
                    if(input.trim().equals("I"))
                    {
                        valid = true;
                    }
                    else if (input.trim().equals("i"))
                    {
                        valid = false;
                    }
                }

            }
            i++;

        }
        return valid;
    }




    // public static boolean grammerCheck(String input, int length)
    // {
    //     boolean validGrammer = true;
    //     int lastCharacter = length-1;

    //     if(input.charAt(lastCharacter)!='.')
    //     {
    //         System.out.println("Missing fullstop at the end of the sentence");
    //         validGrammer = false;
    //     }

    //     if(!Character.isUpperCase(input.charAt(0)))
    //     {
    //         System.out.println("Must start with uppercase character");
    //         validGrammer = false;

    //     }
    //     return validGrammer;
    // }

    public  String[] readDictionary(String filepath)
    {
        ArrayList<String> records = new ArrayList<String>();

        try
        {
            Scanner scan;
            scan = new Scanner(new File(filepath));
            scan.useDelimiter("[,\n]");
            while (scan.hasNext())
            {
                records.add(scan.next());
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        String recordsArray[] = new String[records.size()];
        recordsArray = records.toArray(recordsArray);

        return recordsArray;
    }

    public String[] readFile(String filepath)
    {

        ArrayList<String> spellingMistakes = new ArrayList<String>();

        try
        {
            Scanner scan;
            scan = new Scanner(new File(filepath));
            scan.useDelimiter("[,\n]");
            while (scan.hasNext())
            {
                spellingMistakes.add(scan.next());
            }

        } catch(Exception e)
        {
            System.out.println(e);
        }

        String spellingMistakesArray[] = new String[spellingMistakes.size()];
        spellingMistakesArray = spellingMistakes.toArray(spellingMistakesArray);

        ArrayList<String> formatSpellingMistakes = new ArrayList<String>();

        for(var i = 0; i < spellingMistakesArray.length; ++i)
        {
            spellingMistakesArray[i] = spellingMistakesArray[i].replace("\r","");
            if (spellingMistakesArray[i] != "")
            {
                formatSpellingMistakes.add(spellingMistakesArray[i]);
            }
        }


        String FormatArray[] = new String[formatSpellingMistakes.size()];
        FormatArray = formatSpellingMistakes.toArray(FormatArray);

         return  FormatArray;

    }

    public String getMode (String[] array){



            // Create HashMap to store word and it's frequency
            HashMap<String, Integer> hs = new HashMap<String, Integer>();

            // Iterate through array of words
            for (int i = 0; i < array.length; i++) {
                // If word already exist in HashMap then increase it's count by 1
                if (hs.containsKey(array[i])) {
                    hs.put(array[i], hs.get(array[i]) + 1);
                }
                // Otherwise add word to HashMap
                else {
                    hs.put(array[i], 1);
                }
            }

            // Create set to iterate over HashMap
            Set<Map.Entry<String, Integer> > set = hs.entrySet();
            String key = "";
            int value = 0;

            for (Map.Entry<String, Integer> me : set) {
                // Check for word having highest frequency
                if (me.getValue() > value && !me.getKey().contains("¬")) {
                    value = me.getValue();
                    key = me.getKey();
                }
            }

            // Return word having highest frequency

           System.out.println(key);
            System.out.println(value);
           System.out.println(key);

            return key;
        }





    public String[] readKey (String filepath)
    {
        ArrayList<String> keyrecords = new ArrayList<String>();

        try
        {
            Scanner kscan;
            String word;
            kscan = new Scanner(new File(filepath));
            kscan.useDelimiter("[,\n]");
            while (kscan.hasNext())
            {


                keyrecords.add(kscan.next());

                //System.out.println(word);


            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        ArrayList<String> formatArrayList = new ArrayList<String>();

        String keyrecordsArray[] = new String[keyrecords.size()];
        keyrecordsArray = keyrecords.toArray(keyrecordsArray);


        for(var i = 0; i < keyrecordsArray.length; ++i)
        {
            keyrecordsArray[i] = keyrecordsArray[i].replace("\r","");
            if (keyrecordsArray[i] != "")
            {
                formatArrayList.add(keyrecordsArray[i]);
            }
        }

        String FormatArray[] = new String[formatArrayList.size()];
        FormatArray = formatArrayList.toArray(FormatArray);

        System.out.println(FormatArray);



        return FormatArray;
    }


    public String  GetTextInput(String[] arr){

        String inputString = "";
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i] != "")
            {

                //ADD SPACE STRING HANDELING turn backspaceinto - or

                if(arr[i].contains("¬") )
                {
                    inputString = (inputString + arr[i]+ " ").trim();
                    //GET POS OF
                    while(inputString.contains("¬"))
                    {

                        int backspacePos = inputString.lastIndexOf("¬");
                        if(inputString.charAt(backspacePos-1)== '¬')
                        {
                            // See amount of backspace and delete nr before 1 st backspace and from 1 backspace to last backspace

                            int totalBackspace= 0;
                            char temp;
                            for (int c = 0; c < inputString.length(); c++) {

                                temp = inputString.charAt(c);
                                if (temp == '¬')
                                    totalBackspace++;
                            }
                            int firstBackspace= inputString.indexOf('¬');
                            int lastBackspace =inputString.lastIndexOf('¬');

                            inputString = inputString.substring(0, (firstBackspace-totalBackspace)) + inputString.substring(lastBackspace+1); // space

                            // Add string to typo
                            int lastSpacePos=inputString.lastIndexOf(" ");
                            String typo = inputString.substring(lastSpacePos+1);
                            System.out.println(typo);

                            inputString = inputString+" " ;

                            System.out.println(inputString);

                            try (OutputStream os = Files.newOutputStream(Typofile, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer = new PrintWriter(os)){
                                writer.println(typo);

                            }catch(IOException ex){
                                System.exit(-1);
                            }



                            System.out.println(inputString);

                            // inputString = inputString.substring(0,(backspacePos-1))+inputString.substring((backspacePos+1))+" ";
                        }
                        else{
                            System.out.println(inputString);
                            int lasSpacePos= inputString.lastIndexOf(" ");
                            int backsPos = inputString.lastIndexOf("¬");
                            String typo = inputString.substring(lasSpacePos,(backspacePos-1))+inputString.substring((backspacePos+1));
                            try (OutputStream os = Files.newOutputStream(Typofile, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer = new PrintWriter(os)){
                                writer.println(typo);

                            }catch(IOException ex){
                                System.exit(-1);
                            }
                            System.out.println(typo);
                            inputString = inputString.substring(0,(backspacePos-1))+inputString.substring((backspacePos+1))+" ";
                            System.out.println(inputString);
                        }

                    }


                    System.out.println(inputString);;
                }

                else{
                    inputString = (inputString + arr[i]+ " ");
                }



            }

        }
        System.out.println(inputString);
        return inputString;
    }

    private ArrayList<String> makeSuggestions(String input,String[] dict, char [] alphabet) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input,dict,alphabet));
        toReturn.addAll(charMissing(input,dict,alphabet));
        toReturn.addAll(charsSwapped(input,dict,alphabet));
        return toReturn;
    }

    private ArrayList<String> charAppended(String input,String[] dict, char [] alphabet) {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i =0; i<alphabet.length; i++) {
            char c = alphabet[i];
            String atFront = c + input;
            String atBack = input + c;

            boolean valid1 = false;
            boolean valid2 = false;
            int length = dict.length;

            int a=0;
            while ( a < dict.length)
            {
                if(!valid1 && atFront.trim().equalsIgnoreCase(dict[a]))
                {
                    valid1 = true;
                    toReturn.add(atFront);
                }

                if (!valid2 && atBack.trim().equalsIgnoreCase(dict[a]))
                {
                    valid2 = true;
                    toReturn.add(atBack);
                }
                a++;
            }

        }
        return toReturn;
    }

    private ArrayList<String> charMissing(String input,String[] dict, char [] alphabet) {
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        boolean valid1 = false;

        int length = dict.length;

        int b=0;
        while ( b < dict.length) {
            //try removing char from the front
            if (!valid1 && input.trim().substring(1).equalsIgnoreCase(dict[b])) {
                valid1 = true;
                toReturn.add(input.trim().substring(1));
            }
            b++;
        }


        for (int i = 1; i < len; i++) {
            //try removing each char between (not including) the first and last
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            int c=0;
            boolean valid2 = false;
            while ( c < dict.length) {
                if (!valid2 && working.trim().equalsIgnoreCase(dict[c]))
                {
                    valid2 = true;
                    toReturn.add(working);
                }
                c++;
            }
            boolean valid3 = false;
            int d =0;
            while ( d < dict.length) {
                if (!valid3 && input.trim().substring(0,len).equalsIgnoreCase(dict[d]))
                {
                    valid3 = true;
                    toReturn.add(input.substring(0, len));
                }
                d++;
            }
            }

        return toReturn;
    }

    private ArrayList<String> charsSwapped(String input,String[] dict, char [] alphabet) {
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);// System.out.println("    0:" + working);
            working = working + input.charAt(i + 1);  //System.out.println("    1:" + working);
            working = working + input.charAt(i); //System.out.println("    2:" + working);
            working = working.concat(input.substring((i + 2)));//System.out.println("    FIN:" + working);

            boolean valid1 = false;
            int e =0;
            while ( e < dict.length) {
                if (!valid1 && working.trim().equalsIgnoreCase(dict[e]))
                {
                    valid1 = true;
                    toReturn.add(working);
                }
                e++;
            }

        }
        return toReturn;
    }

    public String getNumWords(String[] arr){

        int Amount = (arr.length );
         if (Amount == 0)
         {
             Amount = 0;
         }
        return String.valueOf(Amount);

         // run when system begins
        // on app exit clear all files except count

    }

    public void DeleteFileContent(String filename){
        File files = new File(filename);
        if(files.length() != 0) {
            try {
                new FileWriter(filename, false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSessionScore(String TotalWords,String div){
        int score;
        if (Integer.valueOf(div) != 0 && Integer.valueOf(TotalWords) != 0 )
        {
            score = Math.round((Integer.valueOf(TotalWords))/(Integer.valueOf(div)));
        }
        else {
             score =0;
        }


        return String.valueOf(score);
    }


    public void writeScoreTofile(String score,Path filepath)
    {

        if (Integer.valueOf(score) != 0) {
            try (OutputStream os = Files.newOutputStream(filepath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
                writer.println(score);

            } catch (IOException ex) {
                System.exit(-1);
            }
        }
    }

    public void writeTotalTofile(String score,Path filepath)
    {
            try (OutputStream os = Files.newOutputStream(filepath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
                writer.println(score);

            } catch (IOException ex) {
                System.exit(-1);
            }

    }

    public ArrayList<String> getNumAllCategories(String Words, String SpellingMistakes,String Typo)
    {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add(Words);
        toReturn.add(SpellingMistakes);
        toReturn.add(Typo);
        return toReturn;
    }


 public Map<String, String> returnObj(String[] totalwords,String[] totalSpellingmistakes)
    {
        ArrayList<String> data;
        HashMap<String, String> map = new HashMap<>();

        for (var i = 0; i < totalwords.length; i++) {
            var val1 = totalwords[i];
            var val2 = totalSpellingmistakes[i];

             map.put(val1,val2);



        }

        return map;
    }


}
