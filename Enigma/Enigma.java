package Enigma;

import java.util.Arrays;
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
  String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String caesarEncrypt1 = "Z ABCDEFGHIJKLMNOPQRSTUVWXY";
  String caesarEncrypt2 = "YZ ABCDEFGHIJKLMNOPQRSTUVWX";
  String in;
  String t = "";
  String finalWord = "";
  String keyMap;
  String msg;
  String message;
  String mappedKey;


  public int getIntInput() {
    return input.nextInt();
  }

  public void callIntInput() {
    inButInt = getIntInput();
  }

  public String getStringInput() {
    return input.nextLine();
  }

  public void callStringInputLowerCase() {
    in = getStringInput().toLowerCase(Locale.ROOT);
  }

  public void callStringInputUpperCase() {
    in = getStringInput().toUpperCase(Locale.ROOT);
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

  //Beginning of Caesar encryption
  public void encryptCaesar() {
    input.nextLine();
    System.out.print(TEXT_RED + "Please enter the message wanted for encryption: " + TEXT_RESET);
    callStringInputUpperCase();
    keyChecker();
  }

  public void keyChecker() {
    int[] stringArray = new int[in.length()];
    System.out.println(TEXT_stuff + "1.");
    System.out.println("2." + TEXT_RESET);
    System.out.print("Enter the wanted key for encryption: ");
    callIntInput();
    input.nextLine();
    for (int i = 0; i < in.length(); i++) {
      if (inButInt == 1) {
        stringArray[i] = in.charAt(i);
        stringArray[i] = caesarEncrypt1.indexOf(stringArray[i]);
        if (i == in.length() - 1) {
          System.out.println(Arrays.toString(stringArray));
        }
      } else if (inButInt == 2) {
        stringArray[i] = in.charAt(i);
        stringArray[i] = caesarEncrypt2.indexOf(stringArray[i]);
        if (i == in.length() - 1) {
          System.out.println(Arrays.toString(stringArray));
        }
      }
    }
  }
  //End of Caesar encryption

  //Beginning of Vigenere encrypt
  public void encryptVignere() {
    System.out.println(TEXT_RED + "***Message and key should be alphabetic***" + TEXT_RESET);
    enterMessage();
    mapTheKey();
    printVigenere();
    encryptCipher(message, mappedKey);

  }

  public void encryptCipher(String message, String mappedKey) {
    int[][] table = createVigenereTable();
    String encryptedText = "";
    for (int i = 0; i < message.length(); i++) {
      if (message.charAt(i) == (char) 32 && mappedKey.charAt(i) == (char) 32) {
        encryptedText += " ";
      } else {
        encryptedText += (char)table[ (int)message.charAt(i) - 65] [(int)mappedKey.charAt(i) -65];
      }
    }
    System.out.println("Encrypted Text: " + encryptedText);
  }

  public int[][] createVigenereTable() {
    //Create 26*26 table containing alphabets
    int[][] tableArray = new int[26][26];
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        int temp;
        if ((i + 65) + j > 90) {
          temp = ((i + 65) + j) + 26;
          tableArray[i][j] = temp;
        } else {
          temp = (i + 65) + j;
          tableArray[i][j] = temp;
        }
      }
    }
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        System.out.print((char) tableArray[i][j] + " ");
      }
      System.out.println();
    }
    return tableArray;
  }

  public void enterMessage() {
    //String input
    System.out.print("Please enter your message: ");
    input.nextLine();
    msg = getStringInput();
    msg = msg.toUpperCase(Locale.ROOT);

  }

  public void mapTheKey() {
    //Key input
    System.out.print("Please enter key: ");
    String key = getStringInput();
    key = key.toUpperCase(Locale.ROOT);

    //Map key to message
    keyMap = "";
    for (int i = 0, j = 0; i < msg.length(); i++) {
      if (msg.charAt(i) == (char) 32) {
        //Ignore space
        keyMap += (char) 32;

      } else {
        //Map letters of key with letters
        if (j < key.length()) {
          keyMap += key.charAt(j);
          j++;
        } else {
          //Restarting key from beginning, once length complete
          // And still not mapped
          j = 0;
          keyMap += key.charAt(j);
          j++; // key's first letter will be mapped twice
        }
      }

    }

  }

  public void printVigenere() {
    message = msg;
    mappedKey = keyMap;
    System.out.println("Message: " + message);
    System.out.println("Key: " + mappedKey);
  }
  //End of Vigenere encryption

  //Beginning of number encryption
  public void encryptNumber() {
    input.nextLine();
    System.out.print(TEXT_RED + "Please enter the message wanted for encryption: " + TEXT_RESET);
    callStringInputLowerCase();
    for (int i = 0; i < in.length(); i++) {
      char indexAtChar = in.charAt(i);
      //Creates a space between each letter after conversion
      if (!t.isEmpty()) {
        t += " ";
      }
      int n = (int) indexAtChar - (int) 'a' + 1;
      if (n < 0) {
        n = 0;
      }
      t += String.valueOf(n);
    }
    System.out.println(t);
  }
  //End of number encryption

  public void encryptSubstitution() {
    System.out.println("This function is currently unavailable");
    runScript();
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

  //Beginning of Caesar decryption
  public void decryptCaesar() {
    System.out.println(TEXT_stuff + "1.");
    System.out.println("2." + TEXT_RESET);
    System.out.print("Enter the wanted key for decryption: ");
    callIntInput();
    input.nextLine();
    System.out.print(TEXT_RED + "Please enter the keycode: " + TEXT_RESET);
    if (inButInt == 1) {
      enterKey1();
    } else if (inButInt == 2) {
      enterKey2();
    }
  }

  public void enterKey1() {
    callIntInput();
    char currentLetter = numToTextKey1(inButInt);
    finalWord += currentLetter;
    moreLettersKey1();
  }

  public char numToTextKey1(int inButInt) {
    return caesarEncrypt1.charAt(inButInt);
  }

  public char numToTextKey2(int inButInt) {
    return caesarEncrypt2.charAt(inButInt);
  }

  public void enterKey2() {
    callIntInput();
    char currentLetter = numToTextKey2(inButInt);
    finalWord += currentLetter;
    moreLettersKey2();
  }

  public void moreLettersKey2() {
    input.nextLine();
    System.out.print("Does the keycode contain more numbers? y/n: ");
    callStringInputLowerCase();
    if (in.contains("y")) {
      System.out.print("Please enter the next number: ");
      enterKey2();
    } else if (in.contains("n")) {
      System.out.println(finalWord);
    } else {
      System.out.println("You might have misspelled, try again");
      moreLettersKey2();
    }
  }

  public void moreLettersKey1() {
    input.nextLine();
    System.out.print("Does the keycode contain more numbers? y/n: ");
    callStringInputLowerCase();
    if (in.contains("y")) {
      System.out.print("Please enter the next number: ");
      enterKey1();
    } else if (in.contains("n")) {
      System.out.println(finalWord);
    } else {
      System.out.println("You might have misspelled, try again");
      moreLettersKey1();
    }
  }
  //End of Caesar decryption

  //Beginning of Vigenere decryption
  public void decryptVignere() {
    System.out.println("This function is currently unavailable");
    runScript();
  }
  //End of Vigenere decryption

  //Beginning of number decryption
  public void decryptNumber() {
    System.out.print(TEXT_RED + "Please enter the keycode: " + TEXT_RESET);
    enterKeycode();
  }

  public void enterKeycode() {
    callIntInput();
    char currentLetter = numToText(inButInt);
    finalWord += currentLetter;
    moreLetters();
  }

  public void moreLetters() {
    input.nextLine();
    System.out.print("Does the keycode contain more numbers? y/n: ");
    callStringInputLowerCase();
    if (in.contains("y")) {
      System.out.print("Please enter the next number: ");
      enterKeycode();
    } else if (in.contains("n")) {
      System.out.println(finalWord);
    } else {
      System.out.println("You might have misspelled, try again");
      moreLetters();
    }
  }

  public char numToText(int inButInt) {
    return alphabet.charAt(inButInt);
  }
  //End of number decryption

  public void decryptSubstitution() {
    System.out.println("This function is currently unavailable");
    runScript();
  }

  public void runScript() {
    greetUser();
    encryptOrDecryptChecker();
  }
}
