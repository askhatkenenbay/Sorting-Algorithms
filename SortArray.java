public class SortArray {

	/**
	 * @param array  input array which will be sorted
	 * running time O(n^2) 
	 * in place
	 */
	public static void insertion(int[] array) {
		int key;
		int move;
		for(int i = 1; i<array.length; i++) {
			key = array[i];
			//Insert array[i] into the sorted sequence array[1...i-1]
			move = i-1;
			while(move>=0 && array[move]>key) {
				array[move+1] = array[move];
				move--;
			}
			array[move+1] = key;
		}
	}

	/**
	 * @param array	input array which will be sorted
	 * running time O(nlgn) 
	 * not in place
	 */
	public static void mergeSort(int[] array) {
		int startIndex = 0;
		int endIndex = array.length-1;
		mergeSortHelper(array,startIndex, endIndex);
	}
	private static void mergeSortHelper(int[] array, int startIndex, int endIndex) {
		if(startIndex<endIndex) {
			int q = (startIndex+endIndex)/2 ;
			mergeSortHelper(array, startIndex, q);
			mergeSortHelper(array, q+1, endIndex);
			merge(array, startIndex, q , endIndex);
		}
	}
	private static void merge(int[] array, int startIndex, int q, int endIndex) {
		int size1 = q - startIndex +1;
		int size2 = endIndex - q;
		int[] firstSubArray = new int[size1+1];
		int[] secondSubArray = new int[size2+1];
		
		for(int i =0; i<size1; i++) {
			firstSubArray[i] = array[startIndex+i];
		}
		for(int i=0; i<size2; i++) {
			secondSubArray[i] = array[q+i+1];
		}
		firstSubArray[size1] = 2_147_483_647;
		secondSubArray[size2] = 2_147_483_647;
		int i =0;
		int j =0;
		for(int k =startIndex;k<=endIndex;k++) {
			if(firstSubArray[i]<=secondSubArray[j]) {
				array[k] = firstSubArray[i];
				i++;
			}else {
				array[k] = secondSubArray[j];
				j++;
			}
		}
	}

	/**
	 * @param array	input array which will be sorted
	 * running time O(nlgn) 
	 * in place
	 */
	public static void heapSort(int[] array) {
		int temp;
		int heapSize = buildMaxHeap(array);
		for(int i = array.length-1; i>0; i--) {
			temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			heapSize--;
			maxHeapify(array,0, heapSize);
		}
	}
	private static int buildMaxHeap(int[] array) {
		int heapSize = array.length;
		for(int i = (array.length/2); i>=0; i--) {
			 maxHeapify(array,i, heapSize);
		}
		return heapSize;
	}
	
	private static void maxHeapify(int[] array, int i, int heapSize) {
		int left = left(array, i, heapSize);
		int right = right(array, i, heapSize);
		int largest;
		if(left<=heapSize && array[left]>array[i]) {
			largest = left;
		}else {
			largest = i;
		}
		
		if(right<=heapSize && array[right]>array[largest]) {
			largest = right;
		}
		if(largest != i) {
			int temp;
			temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			maxHeapify(array,largest,heapSize);
		}
		
	}
	private static int left(int[] array, int i, int heapSize) {
		int left = 2*i+1;
		if(left<array.length && left<heapSize) {
			return left;
		}else {
			return heapSize+1;
		}
		
	}
	private static int right(int[] array, int i, int heapSize) {
		int right = 2*i+2;
		if(right<array.length && right<heapSize) {
			return right;
		}else {
			return heapSize+1;
		}
	}
	
	/**
	 * @param array 		input array which will be sorted
	 * @param startIndex	starting index
	 * @param endIndex		ending index
	 * to sort whole array pass startIndex=0 && endIndex=array.length-1
	 * running time O(n^2), W(nlgn), average(nlgn)
	 * to sort whole array pass startIndex=0 && endIndex=array.length-1
	 */
	public static void quickSort(int[] array, int startIndex, int endIndex) {
		if(startIndex<endIndex) {
			int q = partition(array, startIndex, endIndex);
			quickSort(array,startIndex,q-1);
			quickSort(array,q+1,endIndex);
		}
	}
	private static int partition(int[] arrayIn, int startIndex, int endIndex) {
		int x = arrayIn[endIndex];
		int i = startIndex-1;
		int temp;
		for(int j=startIndex;j<endIndex;j++) {
			if(arrayIn[j]<=x) {
				i++;
				temp =arrayIn[j];
				arrayIn[j]=arrayIn[i];
				arrayIn[i]=temp;
			}
		}
		temp =arrayIn[i+1];
		arrayIn[i+1]=arrayIn[endIndex];
		arrayIn[endIndex]=temp;
		return i+1;
	}
	/**
	 * @param array 		input array which will be sorted
	 * @param startIndex	starting index
	 * @param endIndex		ending index
	 * to sort whole array pass startIndex=0 && endIndex=array.length-1
	 * running time average(nlgn)
	 */
	public  static void randomizedQuicksort(int[] array, int startIndex, int endIndex) {
		if(startIndex<endIndex) {
			int q = randomizedPartition(array, startIndex, endIndex);
			randomizedQuicksort(array,startIndex,q-1);
			randomizedQuicksort(array,q+1,endIndex);
		}
		
	}
	private static int randomizedPartition(int[] arrayIn, int startIndex, int endIndex) {
		int i = (int)(Math.random()*((endIndex-startIndex)+1))+startIndex;
		int temp = arrayIn[endIndex];
		arrayIn[endIndex] = arrayIn[i];
		arrayIn[i] = temp;
		return  partition(arrayIn,startIndex,endIndex);
	}

	/**
	 * Assumes that each of the n input elements is an integer in the range 0 to k
	 * for some integer k
	 * 
	 * @param array		input array which will be sorted
	 * @param k			range of integers [0,k]
	 * running time O(n)
	 * not in place, stable
	 */
	public static void countingSort(int[] array, int k) {
		int[] workStorage = new int[k+1];
		int[] result = new int[array.length];
		for(int i=0; i<array.length;i++) {
			workStorage[array[i]] = workStorage[array[i]]+1;
		}
		for(int i=0; i<k; i++) {
			workStorage[i+1] = workStorage[i+1] + workStorage[i];
		}
		for(int i = array.length-1; i>=0; i--) {
			result[workStorage[array[i]]-1] = array[i];
			workStorage[array[i]] = workStorage[array[i]] - 1;
		}
		for(int i=0;i<array.length;i++) {
			array[i] = result[i];
		}
	}

	private SortArray() {
	    throw new IllegalStateException("Utility class");
	  }

}
