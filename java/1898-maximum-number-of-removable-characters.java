class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int start = 0;
        int end = removable.length;

        while (start < end) { //binary search for efficiency
            int mid = start + (end - start + 1) / 2;
            if (isSubsequence(s, p, removable, mid)) { //if p is still a subsequence after removing mid chars, move to the right half
                start = mid;
            } else { //if p is not a subsequence after removing mid chars, move to the left half
                end = mid - 1;
            }
        }

        return start;
    }

    private boolean isSubsequence(String s, String p, int[] removable, int k) {
        boolean[] removed = new boolean[s.length()]; //to mark chars to be removed
        
        for (int i = 0; i < k; i++) {
            removed[removable[i]] = true;
        }

        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (removed[i]) {  //skip chars that are marked for removal
                i++;
                continue;
            }
            if (s.charAt(i) == p.charAt(j)) { //if the chars match, move to the next char in p
                i++;
                j++;
            } else { //otherwise, move to the next char in s
                i++;
            }
        }

        // If our j pointer reaches the end of 'p' string then 'p' is a subsequence 
        // of 's' so return true otherwise return false
        return j == p.length();
    }
}

