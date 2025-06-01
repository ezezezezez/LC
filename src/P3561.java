import java.util.Stack;

// 3561. Resulting String After Adjacent Removals
public class P3561 {
    public String resultingString(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            if (!stack.isEmpty() && (add(stack.peek(), 1) == x || stack.peek() == add(x, 1))) {
                stack.pop();
            } else {
                stack.push(x);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append((char) (stack.pop() + 'a'));
        return sb.reverse().toString();
    }

    int add(int x, int y) {
        return (x + y) % 26;
    }
}
