package cpsc2150.banking;


/**
 * Created by mwfranc on 2/25/20.
 */
public class Mortgage extends AbsMortgage  {
    private double payment, rate, debtToIncomeRatio, principal, percentDown;
    private ICustomer customer;
    private int numberOfPayments;


    Mortgage(double houseCost, double downPayment, int numYears, ICustomer C){

        // CUSTOMER ASSIGNMENT
        customer = C;

        // PERCENT DOWN CALCULATION
        percentDown = downPayment / houseCost;

        // NUMBER OF PAYMENTS CALCULATION
        numberOfPayments = numYears * 12;

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

        else if(creditScore >= BADCREDIT && creditScore < FAIRCREDIT){ rate += BADRATEADD; }

        else if(creditScore >= FAIRCREDIT && creditScore < GOODCREDIT){ rate += NORMALRATEADD; }

        else if(creditScore >= GOODCREDIT && creditScore < GREATCREDIT) { rate += GOODRATEADD; }

        else if(creditScore >= GREATCREDIT && creditScore <= MAXCREDIT) { rate += 0; }


        rate /= 12;

        // PRINCIPAL CALCULATION
        principal = houseCost - downPayment;

        // DEBT TO INCOME RATIO CALCULATION
        // CHECK THIS ONE
        debtToIncomeRatio = 12*(customer.getMonthlyDebtPayments()+payment) / customer.getIncome();


        // MONTHLY PAYMENTS
        payment = (rate*principal) / (1-Math.pow((1+rate),-1*numberOfPayments ));


    }



    public boolean loanApproved(){
        return 12*rate < RATETOOHIGH && percentDown >= MIN_PERCENT_DOWN &&
                debtToIncomeRatio < DTOITOOHIGH;
    }

    public double getPayment(){
        return payment;

    }

    public double getRate(){
        return rate*12;

    }

    public double getPrincipal(){
        return principal;

    }

    public int getYears(){
        return numberOfPayments/12;

    }
}
