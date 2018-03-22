package mavis.rvs.ac.th.readcode.utility;

/**
 * Created by Mavis on 22/03/2018.
 */

public class MyConstant {
    //    About URL  ค่าคงที่ของ URL
    private String urlPostUserString = "http://androidthai.in.th/mar/postUser.php";  // ตัวแปร String ชื่อ urlPostUserString
    private String urlGetAllUserString = "http://androidthai.in.th/mar/getAllUser.php";

    //    About Array
    private String[] loginStrings = new String[]{"id", "Name", "User", "Password"};

    public String[] getLoginStrings() {
        return loginStrings;
    }

    public String getUrlGetAllUserString() { return urlGetAllUserString;  }

    public String getUrlPostUserString() { return urlPostUserString;  }
}// Main Class
