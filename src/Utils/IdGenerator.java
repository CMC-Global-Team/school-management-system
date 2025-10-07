package Utils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class IdGenerator {
    public static String generateId(List<String> existingIds, String prefix, int length) {
        if (existingIds == null || existingIds.isEmpty()) {
            return prefix + String.format("%0" + length + "d", 1);
        }

        Pattern pattern = Pattern.compile(prefix + "(\\d+)");
        int max = 0;

        for (String id : existingIds) {
            Matcher matcher = pattern.matcher(id);
            if (matcher.matches()) {
                int num = Integer.parseInt(matcher.group(1));
                if (num > max) {
                    max = num;
                }
            }
        }

        return prefix + String.format("%0" + length + "d", max + 1);
    }

}
