class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int value = 0;
        for(int i = 0; i < s.length(); i++) {
            // Get the value of first char
            int current = map.get(s.charAt(i));

            // Get the value of next char
            int next = 0;
            if(i+1 < s.length()) {
                next = map.get(s.charAt(i+1));
            }
            // If value of next char is bigger, then lets deduct the value of first char
            if(current < next) {
                value = value - current;
            } else {
                value = value + current;
            }
        }
        return value;
    }
}
