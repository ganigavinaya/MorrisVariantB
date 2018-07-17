import java.util.List;

public class MiniMaxGameBlack {

	public MorrisGameBoard maxMinGameBlack(int depth, char[] board, boolean flag){
		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if (depth==0) {
			MidEndGameFunctions.leafLevelBlack(board, result);
			return result;
		}

		if(flag){
			result.estimate = Integer.MIN_VALUE;
			possibleMoves = MidEndGameFunctions.generateBlackMoves(board);
		}
		else{
			result.estimate = Integer.MAX_VALUE;
			possibleMoves = MidEndGameFunctions.generateMovesMidEndGame(board);
		}

		for(char[] pos:possibleMoves){
			if(flag) {
				MorrisGameBoard mgIn = maxMinGameBlack(depth - 1, pos, false);
				if (mgIn.estimate > result.estimate) {
					result.estimate = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
			else{
				MorrisGameBoard mgIn = maxMinGameBlack(depth - 1, pos, true);
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

		MiniMaxGameBlack mg = new MiniMaxGameBlack();
		MorrisGameBoard res = mg.maxMinGameBlack(depth,board,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("MINMAX game black estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);
	}
}
