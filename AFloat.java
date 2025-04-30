package arbitraryarithmetic;

public class AFloat {
   
    public String number;

    public AFloat()
    {
        this.number = "0.0";
    }

    public AFloat(String string)
    {
        this.number = string;
    }

    public AFloat(AFloat other_constructor)
    {
        this.number = other_constructor.number;
    }

    public static AFloat parse(String string)
    {
        return new AFloat(string);
    }

    public static String  remove_leading_zeroes(String string)
    {   
        if(!isdecimal(string))
        {
            return AInteger.remove_leading_zeroes(string);
        }
        
        int i=0;

        while(string.charAt(i)=='0'|| string.charAt(i)=='-')
        {
            i++;
        }

        if(string.charAt(i)=='.')
        {
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
        else 
        {
            if(string.charAt(0)=='-') return '-' + string.substring(i);
            
            else return string.substring(i);
        }
    }


    public static Boolean isdecimal(String string)
    {
        for(int i=0;i<string.length();i++)
        {
            if(string.charAt(i)=='.') return true;
        }
        
        return false;
    }


    public static String remove_trailing_zeroes(String string)
    {
        if(isdecimal(string))
        {
            int i=string.length()-1;
            
            while(string.charAt(i)=='0')
            {
                i--;
            }
            
            if(string.charAt(i)=='.')
            {
                return string.substring(0,i+2);
            }
            
            else return string.substring(0,i+1);
        }
        else return string;
    }
    
    
    public static String remove_both_leading_and_trailing_zeroes(String string)
    {
        return remove_trailing_zeroes(remove_leading_zeroes(string));
    }

    public static int decimal_places(String string)
    {
        if(isdecimal(string))
        {
            int i = string.length()-1;
            
            while(string.charAt(i)!='.') i--;

            return string.length() - i -1;

        }
        return 0;
    }

    public static int decimal_index(String string)
    {
        return string.length() - decimal_places(string) - 1;
    }
    

    public static String Truncate(String string)
    {

        if(decimal_places(string)<= 30)
        {
            return string;
        }
        else
        {
            return string.substring(0,decimal_index(string)+31);
        }
    } 

        public static String Addition(String string1,String string2)
    {
        if(!(isdecimal(string2) || isdecimal(string1))) return AInteger.Addition(string1, string2);

        if(!isdecimal(string1)) string1 = string1 + ".0";
        
        if(!isdecimal(string2)) string2 = string2 + ".0";

        string1 = remove_both_leading_and_trailing_zeroes(string1);
        
        string2 = remove_both_leading_and_trailing_zeroes(string2);

        int max_dec = Math.max(decimal_places(string1),decimal_places(string2));

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

        String formatted_string1 = string1.substring(0,decimal_index(string1)) + string1.substring(decimal_index(string1)+1);
           
        String formatted_string2 = string2.substring(0,decimal_index(string2)) + string2.substring(decimal_index(string2)+1);

        String Answer = AInteger.Addition(formatted_string1, formatted_string2);

        String absolute_Answer = AInteger.absolute_string(Answer);

        String fin_Answer;

        if(absolute_Answer.length() > max_dec)
        {
            fin_Answer= absolute_Answer.substring(0,absolute_Answer.length() - max_dec) + '.';
           
            fin_Answer = fin_Answer + absolute_Answer.substring(absolute_Answer.length() - max_dec);
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
        }
        else
        {
            fin_Answer = "0.";
            
            for(int i=0;i<(max_dec-absolute_Answer.length());i++)
            {
                fin_Answer = fin_Answer+'0';
            }
           
            fin_Answer = fin_Answer + absolute_Answer;
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
        }
        if(Answer.charAt(0)!='-') return fin_Answer;
       
        else return '-' + fin_Answer;
    }

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

        String Answer = Addition(string1, string2);
        
        return Answer;
    }

    public static String Multiplication(String string1,String string2)
    {
        if(!(isdecimal(string2) || isdecimal(string1))) return AInteger.Multiplication(string1, string2);

        if(!isdecimal(string1)) string1 = string1 + ".0";
        
        if(!isdecimal(string2)) string2 = string2 + ".0";

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

        String formatted_string1 = AInteger.absolute_string(string1.substring(0,decimal_index(string1)) + string1.substring(decimal_index(string1)+1));
           
        String formatted_string2 = AInteger.absolute_string(string2.substring(0,decimal_index(string2)) + string2.substring(decimal_index(string2)+1));

        String absolute_Answer = AInteger.Multiplication(formatted_string1, formatted_string2);

        String fin_Answer;

        if(absolute_Answer.length() > max_dec+min_dec)
        {
            fin_Answer = absolute_Answer.substring(0,absolute_Answer.length() - (max_dec+min_dec)) + '.';
           
            fin_Answer = fin_Answer + absolute_Answer.substring(absolute_Answer.length() - (max_dec + min_dec));
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
        }
        else
        {
            fin_Answer = "0.";
            
            for(int i=0;i<(max_dec+min_dec-absolute_Answer.length());i++)
            {
                fin_Answer = fin_Answer+'0';
            }
            
            fin_Answer = fin_Answer + absolute_Answer;
            
            fin_Answer = Truncate(remove_both_leading_and_trailing_zeroes((fin_Answer)));
        }
        if((string1.charAt(0)!='-' && string2.charAt(0)!='-' )||(string1.charAt(0)=='-' && string2.charAt(0)=='-')) return fin_Answer;
        
        else return '-' + fin_Answer;
    }
}
