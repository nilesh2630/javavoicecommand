package org.example;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import org.example.GifNameGUI;

public class voiceControl {
    // KMP algorithm for pattern matching
    private static int[] computeLPSArray(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    private static boolean isSubstring(String text, String pattern) {
        text = text.toUpperCase();

        int m = pattern.length();
        int n = text.length();

        int[] lps = computeLPSArray(pattern);

        int i = 0;
        int j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                return true;
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\main\\resources\\5609.dic");
        config.setLanguageModelPath("src\\main\\resources\\5609.lm");
        GifNameGUI gui=new GifNameGUI();
        try {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true);

            SpeechResult speechResult = null;

            while ((speechResult = speech.getResult()) != null) {
                System.out.println("Listening.......");
                String voiceCommand = speechResult.getHypothesis();
                gui.ok(voiceCommand);
                System.out.println("Voice Command is: " + voiceCommand);

                if (isSubstring(voiceCommand, "OPEN CHROME")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.google.com");
                } else if (isSubstring(voiceCommand, "CLOSE CHROME")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                } else if (isSubstring(voiceCommand, "OPEN YOUTUBE")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.youtube.com");
                } else if (isSubstring(voiceCommand, "CLOSE YOUTUBE")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                } else if (isSubstring(voiceCommand, "MUSIC")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.youtube.com/watch?v=6-n_szx2XRE&ab_channel=RahatFAKhanVEVO");
                } else if (isSubstring(voiceCommand, "STOP MUSIC")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                }


                gui.kk();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting the program...");
        System.exit(0);
    }


}
