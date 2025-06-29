// 3582. Generate Tag for Video Caption
public class P3582 {
    public String generateTag(String caption) {
        StringBuilder res = new StringBuilder();
        res.append('#');
        int n = caption.length();
        String[] words = caption.split(" ");
        boolean first = true;
        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) continue;
            if (first) {
                first = false;
                res.append(words[i].toLowerCase());
            } else {
                res.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
            }
        }
        return res.substring(0, Math.min(res.length(), 100));
    }
}
