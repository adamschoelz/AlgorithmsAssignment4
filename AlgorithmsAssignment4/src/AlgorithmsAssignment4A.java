import java.util.ArrayList;


public class AlgorithmsAssignment4A {
	public static void main(String args[]){
		String[] s = new String[3];
		
		s[0] = "90 6 90";
		s[1] = "3 90 90";
		s[2] = "90 90 5";
		System.out.println(generatePrice(s));
	}
	
	public static int generatePrice(String[] str){
		int cost = 0;
		int mini = 0, minj = 0;
		int[][] prices = new int[str.length][3];
		
		//Find minimum price and build price array
		for(int i = 0; i < str.length; i++){
			String[] toConvert = str[i].split(" ");
			for(int j = 0; j < 3; j++){
				prices[i][j] = Integer.parseInt(toConvert[j]);
				if(prices[i][j] < prices[mini][minj]){
					mini = i;
					minj = j;
				}
			}
		}
		cost += prices[mini][minj];
		int prevIndex = minj;
		//Build out prices from there
		for(int i = mini - 1; i >= 0; i--){
			int next1, next2;
			//Select mininum out of previous two relevant elements
			next1 = (prevIndex + 1) % 3;
			next2 = (prevIndex + 2) % 3;
			if(prices[i][next1] <= prices[i][next2]){
				prevIndex = next1;
				cost += prices[i][next1];
			}
			else{
				prevIndex = next2;
				cost += prices[i][next2];
			}
		}
		prevIndex = minj;
		for(int i = mini + 1; i < prices.length; i++){
			int next1, next2;
			//Select mininum out of previous two relevant elements
			next1 = (prevIndex + 1) % 3;
			next2 = (prevIndex + 2) % 3;
			System.out.println(next1 + " n2 " + next2 + " pi " + prevIndex);
			if(prices[i][next1] <= prices[i][next2]){
				prevIndex = next1;
				cost += prices[i][next1];
			}
			else{
				prevIndex = next2;
				cost += prices[i][next2];
			}
		}
		
		return cost;
	}
}
