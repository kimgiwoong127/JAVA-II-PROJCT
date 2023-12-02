// LoginDTO.java
package Register;

public class LoginDTO {
    private String id;
    private String password;

    public LoginDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
