//Student No: C1560749

public class hasher{
	//where k = key
	public int hashFn(int a, int b, int p, int m, int k){
		//Apply fn to get index
		int index = ( ((a*k) + b) % p ) % m;
		return index;
	}
}