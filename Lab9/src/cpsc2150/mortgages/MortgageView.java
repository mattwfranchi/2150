package cpsc2150.mortgages;

import java.util.Scanner;

public class MortgageView implements IMortgageView {
    Scanner input_handle;
    IMortgageController controller;

    MortgageView()
    {
        // initialize the scanner in the constructor
        input_handle = new Scanner(System.in);
    }

    public void setController(IMortgageController c)
    {
        controller = c;
    }

    public double getHouseCost()
    {
        System.out.println("How much does the house cost?");
        return input_handle.nextDouble();
    }

    public double getDownPayment()
    {
        System.out.println("How much is the down payment?");
        return input_handle.nextDouble();
    }

    public int getYears()
    {
        System.out.println("How many years?");
        return input_handle.nextInt();
    }

    public double getMonthlyDebt()
    {
        System.out.println("How much are your monthly debt payments?");
        return input_handle.nextDouble();
    }

    public double getYearlyIncome()
    {
        System.out.println("How much is your yearly income?");
        return input_handle.nextDouble();
    }

    public int getCreditScore()
    {
        System.out.println("What is your credit score?");
        return input_handle.nextInt();
    }

    public String getName()
    {
        System.out.println("What's your name?");
        return input_handle.nextLine();
    }

    public void printToUser(String s)
    {
        System.out.println(s);
    }

    public void displayPayment(double p)
    {
        System.out.print("Payment: "+p);
    }

    public void displayRate(double r)
    {
        System.out.print("Rate: "+r);
    }

    public void displayApproved(boolean a)
    {
        // approved
        if(a)
        {
            System.out.println("Mortgage application was approved.");
        }
        // not approved
        else
        {
            System.out.println("Mortgage application was not approved.");
        }
    }

    public boolean getAnotherMortgage()
    {
        System.out.print("Would you like to apply for another mortgage [Y/N]? ");
        // read in user input as string, convert to uppercase
        String input =  input_handle.next().toUpperCase();
        while(input.charAt(0) != 'Y' && input.charAt(0) != 'N')
        {
            System.out.print("Error... Please enter Y or N: ");
            input =  input_handle.next().toUpperCase();

        }
        // reads and discards lingering line break
        input_handle.nextLine();
        return input.charAt(0) == 'Y';
    }

    public boolean getAnotherCustomer()
    {
        System.out.print("Would you like to enter another customer [Y/N]? ");
        // read in user input as string, convert to uppercase
        String input =  input_handle.next().toUpperCase();

        while(input.charAt(0) != 'Y' && input.charAt(0) != 'N')
        {
            System.out.print("Error... Please enter Y or N: ");
            input =  input_handle.next().toUpperCase();

        }
        // reads and discards lingering line break
        input_handle.nextLine();
        return input.charAt(0) == 'Y';
    }
}
