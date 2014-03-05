package edu.iscas.leetcode;

/**
* Definition for binary tree
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* }
*/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}


public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
       
        if(root.left == null && root.right == null){
            return root.val == sum;
        }
       
        boolean checkLeft = false;
        boolean checkRight = false;
       
        if(root.left != null){
            checkLeft = hasPathSum(root.left,sum-root.val);
        }
        //���ͨ�����������Ѿ������ж��ˣ��Ͳ���ȥ���������ˣ����ٸ��Ӷ�
        if(checkLeft)
            return checkLeft;
        if(root.right != null){
            checkRight = hasPathSum(root.right,sum-root.val);
        }
        if(checkRight)
            return checkRight;
       
        return false;
    }
}

