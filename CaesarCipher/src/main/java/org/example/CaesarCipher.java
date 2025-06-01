package org.example;

public class CaesarCipher implements Cipher {
  private static CaesarCipher caesarCipherInstance = new CaesarCipher();

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
        if (Character.isUpperCase(c)) {
          shiftedChar = (char) ((c - 'A' + n) % 26 + 'A');
          shiftedMessage.append(shiftedChar);
        } else {

          shiftedChar = (char) ((c - 'a' + n) % 26 + 'a');
          shiftedMessage.append(shiftedChar);
        }
      } else if (isRussian(c)) {
        if (Character.isUpperCase(c)) {
          shiftedChar = (char) ((c - 'А' + n) % 33 + 'А');
          shiftedMessage.append(shiftedChar);
        } else {

          shiftedChar = (char) ((c - 'а' + n) % 33 + 'а');
          shiftedMessage.append(shiftedChar);
        }
      } else {
        shiftedMessage.append(c);
      }

    }
    return shiftedMessage.toString();
  }

  public boolean isRussian(char c) {
    return (c >= 'А' && c < 'Я') || (c >= 'а' && c <= 'я');
  }

  public boolean isLatin(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

}
