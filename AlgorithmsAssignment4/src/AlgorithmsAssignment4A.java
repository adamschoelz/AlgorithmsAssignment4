import java.util.Arrays;
import java.util.Scanner;

//Adam Schoelz

public class AlgorithmsAssignment4A {
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		int size = 0;
		System.out.println("Please enter a number and then a series of lines that represents the array");
		size = s.nextInt();
		String[] str = new String[size];
		s.nextLine();
		for(int i = 0; i < str.length; i++){
			str[i] = s.nextLine();
		}
		System.out.println(generatePrice(str));
		
		s.close();
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
