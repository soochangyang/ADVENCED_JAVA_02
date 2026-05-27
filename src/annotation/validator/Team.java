package annotation.validator;

public class Team {
    @NotEmpty(message="put your team name")
    private String name;
    @Range(min=1, max=999, message="Team members must between 1 and 999!!!! ")
    private int memberCount;

    public Team(String name, int memberCount) {
        this.name = name;
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
