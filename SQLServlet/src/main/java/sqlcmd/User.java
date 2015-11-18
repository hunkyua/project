//package sqlcmd;
//
//import sqlcmd.enums.UserRoles;
//
//import java.util.*;
//
///**
// * Created by Hunky on 21.09.2015.
// */
//public class User implements Observer {
//    private String name;
//    private String surName;
//    private String email;
//    private Integer id;
//    private List<UserRoles> myRoles = new LinkedList<UserRoles>();
//
//    private User(String name, String surName, String email) {
//        this.name = name;
//        this.surName = surName;
//        this.email = email;
//    }
//
//    private User(String name, String surName, String email, Integer id) {
//        this.name = name;
//        this.surName = surName;
//        this.email = email;
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurName() {
//        return surName;
//    }
//
//    public void setSurName(String surName) {
//        this.surName = surName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void notifyObserver(String s) {
//        System.out.println(s);
//    }
//
//    private boolean hasRole(UserRoles role) {
//        return myRoles.contains(role);
//    }
//
//    public void addRole(UserRoles role) {
//        if (hasRole(role)) {
//            AirBnB.log.info("User: " + this + " already has role - " + role);
//            return;
//        } else {
//            myRoles.add(role);
//            AirBnB.log.info("User: " + this + " now has new role - " + role);
//
//        }
//    }
//
//
//    @Override
//    public String toString() {
//        return String.format("name = '%s', surName = '%s', email = '%s'", name, surName, email);
//    }
//
//    public static Builder createBuilder(){
//        return new Builder();
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//
//    }
//
//    public static class Builder{
//        private Builder() {
//        }
//
//        private static Set<String> emails = new TreeSet<String>();
//        private static List<User> users = new LinkedList<User>();
//
//        public User createUser(String name, String surName, String email) {
//
//            return createUser(-1,name, surName,email);
//
//        }
//        public User createUser(Integer id, String name, String surName, String email) {
//
//            User user = new User(name, surName, email, id);
//
//            if (validate(user)) {
//                return user;
//            } else {
//                return null;
//            }
//        }
//
//        public User createUser(Integer id, String name, String surName, String email, UserRoles... roles) {
//
//            User user = createUser(id,name, surName, email);
//            if (user != null){
//                for (UserRoles userRoles :roles) {
//                    user.addRole(userRoles);
//                }
//
//            }
//            return user;
//        }
//        public User createUser(String name, String surName, String email, UserRoles... roles) {
//            return createUser(-1,name,surName,email,roles);
//
//        }
//
//        private boolean validate(User user) {
//            if (emails.contains(user.getEmail())){
//                AirBnB.log.error("Failed to create user. Email is busy - " + user.getEmail());
//                return false;
//            }
//
//            Validator v = Validator.getInstance();
//
//            if (!v.validateUser(user)) {
//                AirBnB.log.error("Failed to create - " + user);
//                return false;
//            } else {
//                AirBnB.log.info("User created - " + user);
//                emails.add(user.getEmail());
//                users.add(user);
//                return true;
//            }
//        }
//    }
//}