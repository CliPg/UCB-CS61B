public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque deque = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            deque.addLast(ch);
        }
        return deque;
    }
}
