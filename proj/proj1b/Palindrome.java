
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque deque = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            deque.addLast(ch);
        }
        return deque;
    }

    public Deque<Character> wordInversion(String word){
        Deque deque = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            deque.addFirst(ch);
        }
        return deque;
    }
    public boolean isPalindrome(String word){
        Deque deque = wordToDeque(word);
        for (int i = 0; i < deque.size()/2; i++) {
            if (!(deque.removeFirst() == deque.removeLast())){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        int oriSize = deque.size();
        for (int i = 0; i < oriSize/2; i++) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())){
                return false;
            }
        }
        return true;
    }
}
