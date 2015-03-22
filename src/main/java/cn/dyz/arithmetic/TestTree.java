package cn.dyz.arithmetic;

public class TestTree {

	public static void main(String[] args) {
		int[] preOrder={1,2,7,8,3,4};
		int[] inOrder={7,2,8,1,4,3};
		TreeNode tree1 = buildTree(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1);
		
		int[] preOrder2={2,7,9};
		int[] inOrder2={7,2,9};
		TreeNode tree2 = buildTree(preOrder2, 0, preOrder2.length-1, inOrder2, 0, inOrder2.length-1);
		
		
		System.out.println(subTree(tree1, tree2));
		
		//preIter(root);
		
	//	interIter(root);
	}
	
	public static boolean subTree(TreeNode tree1,TreeNode tree2){
		boolean result = false;
		if(tree1==null)
			return false;
		
		if(tree1.getValue()==tree2.getValue()){
			result=hasSubTree(tree1, tree2);
		}else{
			result =subTree(tree1.getLeft(), tree2);
			if(!result){
				result=subTree(tree1.getRgiht(), tree2);
			}
		}
		return result;
	}
	
	/**
	 * 判断tree1是否包含tree2
	 * @param tree1
	 * @param tree2
	 * @return
	 */
	public static boolean hasSubTree(TreeNode tree1,TreeNode tree2){
		boolean result=false;
		if(tree2==null){
			return true;
		}
		
		if(tree1==null){
			return false;
		}
		
		if(tree1.getValue()!=tree2.getValue()){
			return false;
		}
		result = equalsTree(tree1.getLeft(), tree2.getLeft());
		if(result){
			result=equalsTree(tree1.getRgiht(), tree2.getRgiht());
		}
		return result;
	}
	
	
	
	/**
	 * 判断两棵树是否相等
	 * @param tree1
	 * @param tree2
	 * @return
	 */
	public static boolean equalsTree(TreeNode tree1,TreeNode tree2){
		boolean result=false;
		if(tree1==null && tree2==null){
			return true;
		}
		
		if((tree1==null && tree2!=null)||(tree1!=null && tree2==null)){
			return false;
		}
		
		if(tree1.getValue()!=tree2.getValue()){
			return false;
		}
		result = equalsTree(tree1.getLeft(), tree2.getLeft());
		if(result){
			result=equalsTree(tree1.getRgiht(), tree2.getRgiht());
		}
		return result;
	}
	
	/**
	 * 前序遍历树
	 * @param node
	 */
	public static void preIter(TreeNode node){
		if(node!=null){
			System.out.println(node.getValue());
			
			if(node.getLeft()!=null){
				preIter(node.getLeft());
			}
			
			if(node.getRgiht()!=null){
				preIter(node.getRgiht());
			}
		}
	}
	/**
	 * 中序遍历树
	 * @param node
	 */
	public static void interIter(TreeNode node){
		if(node!=null){
			
			if(node.getLeft()!=null){
				interIter(node.getLeft());
			}
			
			System.out.println(node.getValue());
			
			if(node.getRgiht()!=null){
				interIter(node.getRgiht());
			}
			
		}
			
	}
	
	/**
	 * 根据前序遍历树和中序遍历树的结果构建树
	 * @param preOrder 前序遍历结果
	 * @param inOrder	中序遍历结果
	 */
	public static TreeNode buildTree(int[] preOrder,int preStartIndex,int preEndIndex,int[] inOrder,int inStartIndex,int inEndIndex){
		TreeNode root = new TreeNode();
		root.setValue(preOrder[preStartIndex]);
		
		int rootIndex =0;
		for(int i=inStartIndex;i<=inEndIndex;i++){
			if(inOrder[i]==preOrder[preStartIndex]){
				rootIndex=i;
				break;
			}
		}
		int leftSize = rootIndex-inStartIndex;
		int rightSize = inEndIndex-rootIndex;
		if(leftSize>0){
			root.setLeft(buildTree(preOrder, preStartIndex+1, preStartIndex+leftSize, inOrder, inStartIndex, rootIndex-1));
		}
		if(rightSize>0){
			root.setRgiht(buildTree(preOrder, preStartIndex+leftSize+1, preEndIndex, inOrder, rootIndex+1, inEndIndex));
		}
		
		return root;
	}
}

