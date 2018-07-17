import java.util.List;

public class MiniMaxOpening {

	public MorrisGameBoard maxMin(int depth, char[] board, boolean flag) {

		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if(depth==0){
			int staticEstimate = OpeningFunctions.staticEstimation(board);
			return new MorrisGameBoard(staticEstimate,result.count+1,board);
		}

		if(flag){
			result.estimate = Integer.MIN_VALUE;
			possibleMoves = OpeningFunctions.generateAdd(board);

		}
		else{
			result.estimate = Integer.MAX_VALUE;
			possibleMoves = OpeningFunctions.generateBlackMoves(board);
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

	public static void main(String[] args){
		int depth = Integer.parseInt(args[2]);

		//reads input board position
		char[] board = FileUtil.readInput(args[0]);

		MiniMaxOpening mo = new MiniMaxOpening();
		MorrisGameBoard res = mo.maxMin(depth,board,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("MINMAX estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);

	}

}
