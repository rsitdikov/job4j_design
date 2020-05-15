package generic;

public class Role extends Base {
    private String role;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getId() {
        return super.getId();
    }

    public String getRole() {
        return role;
    }
}
