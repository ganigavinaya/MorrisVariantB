import java.util.List;

public class ABGame {


	public MorrisGameBoard alphaBetaMidEndGame(int depth, char[] board, int alpha, int beta, boolean flag){
		MorrisGameBoard result = new MorrisGameBoard();
		List<char[]> possibleMoves;

		if (depth==0) {
			possibleMoves = MidEndGameFunctions.generateBlackMoves(board);
			result.estimate = MidEndGameFunctions.staticEstimate(board,possibleMoves.size());
			result.count+=1;
			return result;
		}

		if(flag){
			possibleMoves = MidEndGameFunctions.generateMovesMidEndGame(board);
		}
		else{
			possibleMoves = MidEndGameFunctions.generateBlackMoves(board);
		}

		for(char[] pos:possibleMoves){
			if(flag) {
				MorrisGameBoard mgIn = alphaBetaMidEndGame(depth - 1, pos, alpha,beta,false);
				if (mgIn.estimate > alpha) {
					alpha = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}
			else{
				MorrisGameBoard mgIn = alphaBetaMidEndGame(depth - 1, pos,  alpha,beta,true);
				if (mgIn.estimate < beta) {
					beta = mgIn.estimate;
					result.board = pos;
				}
				result.count += mgIn.count;
			}

			if(alpha>=beta)
				break;
		}
		result.estimate = flag?alpha:beta;
		return result;
	}

	public static void main(String[] args) {
		int depth = Integer.parseInt(args[2]);

		//reads input board position
		char[] board = FileUtil.readInput(args[0]);

		ABGame abg = new ABGame();
		MorrisGameBoard res = abg.alphaBetaMidEndGame(depth,board,Integer.MIN_VALUE,Integer.MAX_VALUE,true);

		System.out.println("Board Position:"+ new String(res.board));
		System.out.println("Position evaluated by static estimation:"+res.count);
		System.out.println("Alpha Beta estimate:"+res.estimate);

		FileUtil.writeToFile(args[1],res.board);
	}
}
