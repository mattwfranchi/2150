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

            // get and process customer name
            String name = view.getName();

            // get and process income to ensure validity
            double income = view.getYearlyIncome();
            if (!(income > m.MININCOME)) {
                // print error message
                view.printToUser("Income must be greater than " + m.MININCOME);
                // return from function, as mortgage cannot be calculated with invalid args
                return;
            }

            // get and process monthly debt to ensure validity
            double monthlyDebt = view.getMonthlyDebt();
            if (!(monthlyDebt >= m.MINDEBT)) {
                view.printToUser("Debt must be greater than or equal to " + m.MINDEBT);
                return;
            }

            // get and process credit score to ensure validity
            int creditScore = view.getCreditScore();
            if (!(m.MINCREDIT < creditScore && creditScore < m.MAXCREDIT)) {
                view.printToUser("Credit score must be greater than " + m.MINCREDIT + " and less than " + m.MAXCREDIT);
                return;
            }

            // at this point, we have all the data needed to initialize the customer member variable
            c = new Customer(monthlyDebt, income, creditScore, name);

            // get and process house cost to ensure validity
            double houseCost = view.getHouseCost();
            if (!(houseCost > m.MINHOUSECOST)) {
                view.printToUser("Cost must be greater than " + m.MINHOUSECOST);
                return;

            }

            // get and process down payment to ensure validity
            double downPayment = view.getDownPayment();
            if (!(downPayment > m.MINDOWNPAYMENT && downPayment < houseCost)) {
                view.printToUser("Down payment must be greater than " + m.MINDOWNPAYMENT +
                        " and less than the cost of the house");
                return;
            }

            // get and process number of years to ensure validity
            int numYears = view.getYears();
            if (!(m.MINNUMYEARS < numYears)) {
                view.printToUser("Years must be greater than " + m.MINNUMYEARS);
                return;
            }

                // empty the top bar of the GUI so no error messages are left
                view.printToUser("");

                // at this point, we have all the data needed ot initialize the mortgage member variable
                m = new Mortgage(houseCost, downPayment, numYears, c);

                // display whether or not the loan was approved in the appropriate spot
                view.displayApproved(m.loanApproved());
                // display the loan payment in the appropriate spot
                view.displayPayment(m.getPayment());
                // display the loan rate in the appropriate spot
                view.displayRate(m.getRate());
            }




}



