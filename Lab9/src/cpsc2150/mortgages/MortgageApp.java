package cpsc2150.mortgages;

public class MortgageApp {
    public static void main(String[] args)
    {
        // initialize the view and controller
        IMortgageView view = new MortgageView();
        IMortgageController controller = new MortgageController(view);

        // link the controller to the view
        view.setController(controller);

        // start the application processing loop
        controller.submitApplication();
    }
}
