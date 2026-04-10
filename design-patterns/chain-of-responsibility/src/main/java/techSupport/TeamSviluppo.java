package techSupport;

public class TeamSviluppo extends Handler {
    @Override
    public boolean handle(Ticket ticket) {
        if (ticket.complexity().equals(Ticket.Complexity.LEVEL4)) {
            System.out.format("Team sviluppo sta risolvendo il ticket %d%n", ticket.ID());
            return true;
        }

        return super.handle(ticket);
    }
}
