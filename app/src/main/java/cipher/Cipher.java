package cipher;

import java.util.Random;

class Cipher {
    /**
     * The maximum shift value.
     */
    private static final int MAX_SHIFT = 44;

    /**
     * The shift value.
     */
    private int shift;

    /**
     * The random number generator.
     */
    private final Random random = new Random(1);

    /**
     * Creates a new cipher with a shift of 0.
     */
    public Cipher() {
        this.shift = -1;
    }

    /**
     * Creates a new cipher with the given shift.
     * @param shift the shift value
     */
    public Cipher(String offsetCharacter) {
        if (offsetCharacter.length() != 1) {
            throw new IllegalArgumentException("Offset character must be a single character.");
        }

        this.shift = getIndex(offsetCharacter.charAt(0));
    }

    public Cipher(char offsetCharacter) {
        this.shift = getIndex(offsetCharacter);
    }

    /**
     * Returns the plaintext for the given ciphertext.
     * 
     * @param encodedText the ciphertext
     * @return the plaintext
     */
    public String decode(String encodedText) {
        char[] text = encodedText.toCharArray();

        // the first character of the ciphertext is the shift value.
        int shift = getIndex(text[0]);

        // decode the rest of the ciphertext
        char[] plaintext = new char[text.length - 1];
        for (int i = 1; i < text.length; i++) {
            char originalChar = text[i];
            plaintext[i - 1] = getShiftChar(originalChar, shift);
        }

        // return the plaintext
        return new String(plaintext);
    }

    /**
     * Returns the ciphertext for the given plaintext.
     * @param plainText the plaintext
     * @return the ciphertext
     */
    public String encode(String plainText) {
        int shift = this.shift == -1
            ? random.nextInt(MAX_SHIFT)
            : this.shift;

        char[] text = plainText.toCharArray();

        // encode the plaintext with the first character being the shift value
        char[] ciphertext = new char[text.length + 1];
        ciphertext[0] = getChar(shift);

        for (int i = 0; i < text.length; i++) {
            char originalChar = text[i];
            ciphertext[i + 1] = getNegativeShiftChar(originalChar, shift);
        }

        // return the ciphertext
        return new String(ciphertext);
    }

    /**
     * Returns the character that is shifted by the shift value.
     * 
     * @param originalChar the original character
     * @return the shifted character
     */
    public char getNegativeShiftChar(char originalChar, int shift) {
        return getShiftChar(originalChar, shift * -1);
    }

    /**
     * Returns the character that is shifted by the given shift value.
     * @param originalChar the original character
     * @param shift the shift value
     * @return the shifted character
     */
    public char getShiftChar(char originalChar, int shift) {
        if (originalChar == ' ') {
            return ' ';
        }

        int originalIndex = getIndex(originalChar);
        int newCharIndex = (originalIndex + shift + MAX_SHIFT) % MAX_SHIFT;

        return getChar(newCharIndex);
    }

    /**
     * Returns the character for the given index.
     * @param index the index
     * @return the character
     * @throws IllegalArgumentException if the index is invalid
     */
    private static char getChar(int index) throws IllegalArgumentException {
        // case 0: invalid index
        if (index < 0 || index > 43) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }

        // case 1: uppercase letter
        if (index <= 25) {
            return (char) ('A' + index);
        }

        // case 2: number
        if (index <= 35) {
            return (char) ('0' + index - 26);
        }

        // case 3: `(` or `)`
        if (index == 36 || index == 37) {
            return (char) ('(' + index - 36);
        }

        // case 4: `*`, `+`, `,`, `-`, `.`, or `/`
        if (index >= 38 && index <= 43) {
            return (char) ('*' + index - 38);
        }

        // case 5: invalid index
        throw new IllegalArgumentException("Invalid index: " + index);
    }

    /**
     * Returns the index for the given character.
     * @param c the character
     * @return the index
     * @throws IllegalArgumentException if the character is invalid
     */
    private static int getIndex(char c) throws IllegalArgumentException {
        // case 0: uppercase letter
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }

        // case 1: number
        if (c >= '0' && c <= '9') {
            return c - '0' + 26;
        }

        // case 2: `(` or `)`
        if (c == '(' || c == ')') {
            return c - '(' + 36;
        }

        // case 3: `*`, `+`, `,`, `-`, `.`, or `/`
        if (c >= '*' && c <= '/') {
            return c - '*' + 38;
        }

        // case 4: invalid character
        throw new IllegalArgumentException("Invalid character: " + c);
    }
}
