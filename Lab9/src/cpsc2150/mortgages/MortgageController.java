package cpsc2150.mortgages;

public class MortgageController implements IMortgageController {
    IMortgageView view;
    // dont initialize mortgage or customer yet, need to get parameters first
    // all bounds for error-checking are stored in IMortgage
    IMortgage m;
    ICustomer c;

    public MortgageController(IMortgageView v)
    {
        // initialize the view
        view = v;
    }

    public void submitApplication() {
        // initialize both boolean flags to true, so that while loops while be engaged
        boolean mortgage_again = true, new_customer = true;

        // outer loop for customer processing
        while(new_customer) {
            // get name, assume valid
            String name = view.getName();

            // get and process income to ensure validity
            double income = view.getYearlyIncome();
            while (!(income > m.MININCOME)) {
                view.printToUser("Income must be greater than " + m.MININCOME);
                income = view.getYearlyIncome();
            }

            // get and process monthly debt to ensure validity
            double monthlyDebt = view.getMonthlyDebt();
            while (!(monthlyDebt >= m.MINDEBT)) {
                view.printToUser("Debt must be greater than or equal to " + m.MINDEBT);
                monthlyDebt = view.getMonthlyDebt();
            }

            // get and process credit score to ensure validity
            int creditScore = view.getCreditScore();
            while (!(m.MINCREDIT < creditScore && creditScore < m.MAXCREDIT)) {
                view.printToUser("Credit score must be greater than " + m.MINCREDIT + " and less than " + m.MAXCREDIT);
                creditScore = view.getCreditScore();
            }

            // at this point, we have all the data needed to initialize the customer member variable
            c = new Customer(monthlyDebt, income, creditScore, name);

            // inner loop for mortgage processing
            while (mortgage_again) {
                // get and process house cost to ensure validity
                double houseCost = view.getHouseCost();
                while (!(houseCost > m.MINHOUSECOST)) {
                    view.printToUser("Cost must be greater than " + m.MINHOUSECOST);
                    houseCost = view.getHouseCost();
                }

                // get and process down payment to ensure validity
                double downPayment = view.getDownPayment();
                while (!(downPayment > m.MINDOWNPAYMENT && downPayment < houseCost)) {
                    view.printToUser("Down payment must be greater than " + m.MINDOWNPAYMENT +
                            " and less than the cost of the house");
                    downPayment = view.getDownPayment();
                }

                // get and process number of years to ensure validity
                int numYears = view.getYears();
                while (!(m.MINNUMYEARS < numYears)) {
                    view.printToUser("Years must be greater than " + m.MINNUMYEARS);
                    numYears = view.getYears();
                }


                // at this point, we have all the data needed ot initialize the mortgage member variable
                m = new Mortgage(houseCost, downPayment, numYears, c);

                // print the created mortgage and customer to the string, using toString from abstract classes
                view.printToUser(c.toString());
                view.printToUser(m.toString());

                // update boolean flag for y/n enter another mortgage
                mortgage_again = view.getAnotherMortgage();

            }

            // only check for desire to enter new customer if new mortgage flag is false
            if (!mortgage_again) {
                new_customer = view.getAnotherCustomer();
                // if new customer flag is true, then we need to set mortgage_again to true as well
                // this way both loops will be engaged again
                if(new_customer){ mortgage_again = true; }
            }
        }
    }
}



