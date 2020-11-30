package io.simplecrypt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {

    ROT13(Character cs, Character cf) {
    }

    ROT13() {
    }


    public String crypt(String text) throws UnsupportedOperationException {

        String ePhrase = "";
        for (char letter : text.toCharArray()) {
            int asciiLetter = (int) letter;
            int eAsciiLetter = asciiLetter;
            if(Character.isUpperCase(letter)) {
                eAsciiLetter = (((asciiLetter - 65 + 13) % 26) + 65);
            }
            if(Character.isLowerCase(letter)){
                eAsciiLetter = (((asciiLetter - 97 + 13) % 26) + 97);
            }
            char eLetter = (char) eAsciiLetter;
            ePhrase += Character.toString(eLetter);
        }
        return ePhrase;
    }

    public String encrypt(String text) {
        return crypt(text);
    }

    public String decrypt(String text) {
        return crypt(text);
    }

    public static String rotate(String s, Character c) {
        char[] rotatedArr = new char[s.toCharArray().length];
        for(int i = 0; i<s.toCharArray().length; i++){
            if(i>=s.indexOf(c)) {
                rotatedArr[i-s.indexOf(c)] = s.toCharArray()[i];
            }
            else{
                rotatedArr[s.indexOf(c)+i] = s.toCharArray()[i];
            }
        }
        return new String(rotatedArr);
    }

    public void createFile() {
        try{
            File file = new File("sonnet18.enc");
        }
        catch(Exception e){
            System.out.println("An error occurred");
        }
    }

    public void encryptFile() throws FileNotFoundException {
        createFile();

        FileOutputStream fos = new FileOutputStream("sonnet18.enc");
        PrintWriter pw = new PrintWriter(fos);

        try {
            List<String> stream = Files.lines(Paths.get("sonnet18.txt"))
                    .collect(Collectors.toList());
            for(String s: stream){
                pw.println(crypt(s));
            }
            pw.close();
        }
        catch(IOException e){
            System.out.println("An error occurred");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ROT13 rot13 = new ROT13('a','n');
        rot13.encryptFile();
    }

}
