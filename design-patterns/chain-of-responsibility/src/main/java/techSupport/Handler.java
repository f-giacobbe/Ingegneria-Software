package techSupport;

public abstract class Handler {
    private Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(Ticket ticket) {
        if (next != null) {
            return next.handle(ticket);
        }

        return false;
    }
}
