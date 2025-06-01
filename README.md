# Gehtsoft Technical Assessment
The goal of the assignment was to implement arithmetic evaluation and Caesar Cipher algorithms. Current realization is consist of CLI tool, which contains both algorithms.

## Technological stack
- Java 21

## Implementation
The Caesar cipher algorithm supports Russian and Latin letters (Small and capital). It accepts input from stdin or file (by writing absolute path).
Example of the file (encryption mode):
```
encryption
Огород
3


```
The Caesar cipher implementation supports decryption without given shifted value using **frequency analysis algorithm**. The tool considers letters *о* and *e* as the most frequent letter in Russian and Latin language correspondingly. So, if the text is huge enough, algorithm could successfully decrypt message. 

The arithmetic evaluation algorithm utilize reverse polish notation. First, it convert given expression into polish notation.  Then, it calculates the output. The algorithm obeys PEMDAS/BODMAS rules and supports the following operations:
 +-*/. All negative values should be in brackets (i.e. (-2)), except the cases  when negative number is located in the beginning of the expression or goes right after open bracket.
## How to Build

Go to source root of the project
```bash
cd CaesarCipher_ArithmeticEval
```
Run 
```bash
mvn install
```

## How to Run
From the same folder run
```bash
java -cp ./target/CaesarCipher_ArithmeticEval-1.0-SNAPSHOT.jar org.example.Main

```
The menu in console will pop up
```bash
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit

```

- Type *1* to utilize Caesar Cipher algorithm
- Type *2* to use Arithmetic Expression Evaluation algorithm

## Examples
### Decryption without shifted value
```bash
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit


1
Caesar Cipher Algorithm was chosen
Select input source. Type <file> or <stdin>
stdin
Enter mode (encryption/decryption):
decryption
Enter message:
Съзря тусфхсм хзнфх
Enter shift (optional for decryption - put just enter) :

Result:
Очень простой текст
```
### Input via file
/home/aziz/test.txt contains
```
encryption
Огород
3
```
```bash
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit


1
Caesar Cipher Algorithm was chosen
Select input source. Type <file> or <stdin>
file
The file was chosen as input.
Please, type ABSOLUTE path to the file:

/home/aziz/test.txt
Result:
Сёсусж

```

Arithmetic evaluation with negative values (-5)

```bash
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit


2  
Arithmetic Expression Evaluation was chosen.
Please, enter the expression


-2 * (-3)
Answer: 6.0


```
Division by 0 
```bash
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit


2
Arithmetic Expression Evaluation was chosen.
Please, enter the expression


0/0
Exception in thread "main" java.lang.ArithmeticException: Division by 0
        at org.example.ArithmeticEvalAlgo.ReversePolishNotation.evaluateExpression(ReversePolishNotation.java:112)
        at org.example.Main.main(Main.java:41)

```
Complex example 
```
Welcome to Gehtsoft Technical Assessment
Please choose an option (type the number):
1. Caesar Cipher Algorithm
2. Arithmetic Expression Evaluation
3. Exit


2
Arithmetic Expression Evaluation was chosen.
Please, enter the expression


5 * (-4+2) /(-5)
Answer: 2.0

```
