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
        int l1 = string1.length();
        
        int l2 = string2.length();
        
        int carry = 0;
        
        String string_sum = "";
        
        for(int i=l1-1;i>=0;i--)
        {
            if((l1-i) <= l2)
            {
                int sum_of_digits = (string1.charAt(i)-'0')+(string2.charAt(l2-(l1-i))-'0')+carry;
                
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
        int l1 = string1.length();
        
        int l2 = string2.length();
        
        int borrow = 0;
        
        String string_difference = "";
        
        for(int i=l1-1;i>=0;i--)
        {
            if(l1-i <= l2)
            {
                int difference_of_digits;
                
                if((string1.charAt(i) - '0') >= (string2.charAt(l2-(l1-i))-'0')+ borrow)
                {
                    
                    difference_of_digits = (string1.charAt(i)-string2.charAt(l2-(l1-i)))-borrow;
                    
                    borrow = 0;
                    
                    string_difference = Integer.toString(difference_of_digits).concat(string_difference);
                }
                else
                {
                    difference_of_digits = (10-borrow) +(string1.charAt(i)-string2.charAt(l2-(l1-i)));
                    
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
        
        int l3 = string_difference.length();
        
        for(int i=0;i<l3;i++)
        {
            if(string_difference.charAt(i)!='0')
            {
                formatted_string_difference = string_difference.substring(i);
                
                return formatted_string_difference;
            }
        }
        return "0";
    }
}
