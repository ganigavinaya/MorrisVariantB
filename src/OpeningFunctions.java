import java.util.ArrayList;
import java.util.List;

public class OpeningFunctions {

	public static List<char[]> generateAdd(char[] board) {
		List<char[]> allPositionList = new ArrayList<>();

		for(int i=0;i<board.length;i++){
			if(board[i]=='x'){
				char[] copy = board.clone();
				copy[i]='W';
				if(Utility.closeMill(i,copy)){
					Utility.generateRemove(copy, allPositionList);
				}
				else {
					allPositionList.add(copy);
				}
			}
		}

		return allPositionList;
	}

	public static List<char[]> generateBlackMoves(char[] board){
		char[] blackBoard = Utility.swapBoard(board);
		List<char[]> possibleMoves = generateAdd(blackBoard);
		List<char[]> whiteMoves = new ArrayList<>();

		possibleMoves.forEach(x -> whiteMoves.add(Utility.swapBoard(x)));
		return whiteMoves;
	}

	public static int staticEstimation(char[] board) {
		int[] pieceCount = Utility.countWBPieces(board);
		return pieceCount[Utility.WHITE]-pieceCount[Utility.BLACK];
	}

	public static int staticEstimationBlack(char[] board) {
		int[] pieceCount = Utility.countWBPieces(board);
		return pieceCount[Utility.BLACK]-pieceCount[Utility.WHITE];
	}
}
