package model;

import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private Role role;

    private User(UserBuilder userBuilder) {
        id = userBuilder.id;
        email = userBuilder.email;
        password = userBuilder.password;
        role = userBuilder.role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }

    public static class UserBuilder {
        private int id;
        private String email;
        private String password;
        private Role role;

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = Role.valueOf(role);
            return this;
        }

        public User build(){
           return new User(this);
        }
    }


}
