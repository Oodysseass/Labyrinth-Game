import java.util.ArrayList;
import java.util.HashMap;

public class HeuristicPlayer extends Player{
	private ArrayList<Integer> path;
	private boolean life;
	
	static int opponentDistance = 0, artifactDistance = 0, round = -1;			//distances from 
	
	public HeuristicPlayer() {
		super();
		path = new ArrayList<Integer>();
	}
	
	public HeuristicPlayer(int playerId, String name, Board board, int score, int x, int y, int playerTileId, int startingSize, boolean life) {
		super(playerId, name, board, score, x, y, playerTileId);
		path = new ArrayList<Integer>(startingSize);
		this.life = life;
	}
	
	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}
	
	public double evaluate(int currentPos, int dice, Tile[] tiles) {
		double result = -1;
		if(dice == 1) {
			for(int i = 1; i < 4; i++) {
				if(currentPos + i * board.getN() > (board.getN() * board.getN()) - 1 || tiles[currentPos + i * board.getN()].isDown()) {
					if(i == 1)												//We check if the move is out of boundaries
						return -10;											//If it's on the first tile then this move should not be considered
					else {
						setPlayerTileId(currentPos);
						setX(tiles[currentPos].getX());
						setY(tiles[currentPos].getY());
						return result;
					}									//Otherwise we keep the result from previous tiles
				}
				setPlayerTileId(currentPos + i * board.getN());						//We move the player to the next tile
				setX(tiles[currentPos + i * board.getN()].getX());
				setY(tiles[currentPos + i * board.getN()].getY());
				if(tiles[currentPos + i * board.getN()].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							artifactDistance = 1;					//1 doesn't need a check
							break;
						case 2:
							result += 0.5 * 0.46;
							if(artifactDistance != 1)				//if it's either 0 or 3, then 2 is the nearest artifact
								artifactDistance = 2;
							break;
						case 3:
							result += 0.3 * 0.46;
							if(artifactDistance == 0)				//only if there is no other artifact
								artifactDistance = 3;
							break;
					}
				}
				if(tiles[currentPos + i * board.getN()].getTilesPlayerId() == 1) {
					switch(i) {
						case 1:
							result -= 1 * 0.54;
							opponentDistance = 1;					//There is only one opponent so we don't need to check which one is closest
							break;
						case 2:
							result -= 0.5 * 0.54;
							opponentDistance = 2;
							break;
						case 3:
							result -= 0.3 * 0.54;
							opponentDistance = 3;
							break;
					}
				}
			}
		}
		if(dice == 3) {
			for(int i = 1; i < 4; i++) {
				if(currentPos + i > (board.getN() * board.getN()) - 1 || tiles[currentPos + i].isLeft()) {
					if(i == 1)
						return -10;
					else {
						setPlayerTileId(currentPos);
						setX(tiles[currentPos].getX());
						setY(tiles[currentPos].getY());
						return result;
					}
				}
				setPlayerTileId(currentPos + i);
				setX(tiles[currentPos + i].getX());
				setY(tiles[currentPos + i].getY());
				if(tiles[currentPos + i].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							artifactDistance = 1;
							break;
						case 2:
							result += 0.5 * 0.46;
							if(artifactDistance != 1)
								artifactDistance = 2;
							break;
						case 3:
							result += 0.3 * 0.46;
							if(artifactDistance == 0)
								artifactDistance = 3;
							break;
					}
				}
				if(tiles[currentPos + i].getTilesPlayerId() == 1) {
					switch(i) {
						case 1:
							result -= 1 * 0.46;
							opponentDistance = 1;
							break;
						case 2:
							result -= 0.5 * 0.46;
							opponentDistance = 2;
							break;
						case 3:
							result -= 0.3 * 0.46;
							opponentDistance = 3;
							break;
					}
				}
			}
		}
		if(dice == 5) {
			for(int i = 1; i < 4; i++) {
				if(currentPos - i * board.getN() < 0 || tiles[currentPos - i * board.getN()].isUp()) {
					if(i == 1)
						return -10;
					else {
						setPlayerTileId(currentPos);
						setX(tiles[currentPos].getX());
						setY(tiles[currentPos].getY());
						return result;
					}
				}
				setPlayerTileId(currentPos - i * board.getN());
				setX(tiles[currentPos - i * board.getN()].getX());
				setY(tiles[currentPos - i * board.getN()].getY());
				if(tiles[currentPos - i * board.getN()].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							artifactDistance = 1;
							break;
						case 2:
							result += 0.5 * 0.46;
							if(artifactDistance != 1)
								artifactDistance = 2;
							break;
						case 3:
							result += 0.3 * 0.46;
							if(artifactDistance == 0)
								artifactDistance = 3;
							break;
					}
				}
				if(tiles[currentPos - i * board.getN()].getTilesPlayerId() == 1) {
					switch(i) {
						case 1:
							result -= 1 * 0.54;
							opponentDistance = 1;
							break;
						case 2:
							result -= 0.5 * 0.54;
							opponentDistance = 2;
							break;
						case 3:
							result -= 0.3 * 0.54;
							opponentDistance = 3;
							break;
					}
				}
			}
		}
		if(dice == 7) {
			for(int i = 1; i < 4; i++) {
				if(currentPos - i < 0 || tiles[currentPos - i].isRight()) {
					if(i == 1)
						return -10;
					else {
						setPlayerTileId(currentPos);
						setX(tiles[currentPos].getX());
						setY(tiles[currentPos].getY());
						return result;
					}
				}
				setPlayerTileId(currentPos - i);
				setX(tiles[currentPos - i].getX());
				setY(tiles[currentPos - i].getY());
				if(tiles[currentPos - i].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							artifactDistance = 1;
							break;
						case 2:
							result += 0.5 * 0.46;
							if(artifactDistance != 1)
								artifactDistance = 2;
							break;
						case 3:
							result += 0.3 * 0.46;
							if(artifactDistance == 0)
								artifactDistance = 3;
							break;
					}
				}
				if(tiles[currentPos - i].getTilesPlayerId() == 1) {
					switch(i) {
						case 1:
							result -= 1 * 0.54;
							opponentDistance = 1;
							break;
						case 2:
							result -= 0.5 * 0.54;
							opponentDistance = 2;
							break;
						case 3:
							result -= 0.3 * 0.54;
							opponentDistance = 3;
							break;
					}
				}
			}
		}
		setPlayerTileId(currentPos);								//We return the player to it's current position
		setX(tiles[currentPos].getX());
		setY(tiles[currentPos].getY());
		return result;
	}
	
	public int getNextMove(int currentPos, Tile[] tiles) {
		int dice, move = 0;
		double max = -10;
		HashMap<Integer, Double> movesEvaluation = new HashMap<Integer, Double>(4);
		artifactDistance = 0;
		opponentDistance = 0;
		for(dice = 1; dice < 8; dice += 2) {
			if(evaluate(currentPos, dice, tiles) > max) {
				max = evaluate(currentPos, dice, tiles);
				move = dice;
			}
			movesEvaluation.put(dice, evaluate(currentPos, dice, tiles));			//HashMap with every move and it's evaluation
		}
		if(artifactDistance == 0 && opponentDistance == 0) {			//that means there is nothing near us so we have to
			while(true) {												//chose a different way where to move
				move = (int) (Math.random() * 10) % 8;
				if(path.size() > 0) {
					if(move == path.get(path.size() - 4) + 4 || move == path.get(path.size() - 4) - 4)
						continue;										//we want to avoid going back and forth with 1-5, 3-7 all the time
				}
				if(move % 2 == 1) {
					if(move == 1 && !tiles[currentPos].isUp())
						break;
					else if(move == 3 && !tiles[currentPos].isRight())
						break;
					else if(move == 5 && !tiles[currentPos].isDown())
						break;
					else if(move == 7 && !tiles[currentPos].isLeft())
						break;
				}
			}
		}
		path.add(move);
		if(artifactDistance == 1)			//either just an artifact was next to him or an artifact and Minotaur to tiles behind
			path.add(1);
		else
			path.add(0);
		path.add(artifactDistance);
		path.add(opponentDistance);
		return move;
	}
	
	public int[] move(int id, Board gameBoard, int opponentPos) {
		int[] move = new int[4];									//we will return this move
		board = new Board(gameBoard);								//we make player's board equal to the game's
		Tile[] tiles = new Tile[board.getN() * board.getN()];		//and a tiles array to make all the changes until the best move
		tiles = board.getTiles();
		int choice = getNextMove(id, tiles);						//and instead of creating a new tiles at each function, we give it as an argument
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
		board.setTiles(gameBoard.getTiles());						//we used the player's tiles to calculate best move
		move[1] = tiles[move[0]].getX();							//so we set it as the game's tiles
		move[2] = tiles[move[0]].getY();
		move[3] = tiles[move[0]].getTilesSupplyId();
		return move;
	}
	
	public void statistics() {
		round++;
		System.out.println("Theseus has " + getScore() + " artifacts");
		System.out.println("Distance from nearest artifact: " + artifactDistance);
		System.out.println("Distance from Minotaur: " + opponentDistance);
		int movesUp = 0, movesRight = 0, movesDown = 0, movesLeft = 0;
		if(round == 100 || isLife() == false || getScore() == board.getS()) {
			for(int i = 0; i < path.size(); i += 4) {
				if(path.get(i) == 1)
					movesUp++;
				else if(path.get(i) == 3)
					movesRight++;
				else if(path.get(i) == 5)
					movesDown++;
				else
					movesLeft++;
			}
			System.out.println("Theseus moved up " + movesUp + " times.");
			System.out.println("Theseus moved right " + movesRight + " times.");
			System.out.println("Theseus moved down " + movesDown + " times.");
			System.out.println("Theseus moved left " + movesLeft + " times.");
		}
	}
}