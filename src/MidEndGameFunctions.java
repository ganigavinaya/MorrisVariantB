import java.util.ArrayList;
import java.util.List;

public class MidEndGameFunctions {

	public static List<char[]> generateBlackMoves(char[] board){
		char[] blackBoard = Utility.swapBoard(board);
		List<char[]> possibleMoves = generateMovesMidEndGame(blackBoard);
		List<char[]> whiteMoves = new ArrayList<>();

		possibleMoves.forEach(x -> whiteMoves.add(Utility.swapBoard(x)));
		return whiteMoves;
	}

	public static List<char[]> generateMove(char[] board){
		List<char[]> possibleMoves = new ArrayList<>();

		for(int i=0;i<board.length;i++){
			if(board[i]=='W'){
				List<Integer> n = Utility.neighbors(i);
				for(int j:n){
					SubGenerate(board, possibleMoves, i, j);
				}
			}

		}
		return possibleMoves;
	}

	public static List<char[]> generateHopping(char[] board){
		List<char[]> possibleMoves = new ArrayList<>();

		for(int alpha=0;alpha<board.length;alpha++){
			if(board[alpha]=='W'){
				for(int beta=0;beta<board.length;beta++){
					SubGenerate(board, possibleMoves, alpha, beta);
				}
			}
		}

		return possibleMoves;
	}

	public static List<char[]> generateMovesMidEndGame(char[] board){
		int white = 0;

		for(char pos:board){
			if(pos=='W'){
				white++;
			}
		}

		return (white==3)?generateHopping(board):generateMove(board);
	}

	public static void leafLevel(char[] board, MorrisGameBoard result) {
		List<char[]> possibleMoves;
		possibleMoves = generateBlackMoves(board);
		result.estimate = staticEstimate(board,possibleMoves.size());
		result.count++;
	}

	public static void leafLevelBlack(char[] board, MorrisGameBoard result) {
		List<char[]> possibleMoves;
		possibleMoves = generateMovesMidEndGame(board);
		result.estimate = staticEstimateBlack(board,possibleMoves.size());
		result.count++;
	}

	public static int staticEstimate(char[] board, int numBlackMoves){
		int[] pieceCount = Utility.countWBPieces(board);
		if(pieceCount[Utility.BLACK]<=2)
			return 10000;
		else if(pieceCount[Utility.WHITE]<=2)
			return -10000;
		else if(numBlackMoves==0)
			return 10000;
		else
			return (1000*(pieceCount[Utility.WHITE] - pieceCount[Utility.BLACK]) - numBlackMoves);
	}

	public static int staticEstimateBlack(char[] board, int numBlackMoves){
		int[] pieceCount = Utility.countWBPieces(board);
		if(pieceCount[Utility.BLACK]<=2)
			return 10000;
		else if(pieceCount[Utility.WHITE]<=2)
			return -10000;
		else if(numBlackMoves==0)
			return 10000;
		else
			return (1000*(pieceCount[Utility.BLACK] - pieceCount[Utility.WHITE]) - numBlackMoves);
	}

	static void SubGenerate(char[] board, List<char[]> possibleMoves, int i, int j) {
		if(board[j]=='x'){
			char[] b = board.clone();
			b[i]='x';
			b[j]='W';
			if(Utility.closeMill(j,b))
				Utility.generateRemove(b,possibleMoves);
			else
				possibleMoves.add(b);
		}
	}
}
