package sw.java.elk.po;

import java.util.List;
import java.util.Set;

public class Role {
    private int id;
    private String roleName;
    private List<Permission> pers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPers() {
        return pers;
    }

    public void setPers(List<Permission> pers) {
        this.pers = pers;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", pers=" + pers +
                '}';
    }
}
