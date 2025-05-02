# Arbitrary Precision Library

## Info
This Java Library contains classes AInteger and AFloat which which can ensure Infinite precision for integers and 30 decimal precision for float

This Library is created as a Final Project for CS1023 course at IIT HYDERABAD.

### The main challenges in the project were:
- Handling leading and trailing zeroes
- Handling the sign in various contexts
- Decimal point placing in Float operations
- various types of comparision functions required as helpers

## Introduction
Precision in certain Data types of Java might not be sufficient in complex calculations where even a small error could become  significant so this *Arbitrary precision library* is created to  achieve greater precision .

### Limitations of the standard types in Java:
The data range of the standard data types in Java are:
- `int` : **-2147483648** to **2147483647**
- `long` : **-9223372036854775808** to **9223372036854775807**
- `float` : **1.40239846e-45** to **3.40282347e+38**
- `double` : **4.94065645841246544e-324** to **1.79769313486231570e+308**

### What this library supports:
  'Add' 'Subtract' 'Multiply' 'Divide' functions for both integer and float data types with an infinite precision for integers and a 30 decimal precison for float data type 

### Components of The Library
- AInteger
  - This class handles parsing integers and all integer operations
  - Stores each Integer as a string from the object
  - storing as a string avoids overflow
  - supports Addition,Subtraction,Multiplication and Division
 -AFLoat
  - This class handles parsing integers and all integer operations
  - stores each float as a string from the object
  - storing as a string avoids overflow
  - supports Addition,Subtraction,Multiplication,Division
### How to use
