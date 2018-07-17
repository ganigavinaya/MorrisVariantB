import java.util.List;

public class MiniMaxGameImproved {

	int statisticEstimateImproved(char[] board, int numBlackMoves ){
		int[] pieceCount = Utility.countWBPieces(board);
		int mills = Utility.countPossibleMills(board);

		if(pieceCount[Utility.BLACK]<=2)
			return 10000;
		else if(pieceCount[Utility.WHITE]<=2)
			return -10000;
		else if(numBlackMoves==0)
			return 10000;
		else
			return (1000*(pieceCount[Utility.WHITE] - pieceCount[Utility.BLACK]+ mills) - numBlackMoves);
	}

	public MorrisGameBoard maxMinGameImproved(int depth, char[] board, boolean flag){
		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if (depth==0) {
			possibleMoves = MidEndGameFunctions.generateBlackMoves(board);
			result.estimate = statisticEstimateImproved(board,possibleMoves.size());
			result.count++;
			return result;
		}

		if(flag){
			result.estimate = Integer.MIN_VALUE;
			possibleMoves = MidEndGameFunctions.generateMovesMidEndGame(board);
		}
		else{
			result.estimate = Integer.MAX_VALUE;
			possibleMoves = MidEndGameFunctions.generateBlackMoves(board);
		}

		for(char[] pos:possibleMoves){
			if(flag) {
				MorrisGameBoard mgIn = maxMinGameImproved(depth - 1, pos, false);
				if (mgIn.estimate > result.estimate) {
					result.estimate = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
			else{
				MorrisGameBoard mgIn = maxMinGameImproved(depth - 1, pos, true);
				if (mgIn.estimate < result.estimate) {
					result.estimate = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
		}

		return result;
	}



	public static void main(String[] args) {

		int depth = Integer.parseInt(args[2]);

		//reads input board position
		char[] board = FileUtil.readInput(args[0]);

		MiniMaxGameImproved mgi = new MiniMaxGameImproved();
		MorrisGameBoard res = mgi.maxMinGameImproved(depth,board,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("MINMAX Game Improved estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);
	}
}
