package is.state.forno;

public class LuceBool implements  LuceIF{
    private boolean accesa=false;
    @Override
    public void switchOn() {
        if(!accesa){
            accesa= true;
            System.out.println("Luce on");
        }
    }

    @Override
    public void switchOff() {
        if(accesa){
            accesa=false;
            System.out.println("Luce off");

        }
    }
}
