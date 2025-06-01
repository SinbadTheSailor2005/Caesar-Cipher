package org.example.CaesarCipher;

public interface Cipher {
  String shift(int n, String message); // encode letters on n positions forward
}
