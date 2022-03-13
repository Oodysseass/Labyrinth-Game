
public class Player {

	protected int playerId;
	protected String name;
	protected Board board;
	protected int score;
	protected int x ;
	protected int y;
	protected int playerTileId;			//Id of the player's current tile
	
	public Player() {
		playerId = 0;
		name = "";
		board = new Board();
		x = 0;
		y = 0;
		playerTileId = 0;
	}
	
	public Player(int playerId, String name, Board board, int score, int x, int y, int playerTileId) {
		this.playerId = playerId;
		this.name= name;
		this.board = new Board(board);
		this.score = score;
		this.x = x;
		this.y = y;
		this.playerTileId = playerTileId;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getPlayerTileId() {
		return playerTileId;
	}

	public void setPlayerTileId(int playerTileId) {
		this.playerTileId = playerTileId;
	}

	public int[] move(int id, Board gameBoard, int opponentPos) {
		int[] move = new int[4];									//we will return this move
		int choice;
		Tile[] tiles = new Tile[gameBoard.getN() * gameBoard.getN()];
		tiles = gameBoard.getTiles();
		while(true) {												//Randomly choosing direction
			choice = (int) (Math.random() * 10) % 8;			
			if(choice % 2 == 1) {
				if(choice == 1 && !tiles[id].isUp())
					break;
				else if(choice == 3 && !tiles[id].isRight())
					break;
				else if(choice == 5 && !tiles[id].isDown())
					break;
				else if(choice == 7 && !tiles[id].isLeft())
					break;
				
			}
		}
		switch(choice) {
			case 1:
				System.out.println(getName() + " is moving up");
				move[0] = id + board.getN();
				break;
			case 3:
				System.out.println(getName() + " is moving right");
				move[0] = id + 1;
				break;
			case 5:
				System.out.println(getName() + " is moving down");
				move[0] = id - board.getN();
				break;
			case 7:
				System.out.println(getName() + " is moving left");
				move[0] = id - 1;
				break;
		}
		move[1] = tiles[move[0]].getX();
		move[2] = tiles[move[0]].getY();
		move[3] = tiles[move[0]].getTilesSupplyId();
		return move;
	}
	
	void statistics() {}
	
	void setLife(boolean life) {}
	
	boolean isLife() {
		return true;
	}
	
}
