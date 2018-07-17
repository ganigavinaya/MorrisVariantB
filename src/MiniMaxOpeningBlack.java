import java.util.List;

public class MiniMaxOpeningBlack {

	public MorrisGameBoard maxMin(int depth, char[] board, boolean flag) {

		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if(depth==0){
			int staticEstimate = OpeningFunctions.staticEstimationBlack(board);
			return new MorrisGameBoard(staticEstimate,result.count+1,board);
		}

		if(flag){
			result.estimate = Integer.MIN_VALUE;
			possibleMoves = OpeningFunctions.generateBlackMoves(board);

		}
		else{
			result.estimate = Integer.MAX_VALUE;
			possibleMoves = OpeningFunctions.generateAdd(board);
		}

		for(char[] pos:possibleMoves){
			if(flag) {
				MorrisGameBoard mgIn = maxMin(depth - 1, pos, false);
				if (mgIn.estimate > result.estimate) {
					result.estimate = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
			else{
				MorrisGameBoard mgIn = maxMin(depth - 1, pos, true);
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

		MiniMaxOpeningBlack mob = new MiniMaxOpeningBlack();
		MorrisGameBoard res = mob.maxMin(depth,board,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("MINMAX black estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);
	}
}
