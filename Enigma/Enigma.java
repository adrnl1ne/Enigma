package Enigma;

import java.util.Locale;
import java.util.Scanner;

public class Enigma {
  //Create user input
  Scanner input = new Scanner(System.in);
  //import color
  public static final String TEXT_RESET = "\u001b[0m";
  public static final String TEXT_RED = "\u001b[31m";
  public static final String TEXT_GREEN = "\u001b[32m";
  public static final String TEXT_PURPLE = "\u001b[35m";
  public static final String TEXT_stuff = "\u001b[34m";

  int inButInt;
  String in;
  String t = "";


  public int getIntInput() {
    return input.nextInt();
  }

  public void callIntInput() {
    inButInt = getIntInput();
  }

  public String getStringInput() {
    return input.nextLine();
  }

  public void callStringInput() {
    in = getStringInput().toLowerCase(Locale.ROOT);
  }

  public void greetUser() {
    System.out.println(TEXT_GREEN + "Welcome to the Enigma simulator!" + TEXT_RESET);
    encryptOrDecrypt();
    System.out.print(TEXT_RED + "Please enter your desired option: " + TEXT_RESET);

  }

  public void encryptOrDecrypt() {
    System.out.println(TEXT_stuff + "1. " + TEXT_PURPLE + "Encrypt");
    System.out.println(TEXT_stuff + "2. " + TEXT_PURPLE + "Decrypt");

  }

  public void encryptAndDecryptMethods() {
    System.out.println(TEXT_stuff + "1. " + TEXT_PURPLE + "Caesar");
    System.out.println(TEXT_stuff + "2. " + TEXT_PURPLE + "Vigenére");
    System.out.println(TEXT_stuff + "3. " + TEXT_PURPLE + "Number");
    System.out.println(TEXT_stuff + "4. " + TEXT_PURPLE + "Substitution");
    System.out.print(TEXT_RED + "Please enter your desired option: " + TEXT_RESET);
  }

  public void encryptOrDecryptChecker() {
    callIntInput();
    if (inButInt == 1) {
      System.out.println("What type of encryption do you wish to use?");
      methodCheckerEncrypt();
    } else {
      System.out.println("What type of decryption do you wish to use?");
      methodCheckerDecrypt();
    }
  }

  public void methodCheckerEncrypt() {
    encryptAndDecryptMethods();
    callIntInput();
    if (inButInt == 1) {
      encryptCaesar();
    } else if (inButInt == 2) {
      encryptVignere();
    } else if (inButInt == 3) {
      encryptNumber();
    } else if (inButInt == 4) {
      encryptSubstitution();
    } else {
      System.out.println("You might have misspelled, try again");
      methodCheckerEncrypt();
    }
  }

  public void encryptCaesar() {

  }

  public void encryptVignere() {

  }

  public void encryptNumber() {
    input.nextLine();
    System.out.print(TEXT_RED + "Please enter the message wanted for encryption: " + TEXT_RESET);
    callStringInput();
    for (int i = 0; i < in.length(); i++) {
      char indexAtChar = in.charAt(i);
      if (!t.isEmpty()) {
        t += " ";
      }
      int n = (int) indexAtChar - (int) 'a' + 1;
      t += String.valueOf(n);
    }
    System.out.println(t);
  }

  public void encryptSubstitution() {

  }

  public void methodCheckerDecrypt() {
    encryptAndDecryptMethods();
    callIntInput();
    if (inButInt == 1) {
      decryptCaesar();
    } else if (inButInt == 2) {
      decryptVignere();
    } else if (inButInt == 3) {
      decryptNumber();
    } else if (inButInt == 4) {
      decryptSubstitution();
    } else {
      System.out.println("You might have misspelled, try again");
      methodCheckerDecrypt();
    }
  }

  public void decryptCaesar() {

  }

  public void decryptVignere() {

  }

  public void decryptNumber() {

  }

  public void decryptSubstitution() {

  }

  public void runScript() {
    greetUser();
    encryptOrDecryptChecker();

  }
}
