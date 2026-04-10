package techSupport;

public class Operatore extends Handler {
    @Override
    public boolean handle(Ticket ticket) {
        if (ticket.complexity().equals(Ticket.Complexity.LEVEL2)) {
            System.out.format("Operatore sta risolvendo il ticket %d%n", ticket.ID());
            return true;
        }

        return super.handle(ticket);
    }
}
