package techSupport;

public class Test {
    public static void main(String[] args) {
        Handler bot = new Bot();
        bot.setNext(new Operatore()).setNext(new Tecnico()).setNext(new TeamSviluppo());

        Ticket t1 = new Ticket(1, "Reset password", Ticket.Complexity.LEVEL1);
        Ticket t2 = new Ticket(2, "Errore comune", Ticket.Complexity.LEVEL2);
        Ticket t3 = new Ticket(3, "Guasto", Ticket.Complexity.LEVEL3);
        Ticket t4 = new Ticket(4, "Bug critico", Ticket.Complexity.LEVEL4);

        bot.handle(t1);
        bot.handle(t2);
        bot.handle(t3);
        bot.handle(t4);
    }
}
