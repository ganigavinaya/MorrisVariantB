import java.util.ArrayList;
import java.util.List;

public class Utility {
	static int WHITE = 0;
	static int BLACK = 1;

	public static boolean closeMill(int j,char[] board){
		if(board[j]=='x')
			return false;

		char C = board[j];
		switch(j){
			case 18: if((board[19]==C && board[20]==C) || (board[6]==C && board[0]==C)) return true; else break;
			case 19: if((board[18]==C && board[20]==C) || (board[16]==C && board[13]==C)) return true; else break;
			case 20: if((board[19]==C && board[18]==C) || (board[11]==C && board[1]==C)) return true; else break;

			case 15: if((board[16]==C && board[17]==C) || (board[7]==C && board[2]==C)) return true; else break;
			case 16: if((board[19]==C && board[13]==C) || (board[15]==C && board[17]==C)) return true; else break;
			case 17: if((board[10]==C && board[3]==C) || (board[15]==C && board[16]==C)) return true; else break;

			case 12: if((board[8]==C && board[4]==C) || (board[13]==C && board[14]==C)) return true; else break;
			case 13: if((board[12]==C && board[14]==C) || (board[19]==C && board[16]==C)) return true; else break;
			case 14: if((board[9]==C && board[5]==C) || (board[12]==C && board[13]==C)) return true; else break;

			case 6: if((board[7]==C && board[8]==C) || (board[18]==C && board[0]==C)) return true; else break;
			case 7: if((board[6]==C && board[8]==C) || (board[15]==C && board[2]==C)) return true; else break;
			case 8: if((board[6]==C && board[7]==C) || (board[12]==C && board[4]==C)) return true; else break;
			case 9: if((board[10]==C && board[11]==C) || (board[14]==C && board[5]==C)) return true; else break;
			case 10: if((board[9]==C && board[11]==C) || (board[17]==C && board[3]==C)) return true; else break;
			case 11: if((board[9]==C && board[10]==C) || (board[20]==C && board[1]==C)) return true; else break;

			case 4: if((board[12]==C && board[8]==C) || (board[0]==C && board[2]==C)) return true; else break;
			case 5: if((board[14]==C && board[9]==C)) return true; else break;

			case 2: if((board[15]==C && board[7]==C) || (board[0]==C && board[4]==C)) return true; else break;
			case 3: if((board[17]==C && board[10]==C)) return true; else break;

			case 0: if((board[18]==C && board[6]==C) || (board[4]==C && board[2]==C)) return true; else break;
			case 1: if((board[20]==C && board[11]==C)) return true; else break;
		}
		return false;
	}

	public static void generateRemove(char[] board, List<char[]> allPositionList){
		boolean flag = false;

		for(int i=0;i<board.length;i++){
			if(board[i]=='B'){
				if(!closeMill(i,board)){
					char[] copy = board.clone();
					copy[i]='x';
					allPositionList.add(copy);
					flag = true;
				}
			}
		}

		if(!flag){
			char[] copy = board.clone();
			allPositionList.add(copy);
		}
	}

	public static char[] swapBoard(char[] board){
		char[] temp = new char[board.length];

		for(int i=0;i<board.length;i++){
			char c = board[i];
			temp[i] = (c=='B')?'W':((c=='W')?'B':'x');
		}

		return temp;
	}

	public static int[] countWBPieces(char[] board){
		int[] res=new int[]{0,0};
		for(int i=0;i<board.length;i++) {
			if(board[i]=='W') {
				res[WHITE]++;
			}
			else if(board[i]=='B') {
				res[BLACK]++;
			}
		}

		return res;
	}

	public static List<Integer> neighbors(int j){
		List<Integer> neighborList = new ArrayList<>();

		switch(j){
			case 0:
				neighborList.add(1);
				neighborList.add(2);
				neighborList.add(6);
				break;

			case 1:
				neighborList.add(0);
				neighborList.add(11);
				break;

			case 2:
				neighborList.add(0);
				neighborList.add(3);
				neighborList.add(7);
				break;

			case 3:
				neighborList.add(2);
				neighborList.add(10);
				break;

			case 4:
				neighborList.add(2);
				neighborList.add(5);
				neighborList.add(8);
				break;

			case 5:
				neighborList.add(4);
				neighborList.add(9);
				break;

			case 6:
				neighborList.add(0);
				neighborList.add(7);
				neighborList.add(18);
				break;

			case 7:
				neighborList.add(2);
				neighborList.add(6);
				neighborList.add(8);
				neighborList.add(15);
				break;

			case 8:
				neighborList.add(4);
				neighborList.add(7);
				neighborList.add(12);
				break;

			case 9:
				neighborList.add(5);
				neighborList.add(10);
				neighborList.add(14);
				break;

			case 10:
				neighborList.add(3);
				neighborList.add(9);
				neighborList.add(11);
				neighborList.add(17);
				break;

			case 11:
				neighborList.add(1);
				neighborList.add(10);
				neighborList.add(20);
				break;

			case 12:
				neighborList.add(8);
				neighborList.add(13);
				break;

			case 13:
				neighborList.add(12);
				neighborList.add(14);
				neighborList.add(16);
				break;

			case 14:
				neighborList.add(9);
				neighborList.add(13);
				break;

			case 15:
				neighborList.add(7);
				neighborList.add(16);
				break;

			case 16:
				neighborList.add(13);
				neighborList.add(15);
				neighborList.add(17);
				neighborList.add(19);
				break;

			case 17:
				neighborList.add(10);
				neighborList.add(16);
				break;

			case 18:
				neighborList.add(6);
				neighborList.add(19);
				break;
			case 19:
				neighborList.add(16);
				neighborList.add(18);
				neighborList.add(20);
				break;
			case 20:
				neighborList.add(11);
				neighborList.add(19);
				break;

		}
		return neighborList;
	}

	public static int countPossibleMills(char[] board){
		int mills = 0;

		for(int i=0;i<board.length;i++){
			if(board[i]=='x'){
				board[i]='W';
				if(closeMill(i,board)){
					mills++;
				}
				board[i]='x';
			}
		}
		return mills;
	}

}
