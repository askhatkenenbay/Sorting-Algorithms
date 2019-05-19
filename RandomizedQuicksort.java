
public final class RandomizedQuicksort {
	
	public  static void randomizedQuicksort(int[] arrayIn, int startIndex, int endIndex) {
		if(startIndex<endIndex) {
			int q = randomizedPartition(arrayIn, startIndex, endIndex);
			randomizedQuicksort(arrayIn,startIndex,q-1);
			randomizedQuicksort(arrayIn,q+1,endIndex);
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
	
	private static int randomizedPartition(int[] arrayIn, int startIndex, int endIndex) {
		int i = (int)(Math.random()*((endIndex-startIndex)+1))+startIndex;
		int temp = arrayIn[endIndex];
		arrayIn[endIndex] = arrayIn[i];
		arrayIn[i] = temp;
		return  partition(arrayIn,startIndex,endIndex);
	}
	
	

	
}
