package org.example.CaesarCipher;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CaesarCipher implements Cipher {
  private static final char MOST_FREQUENT_CHAR_RUSSIAN = 'о';
  private static final char MOST_FREQUENT_CHAR_LATIN = 'e';
  private static final CaesarCipher caesarCipherInstance = new CaesarCipher();
  private static final String RUSSIAN_ALPHABET =
          "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  private static final String LATIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  private static final int RUSSIAN_ALPHABET_LEN = 33;
  private static final int LATIN_ALPHABET_LEN = 26;

  private CaesarCipher() {
  }

  ;

  public static CaesarCipher getInstance() {
    return caesarCipherInstance;
  }

  @Override
  public String shift(int n, String message) {
    StringBuilder shiftedMessage = new StringBuilder();
    for (char c : message.toCharArray()) {

      char shiftedChar;
      if (isLatin(c)) {
        boolean isUpper = Character.isUpperCase(c);
        int latinIndex =
                (LATIN_ALPHABET.indexOf(
                        Character.toLowerCase(c))+n)% LATIN_ALPHABET_LEN;
        if (latinIndex < 0) latinIndex += LATIN_ALPHABET_LEN;
        shiftedChar = LATIN_ALPHABET.charAt(latinIndex);
        shiftedMessage.append(isUpper ? Character.toUpperCase(shiftedChar) :
                shiftedChar);
      } else if (isRussian(c)) {

        boolean isUpper = Character.isUpperCase(c);
        int ruIndex =
                (RUSSIAN_ALPHABET.indexOf(
                        Character.toLowerCase(c)) + n) % RUSSIAN_ALPHABET_LEN;
        if (ruIndex < 0)ruIndex += RUSSIAN_ALPHABET_LEN;
        shiftedChar = RUSSIAN_ALPHABET.charAt(ruIndex);
        shiftedMessage.append(isUpper ? Character.toUpperCase(shiftedChar) :
                shiftedChar);
      } else {
        shiftedMessage.append(c);
      }

    }
    return shiftedMessage.toString();
  }

  public boolean isRussian(char c) {
    return RUSSIAN_ALPHABET.indexOf(Character.toLowerCase(c)) != -1;
  }

  public boolean isLatin(char c) {
    return LATIN_ALPHABET.indexOf(Character.toLowerCase(c)) != -1;
  }

  // use frequency analysis
  public int calculateShift(String message) {
    Map<Character, Integer> frequency = calcFrequency(message);

    Optional<Character> mostFrequentChar = frequency.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey);

    if (mostFrequentChar.isPresent()) {
      char mostFreq = mostFrequentChar.get();
      if (this.isLatin(mostFreq)) {
        return LATIN_ALPHABET.indexOf(mostFreq) - LATIN_ALPHABET.indexOf(MOST_FREQUENT_CHAR_LATIN);
      } else if (this.isRussian(mostFreq)) {
        return RUSSIAN_ALPHABET.indexOf(mostFreq) - RUSSIAN_ALPHABET.indexOf(MOST_FREQUENT_CHAR_RUSSIAN);
      }
    }

    throw new RuntimeException(
            "Message is empty or does not contain valid letters");
  }

  private Map<Character, Integer> calcFrequency(String message) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : message.toLowerCase()
            .toCharArray()) {
      if (Character.isLetter(c)) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
      }
    }
    return freq;
  }
}
