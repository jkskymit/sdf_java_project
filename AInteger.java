package arbitraryarithmetic;
public class AInteger {
    
    public String integer;

    
    public AInteger()
    {
        //Default constructor initializing the integer to "0"

        this.integer = "0";
    }

    public AInteger(String string)
    {   
        // A parametric constructor to iniatialize a string when a String is provided as an argument

        this.integer = string;
    }

    public AInteger(AInteger other_constructor)
    {   
        // Copy constructor that initiliases the value of this object to the value of the other object.

        this.integer = other_constructor.integer;
    }

    public static AInteger parse(String string)
    {   
        // Function to create an instance of AInteger from a string.

        return new AInteger(string);
    }

    /**
     * @param string1
     * @param string2
     * @return String
     * This function assumes that the length of the first string is not less than than that of the other and both are non-negative
     * performs Integer addition
     */
    public static String add_strings(String string1, String string2)
    {   
        //store lengths of the strings

        int length1 = string1.length();
        
        int length2 = string2.length();
        
        int carry = 0;
        
        String string_sum = "";
        
        for(int i=length1-1;i>=0;i--)
        {   
            // The following block does a digit by digit addition and maintains a carry till it uses all the digits of the second string
            if((length1-i) <= length2)
            {
                int sum_of_digits = (string1.charAt(i)-'0')+(string2.charAt(length2-(length1-i))-'0')+carry;
                
                carry = sum_of_digits/10;
                
                string_sum = Integer.toString(sum_of_digits-10*carry).concat(string_sum);
            }
            //This block does the same thing just that it only adds the carry and the digit of the first string using the same logic
            else
            {
                int sum_of_digit = (string1.charAt(i)-'0') + carry;
                
                carry = sum_of_digit/10;
                
                string_sum = Integer.toString(sum_of_digit -10*carry).concat(string_sum);
            }
        }
        // we add the leftover carry to the final sum
        if(carry!=0)
        {
            string_sum = Integer.toString(carry).concat(string_sum);
        }
        return string_sum;
    }

    
    /**
     * @param string1
     * @param string2
     * @return String
     * This function assumes that the length of the first argument is not less than the other and both are non-negative
     * performs Integer subtraction
     */
    public static String subtract_strings(String string1,String string2)
    {
        int length1 = string1.length();
        
        int length2 = string2.length();
        
        //store Stringlengths
         
        int borrow = 0;
        
        String string_difference = "";
        
        for(int i=length1-1;i>=0;i--)
        {   
            //The following block does digit by digit subtraction taking a borrow if needed till it uses all the digits in the second string
            if(length1-i <= length2)
            {
                int difference_of_digits;
                //This block is executed when you dont need to borrow
                if((string1.charAt(i) - '0') >= (string2.charAt(length2-(length1-i))-'0')+ borrow)
                {
                    
                    difference_of_digits = (string1.charAt(i)-string2.charAt(length2-(length1-i)))-borrow;
                    
                    borrow = 0;
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                }
                //This block is executed when you  borrow to perform subtraction between two digits
                else
                {
                    difference_of_digits = (10-borrow) +(string1.charAt(i)-string2.charAt(length2-(length1-i)));
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                    
                    borrow = 1;
                }
            }
            // This block is executed only when you have to subtract only the borrow which was used to perform digit by digit subtraction
            else
            {   
                int difference_of_digits;
                //This block subtracts the borrow which was taken from this element to an element further in the string
                if(string1.charAt(i)-'0' >= borrow)
                {
                    difference_of_digits = (string1.charAt(i)-'0') - borrow;
                    
                    borrow = 0;
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                }
                //This block is executed when the element had to borrow from the previous elements when an elemnt further in the string borrowed from it
                else
                {
                    difference_of_digits = 10-borrow + (string1.charAt(i)-'0');
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                    
                    borrow = 1;
                }
            }
        }
        
        return string_difference;
    }

    /**
     * @param string1
     * @param string2
     * @return Boolean
     * This function is used to konow if first string has more value than the second
     */
    public static Boolean isgreater(String string1,String string2)
    {   
        string1 = remove_leading_zeroes(string1);
        
        string2 = remove_leading_zeroes(string2);

        if(string1.charAt(0)=='-' || string2.charAt(0)=='-')
        {
            if(string1.charAt(0)=='-' && string2.charAt(0)=='-')
            {
                return islesser(string1.substring(1), string2.substring(1));
            }

            if(string1.charAt(0)=='-') return false;
            else return true;
        }

        int length1 = string1.length();
        
        int length2 = string2.length();
        
        if(length1 > length2) return true;
        
        if(length2 > length1) return false;
        
        for(int i=0;i<length1;i++)
        {
            if(string1.charAt(i)-'0' > string2.charAt(i)-'0')
            {
                return true;
            }
            if(string1.charAt(i)-'0' < string2.charAt(i)-'0')
            {
                return false;
            }
        }
        return false;
    }

    /**
     * @param string1
     * @param string2
     * @return Boolean
     * This function is to know if the value of the first string is less than the second
     */
    public static Boolean islesser(String string1,String string2)
    {   
        string1 = remove_leading_zeroes(string1);
        
        string2 = remove_leading_zeroes(string2);

        int length1 = string1.length();
        
        int length2 = string2.length();
        
        if(string1.charAt(0)=='-' || string2.charAt(0)=='-')
        {
            if(string1.charAt(0)=='-' && string2.charAt(0)=='-')
            {
                return isgreater(string1.substring(1), string2.substring(1));
            }
            
            if(string1.charAt(0)=='-') return true;
            else return false;
        }

        if(length1 < length2) return true;
        
        if(length2 < length1) return false;
        
        for(int i=0;i<length1;i++)
        {
            if(string1.charAt(i)-'0' < string2.charAt(i)-'0')
            {
                return true;
            }
            if(string1.charAt(i)-'0' > string2.charAt(i)-'0')
            {
                return false;
            }
        }
        return false;
    }
    
    /**
     * @param string1
     * @param string2
     * @return Boolean
     * This function is to know if the value of the first string is greater than or equal to the second
     */
    public static Boolean isgreater_or_equals(String string1,String string2)
    {
        if(isgreater(string1, string2) || remove_leading_zeroes(string1).equals(remove_leading_zeroes(string2))) return true;
        else return false;
    }

    /**
     * @param string1
     * @param string2
     * @return Boolean
     * This function is to know if the value of the first string is less than or equal to the second
     */
    public static Boolean islesser_or_equals(String string1,String string2)
    {
        if(islesser(string1, string2) || remove_leading_zeroes(string1).equals(remove_leading_zeroes(string2)) ) return true;
        else return false;
    }

    /**
     * @param string
     * @return String
     * This function removes all the leading zeroes which are not in the units place
     */
    public static String remove_leading_zeroes(String string)
    {
        int l = string.length();
        
        for(int i=0;i<l;i++)
        {   
            // This block preserves the sign of the string and just removes the leading zeroes 
            if(string.charAt(i)!='0' && string.charAt(i)!='-')
            {
                if(string.charAt(0)!='-')  return string.substring(i);
                else return "-" + string.substring(i);
            }
        }
        //This is executed only when there is no non zero element in the string
        return "0";
    }

    /**
     * @param string
     * @return String
     * This strips of the sign from the string
     */
    public static String absolute_string(String string)
    {
        if(string.charAt(0)=='-') return  string.substring(1);
        else return string;
    }

    /**
     * @param string1
     * @param string2
     * @return String
     * This function uses the helper functions add_strings,subtract_strings
     * This function passes the arguments to the helper functions in such a way that the assumptions of the helper functions are met.
     * This considers a lot of cases for their signs and lengths and produces the correct result with no exception
     */
    public static String Addition(String string1,String string2)
    {   
        // removing leading zeroes
        String formatted_string1 = remove_leading_zeroes(string1);
        
        String formatted_string2 = remove_leading_zeroes(string2);

        
        if(formatted_string1.charAt(0)=='-' || formatted_string2.charAt(0)=='-')
        {
            if(formatted_string1.charAt(0)=='-' && formatted_string2.charAt(0)=='-')
            {   
                //when both strings are negative we just add them and put a negative sign to the result
                String absolutestring1 = absolute_string(formatted_string1);
                
                String absolutestring2 = absolute_string(formatted_string2);
                
                //we check which is of greater length and pass that string as an argument so that the assumptions of the helper functions are not violated
                if(isgreater_or_equals(absolutestring1,absolutestring2))
                {
                    String Answer = add_strings(absolutestring1, absolutestring2);
                    
                    Answer = "-" + Answer; 
                    
                    return remove_leading_zeroes(Answer);
                }
                String Answer = add_strings(absolutestring2,absolutestring1);
                
                Answer = "-" + Answer;
                
                return remove_leading_zeroes(Answer);
            }
            // This part of the function is executed only when exactly one  of the strings are negative so its basically subtraction 

            String absolutestring1;
            
            String absolutestring2;
            
            absolutestring1 = absolute_string(formatted_string1);
            
            absolutestring2 = absolute_string(formatted_string2);
            
            /* The following two blocks are to check which among the two absolute strings has a greater value 
                so that the asssumptions of the helper functions are valid 
                And put the negative sign if the larger number had the negative sign */
            if(isgreater(absolutestring1, absolutestring2))
            {   
                String Answer = subtract_strings(absolutestring1, absolutestring2);
                
                if(formatted_string1.charAt(0)=='-') 
                {
                    Answer = '-' + Answer;
                }

                return remove_leading_zeroes(Answer);
            }
            else
            {
                String Answer = subtract_strings(absolutestring2, absolutestring1);
                
                if(formatted_string2.charAt(0)=='-')
                {
                    Answer = '-' + Answer;
                }

                return remove_leading_zeroes(Answer);
            }
        }
        // This block is executed only when both the strings are positive
        //The following blocks also ensure that the assumptions of the helper functions aren't violated 
        if(isgreater(formatted_string1, formatted_string2))
        {
            String Answer = add_strings(formatted_string1, formatted_string2);
           
            return remove_leading_zeroes(Answer);
        }
        else 
        {
            String Answer = add_strings(formatted_string2, formatted_string1);
            
            return remove_leading_zeroes(Answer);
        }
    }


    /**
     * @param string1
     * @param string2
     * @return String
     * Subtraction is basically the addition of the first integer plus the second additive inverse of the second integer
     * so this function directly uses the Addition function as a helper by passing the second argument as the additive inverse of the argument provided
     */
    public static String Subtraction (String string1, String string2)
    {
        if(string2.charAt(0)=='-') string2 = string2.substring(1);
        else string2 = '-' + string2;

        String Answer = Addition(string1, string2);


        return Answer;
    }

    
    /**
     * @param string1
     * @param string2
     * @return String
     * This function gives the product of two strings
     * This uses the fact that Multiplication is repeated addition
     */
    public static String Multiplication (String string1,String string2)
    {   
        // This code performs the multiplication for both absolute values of the strings initially and the prepends the appropriate sign needed
        String absolutestring1 = absolute_string(remove_leading_zeroes(string1));
        
        String absolutestring2 = absolute_string(remove_leading_zeroes(string2));
        
        String Answer = "0";
        
        int l1 = absolutestring1.length();
        
        int l2 = absolutestring2.length();
        
        if(absolutestring1.equals("0")||absolutestring2.equals("0"))
        {
            return "0";
        }
        for(int i=l2-1;i>=0;i--)
        {   
            /*This loop is used to iterate over the elements of the second string 
            The logic is to get the value of the string1 multiplied by a single digit from the string2
            which is later added cumulatively using Addition function with appropriate zeroes padded to the product obtained for each index   
            */
            String string_multiply = ""; 

            int carry = 0;
            
            for(int j=l1-1;j>=0;j--)
            {
                /*This loop is used to perfrom digit by digit multiplication and storing it to get the product
                   of each digit with the entire string 
                 */
                int product_of_digits = (absolutestring1.charAt(j)-'0')*(absolutestring2.charAt(i)-'0') + carry;

                carry = product_of_digits/10;

                string_multiply = Integer.toString(product_of_digits - 10*carry).concat(string_multiply);
            }
            
            if(carry!=0)    string_multiply= Integer.toString(carry).concat(string_multiply);// leftover carry
            
            for(int j=0;j<l2-1-i;j++)
            {
                string_multiply = string_multiply.concat("0"); //padding zeroes so that the digit with which the string is multiplied by gets the number of digits based on the place of the digit in the second string
            }
           
            Answer = Addition(Answer,string_multiply);
            //This cumulatively adds the result to the product

        }
        // This prepends the appropriate sign.
        if(string1.charAt(0)=='-' || string2.charAt(0)=='-')
        {
            if(string1.charAt(0)=='-' && string2.charAt(0)=='-')
            {
                return Answer;
            }
            Answer = '-' + Answer;
            
            return Answer;
        }
        return Answer;
    }
    
    /**
     * @param string1
     * @param string2
     * @return String
     * This function returns the quotient after performing the integer division of string1 and string2
     * This uses the fact that Division is repeated subtraction
     */
    public static String Division(String string1,String string2)
    {   
        // This code performs the Division for both absolute values of the strings initially and the prepends the appropriate sign needed
        String absolutestring1 = absolute_string(remove_leading_zeroes(string1));
        
        String absolutestring2 = absolute_string(remove_leading_zeroes(string2));

        // Exception to avoid indefinite behaviour when divided by zero
        if(absolutestring2.equals("0"))
        {
            throw  new ArithmeticException("Division by zero is not defined");
        }
        if( islesser(absolutestring1, absolutestring2))  
        {
            return "0";
        }

        String string_quotient = "";
        
        String string_dividend = "";
        
        for(int i=0;i<absolutestring1.length();i++)
        {   
            //This loop takes each character at a time and creates a temporary dividend
            string_dividend = string_dividend.concat(Character.toString(absolutestring1.charAt(i)));
            
            int midway_quotient=0;

            while( isgreater_or_equals(string_dividend, absolutestring2) )
            {   
                //This subtracts the divisor from the temporary dividend till the temporary dividend is less than the divisor
                string_dividend = Subtraction(string_dividend, absolutestring2);
                midway_quotient++;

            }

            string_quotient = string_quotient.concat(Integer.toString(midway_quotient));

        }

        string_quotient = remove_leading_zeroes(string_quotient);
        // This prepends the appropriate sign
        if(string1.charAt(0)=='-' || string2.charAt(0)=='-')
        {
            if(string1.charAt(0)=='-' && string2.charAt(0)=='-')
            {
                return string_quotient;
            }

            string_quotient = "-" + string_quotient;
            
            return string_quotient;
        }
        
        return string_quotient;
            
    }
    /*
        Added the methods which passes the String to the helper functions from the object provided
        Then it parses the String into object and return the object
    */
    public static AInteger Add(AInteger integer1, AInteger integer2)
    {
        return parse(Addition(integer1.integer,integer2.integer));
    }

    public static AInteger Subtract(AInteger integer1, AInteger integer2)
    {
        return parse(Subtraction(integer1.integer,integer2.integer));
    }

    public static AInteger Multiply(AInteger integer1,AInteger integer2)
    {
        return parse(Multiplication(integer1.integer, integer2.integer));
    }

    public static AInteger Divide(AInteger integer1,AInteger integer2)
    {
        return parse(Division(integer1.integer,integer2.integer));
    }
}

