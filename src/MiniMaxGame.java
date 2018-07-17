import java.util.List;

public class MiniMaxGame {


	public MorrisGameBoard maxMinGame(int depth, char[] board, boolean flag){
		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if (depth==0) {
			MidEndGameFunctions.leafLevel(board, result);
			return result;
		};

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
				MorrisGameBoard mgIn = maxMinGame(depth - 1, pos, false);
				if (mgIn.estimate > result.estimate) {
					result.estimate = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
			else{
				MorrisGameBoard mgIn = maxMinGame(depth - 1, pos, true);
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

		MiniMaxGame mg = new MiniMaxGame();
		MorrisGameBoard res = mg.maxMinGame(depth,board,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("MINMAX estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);
	}
}
