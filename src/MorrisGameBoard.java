public class MorrisGameBoard {
	int count;
	int estimate;
	char[] board;


	public MorrisGameBoard(){
		this.count = 0;
		this.estimate = 0;
		this.board = new char[20];
	}

	public MorrisGameBoard(int estimate, int count, char[] board) {
		this.count = count;
		this.estimate = estimate;
		this.board = board;
	}

}
