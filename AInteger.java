package arbitraryarithmetic;
public class AInteger {
    
    public String integer;

    public AInteger()
    {
        this.integer = "0";
    }

    public AInteger(String string)
    {
        this.integer = string;
    }

    public AInteger(AInteger other_constructor)
    {
        this.integer = other_constructor.integer;
    }

    public static AInteger parse(String string)
    {
        return new AInteger(string);
    }

    public static String add_strings(String string1, String string2)
    {
        int length1 = string1.length();
        
        int length2 = string2.length();
        
        int carry = 0;
        
        String string_sum = "";
        
        for(int i=length1-1;i>=0;i--)
        {
            if((length1-i) <= length2)
            {
                int sum_of_digits = (string1.charAt(i)-'0')+(string2.charAt(length2-(length1-i))-'0')+carry;
                
                carry = sum_of_digits/10;
                
                string_sum = Integer.toString(sum_of_digits-10*carry).concat(string_sum);
            }
            else
            {
                int sum_of_digit = (string1.charAt(i)-'0') + carry;
                
                carry = sum_of_digit/10;
                
                string_sum = Integer.toString(sum_of_digit -10*carry).concat(string_sum);
            }
        }
        if(carry!=0)
        {
            string_sum = Integer.toString(carry).concat(string_sum);
        }
        return string_sum;
    }

    
    public static String subtract_strings(String string1,String string2)
    {
        int length1 = string1.length();
        
        int length2 = string2.length();
        
        int borrow = 0;
        
        String string_difference = "";
        
        for(int i=length1-1;i>=0;i--)
        {
            if(length1-i <= length2)
            {
                int difference_of_digits;
                
                if((string1.charAt(i) - '0') >= (string2.charAt(length2-(length1-i))-'0')+ borrow)
                {
                    
                    difference_of_digits = (string1.charAt(i)-string2.charAt(length2-(length1-i)))-borrow;
                    
                    borrow = 0;
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                }
                else
                {
                    difference_of_digits = (10-borrow) +(string1.charAt(i)-string2.charAt(length2-(length1-i)));
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                    
                    borrow = 1;
                }
            }
            else
            {   
                int difference_of_digits;
                
                if(string1.charAt(i)-'0' >= borrow)
                {
                    difference_of_digits = (string1.charAt(i)-'0') - borrow;
                    
                    borrow = 0;
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                }
                else
                {
                    difference_of_digits = 10-borrow + (string1.charAt(i)-'0');
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                    
                    borrow = 1;
                }
            }
        }
        String formatted_string_difference;
        
        int length_string_difference = string_difference.length();
        
        for(int i=0;i<length_string_difference;i++)
        {
            if(string_difference.charAt(i)!='0')
            {
                formatted_string_difference = string_difference.substring(i);
                
                return formatted_string_difference;
            }
        }
        return "0";
    }

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
    
    public static Boolean isgreater_or_equals(String string1,String string2)
    {
        if(isgreater(string1, string2) || remove_leading_zeroes(string1).equals(remove_leading_zeroes(string2))) return true;
        else return false;
    }

    public static Boolean islesser_or_equals(String string1,String string2)
    {
        if(islesser(string1, string2) || remove_leading_zeroes(string1).equals(remove_leading_zeroes(string2)) ) return true;
        else return false;
    }

    public static String remove_leading_zeroes(String string)
    {
        int l = string.length();
        
        for(int i=0;i<l;i++)
        {
            if(string.charAt(i)!='0' && string.charAt(i)!='-')
            {
                if(string.charAt(0)!='-')  return string.substring(i);
                else return "-" + string.substring(i);
            }
        }
        return "0";
    }

    public static String absolute_string(String string)
    {
        if(string.charAt(0)=='-') return  string.substring(1);
        else return string;
    }

    public static String Addition(String string1,String string2)
    {
        String formatted_string1 = remove_leading_zeroes(string1);
        
        String formatted_string2 = remove_leading_zeroes(string2);
        
        if(formatted_string1.charAt(0)=='-' || formatted_string2.charAt(0)=='-')
        {
            if(formatted_string1.charAt(0)=='-' && formatted_string2.charAt(0)=='-')
            {
                String absolutestring1 = absolute_string(formatted_string1);
                
                String absolutestring2 = absolute_string(formatted_string2);
                
                if(isgreater_or_equals(absolutestring1,absolutestring2))
                {
                    String Answer = add_strings(absolutestring1, absolutestring2);
                    
                    Answer = "-" + Answer;
                    
                    return Answer;
                }
                String Answer = add_strings(absolutestring2,absolutestring1);
                
                Answer = "-" + Answer;
                
                return Answer;
            }

            String absolutestring1;
            
            String absolutestring2;
            
            absolutestring1 = absolute_string(formatted_string1);
            
            absolutestring2 = absolute_string(formatted_string2);
            
            if(isgreater(absolutestring1, absolutestring2))
            {
                String Answer = subtract_strings(absolutestring1, absolutestring2);
                
                if(formatted_string1.charAt(0)=='-') 
                {
                    Answer = '-' + Answer;
                }

                return Answer;
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

        if(isgreater(formatted_string1, formatted_string2))
        {
            String Answer = add_strings(formatted_string1, formatted_string2);
           
            return Answer;
        }
        else 
        {
            String Answer = add_strings(formatted_string2, formatted_string1);
            
            return Answer;
        }
    }


    public static String Subtraction (String string1, String string2)
    {
        if(string2.charAt(0)=='-') string2 = string2.substring(1);
        else string2 = '-' + string2;

        String Answer = Addition(string1, string2);


        return remove_leading_zeroes(Answer);
    }

    
    public static String Multiplication (String string1,String string2)
    {
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
            String string_multiply = "";

            int carry = 0;
            
            for(int j=l1-1;j>=0;j--)
            {
                int product_of_digits = (absolutestring1.charAt(j)-'0')*(absolutestring2.charAt(i)-'0') + carry;

                carry = product_of_digits/10;

                string_multiply = Integer.toString(product_of_digits - 10*carry).concat(string_multiply);
            }
            
            if(carry!=0)    string_multiply= Integer.toString(carry).concat(string_multiply);
            
            for(int j=0;j<l2-1-i;j++)
            {
                string_multiply = string_multiply.concat("0");
            }
           
            Answer = Addition(Answer,string_multiply);

        }
        
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
    
    public static String Division(String string1,String string2)
    {
        String absolutestring1 = absolute_string(remove_leading_zeroes(string1));
        
        String absolutestring2 = absolute_string(remove_leading_zeroes(string2));

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
            string_dividend = string_dividend.concat(Character.toString(absolutestring1.charAt(i)));
            
            int midway_quotient=0;

            while( isgreater_or_equals(string_dividend, absolutestring2) )
            {   
                string_dividend = Subtraction(string_dividend, absolutestring2);
                
                midway_quotient++;

            }

            string_quotient = string_quotient.concat(Integer.toString(midway_quotient));

        }

        string_quotient = remove_leading_zeroes(string_quotient);

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
}

