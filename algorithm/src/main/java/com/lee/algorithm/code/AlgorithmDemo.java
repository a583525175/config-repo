package com.lee.algorithm.code;

import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @ClassName AlgorithmDemo
 * @Author liwenpeng
 * @Date 2020/4/17 20:29
 * @Description TODO
 **/

public class AlgorithmDemo {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for (int num : nums) {
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }
            max = Math.max(max, sum);
        }

        return max;
    }

    public static int divideMaxSubArray(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int m = (left + right) / 2;

        int leftsum = divideMaxSubArray(nums, left, m);
        int rightsum = divideMaxSubArray(nums, m + 1, right);
        int crossssum = crossSum(nums, left, right);
        return Math.max(Math.max(leftsum, rightsum), crossssum);
    }

    public static int crossSum(int[] nums, int left, int right) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    public static int greedSubArray(int[] nums) {
        int maxSum = nums[0];
        int crossSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            crossSum = Math.max(nums[i], nums[i] + crossSum);
            maxSum = Math.max(crossSum, maxSum);
        }
        return maxSum;

    }

    public static int lengthOfLastWord(String s) {
        int count = 0;
        int n = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                count++;
                n = 1;
            } else {
                if (n == 0) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return count;
    }


    public static int[] plusOne(int[] digits) {
        int l = digits.length;
        //进位，开启循环;
        for (int i = l - 1; i >= 0; i--) {
            if ((digits[i] + 1) % 10 == 0) {
                if (i == 0) {
                    int arr[] = new int[digits.length + 1];
                    arr[0] = 1;
                    return arr;
                }
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        return digits;
    }

    public static String addBinary(String a, String b) {
        String dvalue;
        String svalue;
        int d = 0;
        if (a.length() > b.length()) {
            dvalue = b;
            svalue = a;
            d = a.length() - b.length();
        } else {
            dvalue = a;
            svalue = b;
            d = b.length() - a.length();
        }
        StringBuilder sb = new StringBuilder(dvalue);
        for (int i = 0; i < d; i++) {
            sb.insert(0, "0");
        }
        dvalue = sb.toString();
        sb.delete(0, sb.length());
        int n = 0;
        //两个字符串已经完全匹配，开启一次循环进行计算进位
        for (int i = svalue.length() - 1; i >= 0; i--) {
            int z = Integer.valueOf(String.valueOf(dvalue.charAt(i)));
            int k = Integer.valueOf(String.valueOf(svalue.charAt(i)));
            if (z + k + n >= 2) {
                //进位
                if (z + k + n == 2) {
                    sb.insert(0, 0);
                } else {
                    sb.insert(0, n);
                }
                //下一位需要+1
                n = 1;
            } else {
                sb.insert(0, z + k + n);
                n = 0;
            }

        }
        if (n == 1) {
            sb.insert(0, n);
        }

        return sb.toString();
    }

    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climb_Stairs(int n, int count) {

        if (n == 0) {
            count++;
            return count;
        }
        if (n < 0) {
            return 0;
        }
        return climb_Stairs(n - 1, count) + climb_Stairs(n - 2, count);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode tmp = head;
        while (tmp.next != null) {
            if (tmp.val == tmp.next.val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //暴力破解法
//        int i =m;
//        int j=0;
//        while(i<nums1.length){
//            nums1[i]=nums2[j];
//            i++;
//            j++;
//        }
//
//     for (int k=0; k<nums1.length;k++){
//         for(int t=0;t<nums1.length-k;t++){
//             if(t==nums1.length-k-1){
//                 break;
//             }
//             if(nums1[t]>nums1[t+1]){
//                 int tmp=nums1[t];
//                nums1[t]=nums1[t+1];
//                nums1[t+1]=tmp;
//             }
//         }
//     }
        //双指针前溯法
//        if(n==0){
//            return;
//        }
//        int [] tmpArr =new int[m+n];
//        int i=0;
//        int j=0;
//        int temp=0;
//        while(i<m&&j<n){
//            if(nums1[i]<=nums2[j]&&i<m){
//                tmpArr[temp]=nums1[i];
//                i++;
//            }else if(j<n){
//                tmpArr[temp]=nums2[j];
//                j++;
//            }
//            temp++;
//        }
//        if(i<m){
//            System.arraycopy(nums1,i,tmpArr,temp,m-i );
//        }
//        if(j<n){
//            System.arraycopy(nums2,j,tmpArr,temp, nums2.length-j);
//        }
//
//        System.arraycopy(tmpArr,0,nums1,0, tmpArr.length);
        //双指针后溯法
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
        }
        if (n == 0) {
            return;
        }

        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }
            k--;
        }
        System.arraycopy(nums2, 0, nums1, 0, j + 1);

    }


    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);

    }

    public static boolean isSymmetric(TreeNode root) {
        return isSameTree(root, root);
    }


    public static int maxDepth(TreeNode root) {
        return count(root, 0);
    }

    public static int count(TreeNode root, int count) {
        if (root == null) {
            return count;
        }
        count++;
        return Math.max(count(root.left, count), count(root.right, count));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList();

        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> l = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode tn = queue.poll();
                if (tn.left != null) {
                    queue.add(tn.left);
                }
                if (tn.right != null) {
                    queue.add(tn.right);
                }

                l.add(tn.val);
            }
            list.add(0, l);
        }

        return list;

    }

    public TreeNode sortedArrayToBST(int[] nums) {

        return helper(0, nums.length - 1, nums);
    }

    public static TreeNode helper(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int i = (left + right) / 2;
        TreeNode node = new TreeNode(nums[i]);
        node.left = helper(0, i - 1, nums);
        node.right = helper(i + 1, right, nums);
        return node;
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(rootLevel(root.left, 1) - rootLevel(root.right, 1)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    public static int rootLevel(TreeNode root, int nums) {
        if (root == null) {
            return nums;
        }
        nums++;
        return Math.max(rootLevel(root.left, nums), rootLevel(root.right, nums));

    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> quene = new LinkedList<>();
        int num = 0;
        quene.add(new Pair<>(root, 1));
        while (!quene.isEmpty()) {
            Pair<TreeNode, Integer> pair = quene.poll();
            num = pair.getValue();
            if (pair.getKey().left == null && pair.getKey().right == null) {
                break;
            }

            if (pair.getKey().left != null) {
                quene.add(new Pair<TreeNode, Integer>(pair.getKey().left, num + 1));
            }
            if (pair.getKey().right != null) {
                quene.add(new Pair<TreeNode, Integer>(pair.getKey().right, num + 1));
            }
        }
        return num;
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<Pair<TreeNode, Integer>> quene = new LinkedList<>();
        quene.add(new Pair<>(root, root.val));

        while (!quene.isEmpty()) {
            Pair<TreeNode, Integer> pair = quene.poll();
            TreeNode node = pair.getKey();
            if (node.right == null && node.left == null) {
                if (sum == pair.getValue()) {
                    //是叶子节点，且数值相等
                    return true;
                }
            }

            if (node.left != null) {
                quene.add(new Pair<>(node.left, node.left.val + pair.getValue()));
            }
            if (node.right != null) {
                quene.add(new Pair<>(node.right, node.right.val + pair.getValue()));
            }
        }
        return false;
    }

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0) {
            return list;
        }
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        list.add(l1);

        for (int i = 1; i <= numRows - 1; i++) {
            //获取上一级的list的
            List<Integer> uplist = list.get(i - 1);
            List<Integer> nList = new ArrayList();
            nList.add(1);
            for (int j = 0; j < i - 1; j++) {
                nList.add(uplist.get(j) + uplist.get(j + 1));
            }
            nList.add(1);
            list.add(nList);

        }

        return list;
    }

    public static List<Integer> getRow(int rowIndex) {

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        if (rowIndex == 0) {
            return l1;
        }
        list.add(l1);

        rowIndex = rowIndex + 1;
        for (int i = 1; i <= rowIndex - 1; i++) {
            //获取上一级的list的
            List<Integer> uplist = list.get(i - 1);
            List<Integer> nList = new ArrayList();
            nList.add(1);
            for (int j = 0; j < i - 1; j++) {
                nList.add(uplist.get(j) + uplist.get(j + 1));
            }
            nList.add(1);
            list.add(nList);

        }

        return list.get(list.size() - 1);
    }


    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(prices[i] - min, maxProfit);
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }
    public static int maxProfitII(int[] prices) {
        int maxProfit = 0;
        int m=0;
        int j=0;
        while(j<prices.length){
            int min = prices[j];
            for (int i = 1; i < prices.length; i++) {
                if(prices[i]<min){
                    min=prices[i];
                }else{
                    maxProfit+=prices[i]-min;
                    min=prices[i+1];
                    i++;
                }
            }
            m=Math.max(maxProfit,m);
            j++;
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,6,4};
        System.out.println(maxProfitII(nums));
    }
}
