
import arbitraryarithmetic.AFloat;
import arbitraryarithmetic.AInteger;

public class MyInfArith {
    public static void main (String []args) 
    {   
        // Assigning variables to the arguements
        String first=args[0];
        String second=args[1];
        String third=args[2];
        String fourth=args[3];
        
        if(first.equals("int"))
        {   
            //integer operation
            
            //creating new objects using the parametered constructor AInteger(String)
            AInteger integer1=new AInteger(third);
            AInteger integer2=new AInteger(fourth);

            //passing the created objects to the required function using switch cases
            switch (second)
            {
                case "add": 
                    System.out.println(AInteger.Add(integer1,integer2).integer);
                break;

                case "sub":
                    System.out.println(AInteger.Subtract(integer1, integer2).integer);
                break;

                case "mul":
                    System.out.println(AInteger.Multiply(integer1, integer2).integer);
                break;

                // customizing the out of the exception for Division by zero using try catch block
                case "div":
                try{
                    System.out.println(AInteger.Divide(integer1, integer2).integer);
                    break;
                }
                
                catch(ArithmeticException e)
                {
                    System.out.println("Division by zero error");
                }
  
            }
        }
        else
        {   
            //float operation

            //creating new objects using the parametered constructor AInteger(String)
            AFloat number1=new AFloat(third);
            AFloat number2=new AFloat(fourth);

            //passing the created objects to the required function using switch cases
            switch (second)
            {
                case "add": 
                    System.out.println(AFloat.Add(number1,number2).number);
                break;

                case "sub":
                    System.out.println(AFloat.Subtract(number1,number2).number);
                break;

                case "mul":
                    System.out.println(AFloat.Multiply(number1,number2).number);
                break;

                // customizing the  the exception for Division by zero using try catch block
                case "div":
                try{
                    System.out.println(AFloat.Divide(number1,number2).number);
                }
                catch(ArithmeticException e)
                {
                    System.out.println("Division by zero error");
                }
                break;  
            }
        }
        

    }
}
