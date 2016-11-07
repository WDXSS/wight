package test.wightview.utik;

/**
 * Created by zhouchao on 2016/10/20.
 */

public class StringUtil {

    public static boolean isNotBlank(String s) {
//        if(s == null || "".equals(s.trim())){
//            return false;
//        }
//        return true;
        return s != null && !("".equals(s.trim()));
    }
}
