package ru.aniskov.petproject.pojo.model;

public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static Role getRole(String value){
        for(Role role: Role.values()){
            if(role.getValue().equals(value)){
                return role;
            }
        }

        return null;
    }

    public static boolean isRolePresent(String searchingRole){
        Role result = getRole(searchingRole);
        return result != null;
    }
}
