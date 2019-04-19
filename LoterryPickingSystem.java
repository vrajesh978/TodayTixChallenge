// A Java program to count all subsets with given sum. 
import java.util.ArrayList; 
import java.util.List;
import java.util.*;
public class LoterryPickingSystem 
{ 
	// dp[i][j] is going to store true if sum j is 
	// possible with array elements from 0 to i. 
	static boolean[][] dp; 
	static List<List<Integer>> result;
	static ArrayList<Integer> temp;

	// A recursive function to print all subsets with the 
	// help of dp[][]. Vector p[] stores current subset. 
	static void printSubsetsRec(int arr[], int i, int sum, 
										ArrayList<Integer> p) 
	{ 
		// If we reached end and sum is non-zero. We print 
		// p[] only if arr[0] is equal to sun OR dp[0][sum] 
		// is true. 
		
		if (i == 0 && sum != 0 && dp[0][sum]) 
		{ 
		    
			p.add(arr[i]); 
			temp = new ArrayList<Integer>();
			temp.addAll(p);
			result.add(temp);
			p.clear(); 
			return; 
		} 
	
		// If sum becomes 0 
		if (i == 0 && sum == 0) 
		{ 
		    temp = new ArrayList<Integer>();
			temp.addAll(p);
			result.add(temp);
			p.clear(); 
			return; 
		} 
	
		// If given sum can be achieved after ignoring 
		// current element. 
		if (dp[i-1][sum]) 
		{ 
			// Create a new vector to store path 
			ArrayList<Integer> b = new ArrayList<>(); 
			b.addAll(p); 
			printSubsetsRec(arr, i-1, sum, b); 
		} 
	
		// If given sum can be achieved after considering 
		// current element. 
		if (sum >= arr[i] && dp[i-1][sum-arr[i]]) 
		{ 
			p.add(arr[i]); 
			printSubsetsRec(arr, i-1, sum-arr[i], p); 
		} 
	} 
	
	// Prints all subsets of arr[0..n-1] with sum 0. 
	static List<List<Integer>> printAllSubsets(int arr[], int n, int sum) 
	{ 
		if (n == 0 || sum < 0) 
		return new ArrayList<List<Integer>>(); 
	
		// Sum 0 can always be achieved with 0 elements 
		dp = new boolean[n][sum + 1]; 
		result = new ArrayList<>();
		for (int i=0; i<n; ++i) 
		{ 
			dp[i][0] = true; 
		} 
	
		// Sum arr[0] can be achieved with single element 
		if (arr[0] <= sum) 
		dp[0][arr[0]] = true; 
	
		// Fill rest of the entries in dp[][] 
		for (int i = 1; i < n; ++i) 
			for (int j = 0; j < sum + 1; ++j) 
				dp[i][j] = (arr[i] <= j) ? (dp[i-1][j] || 
										dp[i-1][j-arr[i]]) 
										: dp[i - 1][j]; 
		if (dp[n-1][sum] == false) 
		{ 
			System.out.println("There are no subsets with" + 
												" sum "+ sum); 
			return new ArrayList<List<Integer>>(); 
		} 
	
		// Now recursively traverse dp[][] to find all 
		// paths from dp[n-1][sum] 
		ArrayList<Integer> p = new ArrayList<>(); 
		printSubsetsRec(arr, n-1, sum, p); 
		return result;
	} 
	
	public static List<Integer> mapWithUserId (List<List<Integer>> result, Map<Integer,Integer> userLotteryMap) {
    	List<Integer> result_list = new ArrayList<Integer>();
        Map<Integer,Integer> tempMap = userLotteryMap;
        Random ran = new Random(); 
        // generating integer 
        int nxt = ran.nextInt(result.size()); 
        System.out.println("Random index = " + nxt);
        List<Integer> resultData = result.get(nxt); //getting random index.
        List<Integer> tempList;
        for(int i = 0; i < resultData.size();i++){
        	int num = resultData.get(i);
        	tempList = new ArrayList<Integer>();
        	for (int id : tempMap.keySet()) {
        		if(tempMap.get(id) == num) {
                    tempList.add(id);
                }
            }
            nxt  = ran.nextInt(tempList.size()); ///getting random index.
        	int final_id = tempList.get(nxt); //randomly select user from users whose no. of tickets is same  
            result_list.add(final_id);    //final ans is in result_list.
            tempMap.remove(final_id);     // this will remove the user from the map 
        } 
        return result_list;
    }

	public static List<Integer> lotteryPicking(Map<Integer,Integer> userLotteryMap, int noOfTickets) {
		// getting the values of the users tickets
		
		List<Integer> val_list = new ArrayList<Integer>(userLotteryMap.values());
		int[] val = new int[val_list.size()];
        for(int i = 0; i < val_list.size(); i++) 
            val[i] = val_list.get(i);
 		int sum = noOfTickets;
 		List<List<Integer>> result1 = printAllSubsets(val, val.length, sum);
 		System.out.println(result1);
	    //List<List<Integer>> result = findSubSetArrayGivenSum(val,sum);	
	    List<Integer> result_list = mapWithUserId(result, userLotteryMap);
	    return result_list;
    }

	
	//Driver Program to test above functions 
	public static void main(String args[]) 
	{ 
		Map<Integer,Integer> userLotteryMap = new HashMap<Integer,Integer>();
		userLotteryMap.put(123,5);
		userLotteryMap.put(231,5);
		userLotteryMap.put(124,2);
		userLotteryMap.put(241,3);
		userLotteryMap.put(242,5);
		System.out.println(lotteryPicking(userLotteryMap,15));
	} 
} 
