import java.io.Serializable;

public record ScoreboardEntry(String name, double time) implements Serializable {}
