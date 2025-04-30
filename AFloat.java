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
}
