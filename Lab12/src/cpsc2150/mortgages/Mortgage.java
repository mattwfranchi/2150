package cpsc2150.mortgages;


/**
 * Created by mwfranc on 2/25/20.
 */
public class Mortgage extends AbsMortgage  {
    /**
     * Correspondence payment = Payment
     * Corresondence rate = Rate
     * Correspondence customer = Customer
     * Correspondence debtToIncomeRatio = DebtToIncomeRatio
     * Correspondence principal = Principal
     * Correspondence numberOfPayments = NumberOfPayments
     *
     * @invariant 0 <= rate <= 1
     * @invariant debtToIncomeRatio > 0
     * @invariant MIN_YEARS * 12 <= numberOfPayments <= MAX_YEARS * 12
     * @invariant principal > 0
     * @invariant 0 <= percentDown < 1
     *
     */
    private double payment, rate, debtToIncomeRatio, principal, percentDown;
    private ICustomer customer;
    private int numberOfPayments;


    /**
     * @pre houseCost > 0 AND downPayment > 0 AND downPayment < houseCost AND
     *      numYears >= MIN_YEARS AND numYears <= MAX_YEARS AND C is initialized, usable
     * @param houseCost cost of the house that is being mortgaged
     * @param downPayment amount representing the down payment on the mortgaged house
     * @param numYears number of years that the mortgage will be spread over
     * @param C customer that is engaging the mortgage and applying for a loan
     * @post percentDown is calculated AND numberofPayments is calculated AND
     *       AND rate is calculated based on the BASERATE, numberOfYears, and percentDown AND
     *       principal is calculated and debtToIncomeRatio is calculated AND payment is calculated
     */
    Mortgage(double houseCost, double downPayment, int numYears, ICustomer C){

        // CUSTOMER ASSIGNMENT
        customer = C;

        // PERCENT DOWN CALCULATION
        percentDown = downPayment / houseCost;

        // NUMBER OF PAYMENTS CALCULATION
        numberOfPayments = numYears > MIN_YEARS ?
                            numYears*MONTHSINYEAR : MIN_YEARS*MONTHSINYEAR;

        // RATE CALCULATION
        rate = BASERATE;
        int creditScore = customer.getCreditScore();

        // <30 or >30 Years Rate Adjustment
        if(numYears < MAX_YEARS){ rate += LESSTHAN30RATE; }
        else{ rate += GREATERTHAN30RATE; }

        // Percent Down Rate Adjustment
        if(percentDown < PREFERRED_PERCENT_DOWN) { rate += SMALLPDRATE; }

        // Credit Score Rate Adjustment
        if(creditScore < BADCREDIT){ rate += VERYBADRATEADD; }

        else if( creditScore < FAIRCREDIT){ rate += BADRATEADD; }

        else if( creditScore < GOODCREDIT){ rate += NORMALRATEADD; }

        else if(creditScore < GREATCREDIT) { rate += GOODRATEADD; }

        else if( creditScore <= MAXCREDIT) { rate += 0; }


        rate /= MONTHSINYEAR;

        // PRINCIPAL CALCULATION
        principal = houseCost - downPayment;

        // DEBT TO INCOME RATIO CALCULATION
        // CHECK THIS ONE
        debtToIncomeRatio = MONTHSINYEAR*(customer.getMonthlyDebtPayments()+payment) / customer.getIncome();


        // MONTHLY PAYMENTS
        payment = (rate*principal) / (1-Math.pow((1+rate),-1*numberOfPayments ));


    }



    public boolean loanApproved(){
        return MONTHSINYEAR*rate < RATETOOHIGH && percentDown >= MIN_PERCENT_DOWN &&
                debtToIncomeRatio < DTOITOOHIGH;
    }

    public double getPayment(){
        return payment;

    }

    public double getRate(){
        return rate * MONTHSINYEAR;

    }

    public double getPrincipal(){
        return principal;

    }

    public int getYears(){
        return numberOfPayments/MONTHSINYEAR;

    }
}
