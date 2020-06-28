package Worker.SubSystem;

import Worker.Parser;

import java.util.ArrayList;

public class Authority {

    ArrayList<String> validUsers;
    ArrayList<String> validPasswords;

    private userStatus currentUserStatus = userStatus.NOTLOGGEDIN;

    private enum userStatus {
        NOTLOGGEDIN, ENTEREDUSERNAME, LOGGEDIN
    }

    public Authority(){
        validUsers.add("user1");
        validPasswords.add("pwd1");
    }

    public void handleUser(String username)
    {
        if (validUsers.contains(username.toLowerCase()))
        {
            Parser.sendMsgToClient("331 User name okay, need password");
            currentUserStatus = userStatus.ENTEREDUSERNAME;
        }
        else if (currentUserStatus == userStatus.LOGGEDIN)
        {
            Parser.sendMsgToClient("530 User already logged in");
        }
        else
        {
            Parser.sendMsgToClient("530 Not logged in");
        }
    }

    private void handlePass(String password)
    {
        // User has entered a valid username and password is correct
        if (currentUserStatus == userStatus.ENTEREDUSERNAME && validPasswords.contains(password))
        {
            currentUserStatus = userStatus.LOGGEDIN;
            Parser.sendMsgToClient("230-Welcome to HKUST");
            Parser.sendMsgToClient("230 User logged in successfully");
        }

        // User is already logged in
        else if (currentUserStatus == userStatus.LOGGEDIN)
        {
            Parser.sendMsgToClient("530 User already logged in");
        }

        // Wrong password
        else
        {
            Parser.sendMsgToClient("530 Not logged in");
        }
    }
}
