package arbitraryarithmetic;

public class AFloat {
    
    public String number;

    public AFloat()
    {   
        //Default constructor initializing number to "0.0"

        this.number = "0.0";
    }

    public AFloat(String string)
    {   
        // A parametric constructor to iniatialize a string when a String is provided as an argument

        this.number = string;
    }

    public AFloat(AFloat other_constructor)
    {   
        // Copy constructor that initiliases the value of this object to the value of the other object.

        this.number = other_constructor.number;
    }

    public static AFloat parse(String string)
    {   
        // Function to create an instance of AFloat from a string.

        return new AFloat(string);
    }

    /**
     * @param string
     * @return String
     * This function removes the leading zeroes while preserving the sign
     */
    public static String  remove_leading_zeroes(String string)
    {   
        if(!isdecimal(string))
        {   
            // if its integer directly use the AInteger.remove_leading_zeroes function

            return AInteger.remove_leading_zeroes(string);
        }
        
        int i=0;

        while(string.charAt(i)=='0'|| string.charAt(i)=='-')
        {
            i++;
        }

        //The following block is executed when there is no non zero element before the decimal point
        if(string.charAt(i)=='.')
        {   
            // This ensures the sign is preserved and removes all the leading zeroes just before the character from the decimal point 
            if(string.charAt(0)=='-')
            {   
                for(int j=i+1;j<string.length();j++)
                {
                    if(string.charAt(j)!='0') return '-' + string.substring(i);
                }
                
                return string.substring(i-1);

            }
            else return string.substring(i-1);
        }
        // It preserves the sign and removes all the elements before this index
        else 
        {
            if(string.charAt(0)=='-') return '-' + string.substring(i);
            
            else return string.substring(i);
        }
    }


    /**
     * @param string
     * @return Boolean
     * This checks if the given string has a decimal point or not
     */
    public static Boolean isdecimal(String string)
    {
        for(int i=0;i<string.length();i++)
        {
            if(string.charAt(i)=='.') return true;
        }
        
        return false;
    }


    /**
     * @param string
     * @return String 
     * This removes all the trailing zeroes after the decimal point and returns the string
     */
    public static String remove_trailing_zeroes(String string)
    {
        if(isdecimal(string))
        {   
            // index gives the last non zero element
            int i=string.length()-1;
            
            while(string.charAt(i)=='0')
            {
                i--;
            }
            
            // preserving the decimal nature when the last non zero element is decimal point

            if(string.charAt(i)=='.')
            {
                return string.substring(0,i+2);
            }
            
            else return string.substring(0,i+1);
        }
        else return string; // for integers
    }
    
    
    /**
     * @param string
     * @return String
     * removes both leading and trailing zeroes 
     */
    public static String remove_both_leading_and_trailing_zeroes(String string)
    {
        return remove_trailing_zeroes(remove_leading_zeroes(string));
    }

   /**
     * @param string
     * @return int
     * This function counts the number of elements present after the decimal place
     */
     public static int decimal_places(String string)
    {
        if(isdecimal(string))
        {
            int i = string.length()-1;
            
            while(string.charAt(i)!='.') i--;

            return string.length() - i -1;

        }
        return 0; //if its an integer
    }

    /**
     * @param string
     * @return int
     * This returns the index of the decimal point in the given string
     */
    public static int decimal_index(String string)
    {
        return string.length() - decimal_places(string) - 1;
    }
    

    /**
     * @param string
     * @return String
     * This ensures that the no of decimal places is less than 30
     */
    public static String Truncate(String string)
    {

        if(decimal_places(string)<= 30)
        {
            return string;
        }
        else
        {
            return string.substring(0,decimal_index(string)+31); //Truncate to 30 decimals
        }
    }


    /**
     * @param string1
     * @param string2
     * @return String
     * This function returns the sum of the two given strings
     * It uses the Addition function from AInteger to class to compute the sum without the decimal point.
     * The above mentioned process is done by padding zeroes to ensure their corresponding decimal points are in place 
     * Then the decimal point is placed in the Answer and we get the sum of two decimals
     */
    public static String Addition(String string1,String string2)
    {
        if(!(isdecimal(string2) || isdecimal(string1))) return AInteger.Addition(string1, string2);// for Integers this uses the AInteger class

        if(!isdecimal(string1)) string1 = string1 + ".0";
        
        if(!isdecimal(string2)) string2 = string2 + ".0"; // if its not an integer this just appends a .0 to make one

        string1 = remove_both_leading_and_trailing_zeroes(string1);
        
        string2 = remove_both_leading_and_trailing_zeroes(string2);

        int max_dec = Math.max(decimal_places(string1),decimal_places(string2)); // max is used in placing the decimal point once it computes the sum

        // padding trailing zeroes to ensure both have same decimal places
        if(decimal_places(string1) > decimal_places(string2))
        {
            for(int i=1;i<=(max_dec-decimal_places(string2));i++)
            {
                string2 = string2 + '0';
            }
        }
        else
        {
            for(int i=1;i<=(max_dec-decimal_places(string1));i++)
            {
                string1 = string1 + '0';
            }
        }

        //The formatted strings are formed by removing the decimal point making them an integer
        String formatted_string1 = string1.substring(0,decimal_index(string1)) + string1.substring(decimal_index(string1)+1);
           
        String formatted_string2 = string2.substring(0,decimal_index(string2)) + string2.substring(decimal_index(string2)+1);

        String Answer = AInteger.Addition(formatted_string1, formatted_string2);// we perform Integer addition

        String absolute_Answer = AInteger.absolute_string(Answer); // we remove the sign to avoid indefinite behaviour

        String fin_Answer;

        //This block places the decimal point int the answer we obtained accordingly
        if(absolute_Answer.length() > max_dec)
        {
            fin_Answer= absolute_Answer.substring(0,absolute_Answer.length() - max_dec) + '.';
           
            fin_Answer = fin_Answer + absolute_Answer.substring(absolute_Answer.length() - max_dec);
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));// Truncating to 30 decimal precision
        }
        // This block prepends some zeroes incase the answer doesnt have as many digits as the decimal places to be placed
        else
        {
            fin_Answer = "0.";
            
            for(int i=0;i<(max_dec-absolute_Answer.length());i++)
            {
                fin_Answer = fin_Answer+'0'; //padding zeroes
            }
           
            fin_Answer = fin_Answer + absolute_Answer;
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));//Truncating to 30 decimal precision
        }
        // preserving the sign
        if(Answer.charAt(0)!='-') return fin_Answer;
       
        else return '-' + fin_Answer;
    }

    /**
     * @param string1
     * @param string2
     * @return String
     * This use the fact that 
     * Subtraction is basically the addition of the first number plus the additive inverse of the second integer
     */
    public static String Subtraction(String string1,String string2)
    {
        if(string2.charAt(0)=='-')
        {
            string2 = string2.substring(1);
        }
        else 
        {
            string2 = '-' + string2;
        }

        String Answer = Addition(string1, string2);// used thr Addition function with a change in sign of the second string
        
        return Answer;
    }

    /**
     * @param string1
     * @param string2
     * @return String
     * This function uses the Multiplication function from the AInteger class
     * This converts the decimals into integers after ensuring that same number of decimal places are present
     * Then it places The decimal point appropriately as in the sum of the decimal places 
     */
    public static String Multiplication(String string1,String string2)
    {
        if(!(isdecimal(string2) || isdecimal(string1))) return AInteger.Multiplication(string1, string2);

        if(!isdecimal(string1)) string1 = string1 + ".0";
        
        if(!isdecimal(string2)) string2 = string2 + ".0"; // padding .0 to convert integer into float

        string1 = remove_both_leading_and_trailing_zeroes(string1);
        
        string2 = remove_both_leading_and_trailing_zeroes(string2); 

        int max_dec = Math.max(decimal_places(string1),decimal_places(string2));
        
        int min_dec = Math.min(decimal_places(string1),decimal_places(string2));

        if(decimal_places(string1) > decimal_places(string2))
        {
            for(int i=1;i<=(max_dec-decimal_places(string2));i++)
            {
                string2 = string2 + '0';
            }
        }
        else
        {
            for(int i=1;i<=(max_dec-decimal_places(string1));i++)
            {
                string1 = string1 + '0';
            }
        }

        // converting into absolute to avoid indefinite behaviour with sign
        String formatted_string1 = AInteger.absolute_string(string1.substring(0,decimal_index(string1)) + string1.substring(decimal_index(string1)+1));
           
        String formatted_string2 = AInteger.absolute_string(string2.substring(0,decimal_index(string2)) + string2.substring(decimal_index(string2)+1));

        String absolute_Answer = AInteger.Multiplication(formatted_string1, formatted_string2);

        String fin_Answer;

        if(absolute_Answer.length() > max_dec+min_dec)
        {   // we place the decimal point This block handles the case if the actual absolute value of multiplication is greater than 1
            fin_Answer = absolute_Answer.substring(0,absolute_Answer.length() - (max_dec+min_dec)) + '.';
           
            fin_Answer = fin_Answer + absolute_Answer.substring(absolute_Answer.length() - (max_dec + min_dec));
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));//30 decimal precision
        }
        else
        {   
            // This block helps us to place the point when the actual absolute value of multiplication is less than 1
            fin_Answer = "0.";
            
            for(int i=0;i<(max_dec+min_dec-absolute_Answer.length());i++)
            {
                fin_Answer = fin_Answer+'0';
            }
            
            fin_Answer = fin_Answer + absolute_Answer;
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));//30 decimal precision
        }
        if((string1.charAt(0)!='-' && string2.charAt(0)!='-' )||(string1.charAt(0)=='-' && string2.charAt(0)=='-')) return fin_Answer;
        
        else return '-' + fin_Answer;
    }

    public static String Division(String string1,String string2)
    {
        if(!isdecimal(string1)) string1 = string1 + ".0";
        
        if(!isdecimal(string2)) string2 = string2 + ".0";

        string1 = remove_both_leading_and_trailing_zeroes(string1);
        
        string2 = remove_both_leading_and_trailing_zeroes(string2);

        int dec1 = decimal_places(string1);
        
        int dec2 = decimal_places(string2);

        
        String formatted_string1 = AInteger.absolute_string(string1.substring(0,decimal_index(string1)) + string1.substring(decimal_index(string1)+1));
           
        String formatted_string2 = AInteger.absolute_string(string2.substring(0,decimal_index(string2)) + string2.substring(decimal_index(string2)+1));

        int max = Math.max(30-dec2 + dec1 ,formatted_string2.length());

        for(int i=1;i<= (max);i++)
        {
            formatted_string1 = formatted_string1 + '0';
        }

        String absolute_Answer = AInteger.Division(formatted_string1, formatted_string2);

        String fin_Answer;

        if(absolute_Answer.length() > max)
        {
            fin_Answer = absolute_Answer.substring(0,absolute_Answer.length() - (dec1 - dec2 + max)) + '.';
           
            fin_Answer = fin_Answer + absolute_Answer.substring(absolute_Answer.length() - (dec1 - dec2+ max));
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
        }
        else
        {   
            //This block appends the required zeroes to ensure that the  decimal places are preserved

            if(max + dec1 - dec2 - absolute_Answer.length() < 0)
            {
                fin_Answer = absolute_Answer;

                for(int i=0;i< absolute_Answer.length() + dec2 - dec1 - max;i++)
                {
                    fin_Answer = fin_Answer + "0";
                }

                fin_Answer = fin_Answer + ".0";
            }
            else
            {
                fin_Answer = "0.";
            
                for(int i=0; i< max + dec1 - dec2 - absolute_Answer.length();i++)
                {
                    fin_Answer = fin_Answer+'0';
                }
            
                fin_Answer = fin_Answer.concat(absolute_Answer);
            
                fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
            
                //This ensures that there is 30 decimal precision
            }
        }

        if((string1.charAt(0)!='-' && string2.charAt(0)!='-' )||(string1.charAt(0)=='-' && string2.charAt(0)=='-')) return fin_Answer;
        
        else return '-' + fin_Answer;
    }

    public static AFloat Add(AFloat number1, AFloat number2)
    {
        return parse(Addition(number1.number,number2.number));
    }

    public static AFloat Subtract(AFloat number1, AFloat number2)
    {
        return parse(Subtraction(number1.number,number2.number));
    }

    public static AFloat Multiply(AFloat number1,AFloat number2)
    {
        return parse(Multiplication(number1.number, number2.number));
    }

    public static AFloat Divide(AFloat number1,AFloat number2)
    {
        return parse(Division(number1.number,number2.number));
    }

}
