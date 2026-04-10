package techSupport;

public class Tecnico extends Handler {
    @Override
    public boolean handle(Ticket ticket) {
        if (ticket.complexity().equals(Ticket.Complexity.LEVEL3)) {
            System.out.format("Tecnico sta risolvendo il ticket %d%n", ticket.ID());
            return true;
        }

        return super.handle(ticket);
    }
}
