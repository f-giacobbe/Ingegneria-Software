package techSupport;

public class Bot extends Handler {
    @Override
    public boolean handle(Ticket ticket) {
        if (ticket.complexity().equals(Ticket.Complexity.LEVEL1)) {
            System.out.format("Bot automatico sta risolvendo il ticket %d%n", ticket.ID());
            return true;
        }

        return super.handle(ticket);
    }
}
