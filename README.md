# Ceasar Cipher

[![Java CI with Gradle](https://github.com/alexkmj/cipher/actions/workflows/gradle.yml/badge.svg)](https://github.com/alexkmj/cipher/actions/workflows/gradle.yml)

The main file can be found here: [https://github.com/alexkmj/cipher/blob/master/app/src/main/java/cipher/Cipher.java](https://github.com/alexkmj/cipher/blob/master/app/src/main/java/cipher/Cipher.java)

From [Wikipedia](https://en.wikipedia.org/wiki/Caesar_cipher):
> In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift
cipher, Caesar's code or Caesar shift, is one of the simplest and most widely
known encryption techniques. It is a type of substitution cipher in which each
letter in the plaintext is replaced by a letter some fixed number of positions
down the alphabet. For example, with a left shift of 3, D would be replaced by
A, E would become B, and so on. The method is named after Julius Caesar, who
used it in his private correspondence.

## Usage

The file `Cipher.java` contains the implementation of the cipher. Calling
`encode` will return the encoded string with a random shift value. Calling
`decode` will return the decoded string with the first character as the shift
value.

```java
Cipher cipher = new Cipher();

String encoded = cipher.encode("HELLO WORLD");
String decoded = cipher.decode(encoded);
```

## Test

```
./gradlew test
```

## Build

```
./gradlew build
```

## Run

```
./gradlew run
```
