package Helpers;

public class UserSession {
    private static UserSession instance;
    public int userid;



    private UserSession(int userid) {
        this.userid = userid;
    }

    public static UserSession getInstace(int userid) {
        if(instance == null) {
            instance = new UserSession(userid);
        }
        return instance;
    }

    public int getUserid() {

        return userid;
    }

    public void cleanUserSession() {
        userid = 0;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" + "userid=" + userid + '}';
    }

}
