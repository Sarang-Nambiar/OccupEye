package com.example.occupeye;


import java.util.HashMap;

class User_Collections_Test{
    static HashMap<String,String> user_table=new HashMap<>();

}
public class User {

    private String password;
    private String username;
    private String email;
    private String block;
    HashMap<String,String> obj=new HashMap<>();

    User(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }
    User(String email,String password){
        this.email=email;
        this.password=password;
    }
    boolean password_checker(){

        if (password.length()>7 && !password.contains(" "))return true;
        return false;
    }
    boolean email_checker(){
        if (!email.contains(" ") && email.contains("@gmail.com")&& email.length()>10)return true;
        return false;
    }
    boolean username_checker(){
        if (username.length()>7 && !username.contains(" "))return true;
        return false;
    }
    boolean validate(){
        if(username_checker()&&email_checker()&&password_checker()){
            User_Collections_Test.user_table.put(username,password);
            return true;
        }return false;
    }

    boolean validate_login(){

        if(email_checker()&&password_checker()){

            return true;}
        return false;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    public HashMap<String,String> database_obj(){
        obj.put("username",username);
        obj.put("password",password);
        obj.put("email",email);
        obj.put("block",block);
        return obj;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
