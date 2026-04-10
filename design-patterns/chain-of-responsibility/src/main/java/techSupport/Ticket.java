package techSupport;

public record Ticket(int ID, String description, Ticket.Complexity complexity) {
    public enum Complexity {LEVEL1, LEVEL2, LEVEL3, LEVEL4}
}
