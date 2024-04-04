class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int sIndex=0;
        int fIndex =1;
        while(fIndex < nums.length){
            // Check if the two are same, find nex unique
            if(nums[sIndex] != nums[fIndex]) {
                nums[++sIndex] = nums[fIndex];
            } 
            fIndex++;
        }
        return sIndex+1;
    }
}
