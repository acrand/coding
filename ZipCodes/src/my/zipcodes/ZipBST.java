package my.zipcodes;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * @author acrand
 *
 */
public class ZipBST {

	ZipNode root;
	
	/**
	 * Binary Search Class for zipCodes
	 */
	public ZipBST() {}
	
	/**
	 * Check to if binary tree is empty
	 * @return
	 */
	public boolean isEmpty(){
		return (size()==0? true: false);
	}

	/**
	 * Return a collection of int[] from BST
	 * @return
	 */
	public Collection<int[]> getAllValues() {
		ArrayList<int[]> results = new ArrayList<>();
				
		traverse(root,results);
		
		return results;
	}

	/**
	 * Merge range to binary tree
	 * @param range
	 * @return
	 */
	public boolean mergeRange(ZipRange range) {
		ZipNode current = root;
		
		while(current.left != null){
			current = current.left;
		}
		
		return mergeRange(current, range);
	}
	
	/**
	 * return size of tree
	 * @return
	 */
	public int size(){
		return size(root);
	}

	private int size(ZipNode node) {
		if(node == null) return 0;
		else return node.size;
	}

	/**
	 * Get range from tree by key
	 * @param key
	 * @return
	 */
	public ZipRange get(int key){
		return get(root, key);
	}

	private ZipRange get(ZipNode node, int key) {
		if(node == null) return null;
	
		boolean isKeyLess = (key < node.key? true : false);
		boolean isKeyGreater = (key > node.key? true : false);
		
		if(isKeyLess) {
			return get(node.left, key);
		}else if(isKeyGreater){
			return get(node.right, key);
		}else{
			return node.value;
		}
	}
	
	/**
	 * Insert range into tree
	 * @param key
	 * @param range
	 */
	public void insert(int key, ZipRange range){
		
		root = insert(root, key, range);
	}
		
	private ZipNode insert(ZipNode node, int key, ZipRange range) {
		if(node == null) return new ZipNode(key, range, 1);
		
		boolean isKeyLess = (key < node.key? true : false);
		boolean isKeyGreater = (key > node.key? true : false);
		
		if(isKeyLess) {
			node.left = insert(node.left, key,range);
		}else if(isKeyGreater){
			node.right = insert(node.right, key, range);
		}else{	
			node.value = range;
		}
		
		node.size = 1 + size(node.left) + size(node.right);
		
		return node;
	}
	
	/**
	 * Delete range from tree by key
	 * @param key
	 */
	public void delete(int key){
		root = delete(root, key);
	}

	private ZipNode delete(ZipNode node, int key) {
		if(node == null) return null;
	
		boolean isKeyLess = (key < node.key? true : false);
		boolean isKeyGreater = (key > node.key? true : false);
		
		if(isKeyLess) {
			node.left = delete(node.left, key);
		}else if(isKeyGreater){
			node.right = delete(node.right, key);
		}else{
			
			if(node.right == null) return node.left;
			
			if(node.left == null) return node.right;
			
			ZipNode currentNode =  node;
			
			node =  minNode(currentNode.right);
			
			node.right = deleteMinMode(currentNode.right);
			
			node.left = currentNode.left;
			
		}
		
		node.size = size(node.left) + size(node.right) + 1;
		
		return node;
	}	
	
	private ZipNode deleteMinMode(ZipNode node){
		if(node.left == null) return node.right;
		
		node.left = deleteMinMode(node);
		
		node.size =  size(node.left)+ size(node.right)+ 1;
		
		return node;
	}
	private ZipNode minNode(ZipNode node){
		if(node.left == null) return node;
		else return minNode(node);
	}

	private ZipNode traverse(ZipNode node, ArrayList<int[]> ranges){
		if(node!=null){
			ranges.add(get(node, node.key).getRange());
		}
		if(node.left != null){ 
			return traverse(node.left,ranges);
		}
		
		if(node.right != null){
			
			return traverse(node.right,ranges);
		}	
		return null;
		
	}
		
	private boolean isBoundInRange(int bound, ZipRange range){
				
		if(bound > range.lowerBound && bound < range.upperBound)
			return true;
		else return false;
	}

	private boolean isBoundNextInRange(int upperBound, ZipRange range) {
		if(range.upperBound + 1 == upperBound )
			return true;
		else return false;
	}
	
	private boolean mergeRange(ZipNode node,ZipRange range){
		ZipNode current = node;
		boolean replaceLowerBound = false;
		boolean replaceUpperBound = false;
		boolean rangOverlaps = false;
		
		int newLowerBound = current.key, newUpperBound = current.value.upperBound;
		
		if (current.key > range.lowerBound) {
			replaceLowerBound = true;
			rangOverlaps = true;
			newLowerBound = range.lowerBound;		
		}
		
		if (isBoundInRange(range.lowerBound, current.value) && range.upperBound > current.value.upperBound) {
			
			replaceUpperBound = true;
			rangOverlaps = true;
			
			newUpperBound = range.upperBound;
			
		}
		
		if (isBoundInRange(range.upperBound, current.value)) {
			replaceUpperBound = true;
			rangOverlaps = true;
			newUpperBound = range.upperBound;
		}
		 
		if(isBoundNextInRange(range.upperBound, current.value)){
			replaceUpperBound = true;
			rangOverlaps = true;
			newUpperBound = range.upperBound;
		}
		
		if(rangOverlaps){
			delete(current.key);
		
			if(replaceLowerBound || replaceUpperBound){
				insert(newLowerBound, new ZipRange(new int[]{newLowerBound, newUpperBound}) ); 
			}
		}else if(current.right!=null){
			
			rangOverlaps = mergeRange(current.right, range);
		}
		
		return rangOverlaps;
	}
	
	
	/**
	 * Internal class for nodes of the binary search tree 
	 * @author acrand
	 *
	 */
	class ZipNode {

		int key;
		int size;
		ZipRange value;
		ZipNode left, right;
		
		public ZipNode(int key, ZipRange range, int size) {
			this.key = key;
			this.size = size;
			this.value = range;
		}	
	
		@Override
		public String toString() {
			
			return value.toString();
		}
		
	}

}
