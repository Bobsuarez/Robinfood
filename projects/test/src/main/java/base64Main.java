import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

public class base64Main {
    public static final String TARGET_BACKSLASH = "\\";
    public static final String TARGET_DOUBLE_BACKSLASH = "\\\\";

    public static void main(String[] args) {

        var data =
                "IntcImNoYW5uZWxJZFwiOjEwLFwiY291cG9uUmVmZXJlbmNlXCI6bnVsbCxcImRhdGVUaW1lXCI6XCIyMDIzLTEwLTE4VDE4OjIyOjU0LjU0OTc5NzY1NVwiLFwiaW50ZWdyYXRpb25JZFwiOm51bGwsXCJvcmRlcklkXCI6NTQ5MzcyMyxcIm9yZGVyVWlkXCI6bnVsbCxcIm9yZGVyVXVpZFwiOlwiMTUxNGJhYzYtZWVjOC00MWMzLTkyNWEtNzhhNjY0NTczZTE2XCIsXCJzdGF0dXNDb2RlXCI6XCJPUkRFUl9JTl9QUk9HRVNTXCIsXCJ0cmFuc2FjdGlvbklkXCI6MjM2NTIxMyxcInRyYW5zYWN0aW9uVXVpZFwiOlwiMGE1ZjY0NWEtMGFhNy00MDYwLWE2MTEtZWRmNmEyMWI5NmQ4XCJ9Ig==";

        System.out.println(execute(data));
    }

    public static String execute(String messageActiveMQEvent) {

        byte[] prueba = Base64.decodeBase64(messageActiveMQEvent);
        String message = new String(prueba);
        System.out.println(message);
        message = deleteBackslash(TARGET_DOUBLE_BACKSLASH, message);
        message = deleteBackslash(TARGET_BACKSLASH, message);
        return deleteFirstAndLastCharacter(message);
    }

    private static String deleteBackslash(String target, String message) {

        return message.replace(target, "");
    }

    private static String deleteFirstAndLastCharacter(String message) {

        return message.substring(1, message.length() - 1);
    }

}
