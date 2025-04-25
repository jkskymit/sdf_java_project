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
}
