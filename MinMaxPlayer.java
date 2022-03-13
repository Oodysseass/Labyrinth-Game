import java.util.ArrayList;

public class MinMaxPlayer extends Player{
	private ArrayList<Integer> path;
	private boolean life;
	
	static int opponentDistance = 0, artifactDistance = 0, round = -1;			//distances from opponent and artifact
	
	public MinMaxPlayer() {
		super();
		path = new ArrayList<Integer>();
	}
	
	public MinMaxPlayer(int playerId, String name, Board board, int score, int x, int y, int playerTileId, int startingSize, boolean life) {
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
						setPlayerTileId(currentPos);						//We return the player to it's current position
						setX(tiles[currentPos].getX());
						setY(tiles[currentPos].getY());
						return result;										//And we keep the result from previous tiles
					}
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
	
	public int chooseMinMaxMove(Node root) {
		int move = 0;
		for(int i = 0; i < root.children.size(); i++) {
			double minDifference = root.children.get(i).children.get(0).nodeEvaluation; 
			for(int j = 1; j < root.children.get(i).children.size(); j++) {
				if(root.children.get(i).children.get(j).nodeEvaluation < minDifference) {
						minDifference = root.children.get(i).children.get(j).nodeEvaluation;
						root.children.get(i).nodeEvaluation = minDifference;
				}
			}
		}
		for(int i = 1; i < root.children.size(); i++) {
			double maxDifference = root.children.get(0).nodeEvaluation; 
			move = root.children.get(i).nodeMove[0];
			if(root.children.get(i).nodeEvaluation > maxDifference) {
				maxDifference = root.children.get(i).nodeEvaluation;
				move = root.children.get(i).nodeMove[2];
			}
		}
		return move;
	}
	
	public void createMySubtree(int currentPos, int opponentCurrentPos, Node root, int depth) {
		for(int depthMeter = 1; depthMeter < 2; depthMeter++) {
			for(int dice = 1; dice < 8; dice += 2) {
				int numOfMovesChild = availableMovements(opponentCurrentPos, board.getTiles());		//numOfChildrenForChild = opponentAvailableMoves
				Board boardChild = new Board(root.getNodeBoard());
				Tile[] tiles = new Tile[board.getN() * board.getN()];
				tiles = boardChild.getTiles();												//we make all the changes on child's board.tiles
				int[] nodeMove = new int[3];
				double nodeEvaluation = evaluate(currentPos, dice, tiles);
				if(nodeEvaluation != -10) {								//that means its not a wall and its available
					if(dice == 1) {
						tiles[currentPos + board.getN()].setTilesPlayerId(0);
						tiles[currentPos].setTilesPlayerId(-1);
						nodeMove[0] = tiles[currentPos + board.getN()].getX();				//we simulate the making of the movement
						nodeMove[1] = tiles[currentPos + board.getN()].getY();
					}
					if(dice == 3) {
						tiles[currentPos + 1].setTilesPlayerId(0);
						tiles[currentPos].setTilesPlayerId(-1);
						nodeMove[0] = tiles[currentPos + 1].getX();
						nodeMove[1] = tiles[currentPos + 1].getY();
					}
					if(dice == 5) {
						tiles[currentPos - board.getN()].setTilesPlayerId(0);
						tiles[currentPos].setTilesPlayerId(-1);
						nodeMove[0] = tiles[currentPos - board.getN()].getX();
						nodeMove[1] = tiles[currentPos - board.getN()].getY();
					}
					if(dice == 7) {
						tiles[currentPos - 1].setTilesPlayerId(0);
						tiles[currentPos].setTilesPlayerId(-1);
						nodeMove[0] = tiles[currentPos - 1].getX();
						nodeMove[1] = tiles[currentPos - 1].getY();
					}
					nodeMove[2] = dice;
					Node child = new Node(root, numOfMovesChild, root.nodeDepth + depthMeter,
							nodeMove, boardChild, nodeEvaluation);
					root.children.add(child);												//we make the child with all the info and add it to the root/parent
					createOpponentSubtree(nodeMove[1] * board.getN() + nodeMove[0], opponentCurrentPos,
							child, child.getNodeDepth() + 1, child.getNodeEvaluation());
				}
			}
		}
	}
	
	public void createOpponentSubtree(int currentPos, int opponentCurrentPos, Node parent, int depth, double parentEval) {
		for(int dice = 1; dice < 8; dice += 2) {												//pretty much the same as in mySubtree
			int numOfMovesChild = availableMovements(currentPos, board.getTiles());
			Board boardChild = new Board(parent.getNodeBoard());
			Tile[] tiles = new Tile[board.getN() * board.getN()];
			tiles = boardChild.getTiles();
			int[] nodeMove = new int[3];
			double nodeEvaluation = evaluateMinotaur(opponentCurrentPos, dice, tiles);
			if(nodeEvaluation != -10) {						//but we call minotaurEvaluation and change opponentCurrentPos
				if(dice == 1) {
					tiles[opponentCurrentPos + board.getN()].setTilesPlayerId(1);
					tiles[opponentCurrentPos].setTilesPlayerId(-1);
					nodeMove[0] = tiles[opponentCurrentPos + board.getN()].getX();
					nodeMove[1] = tiles[opponentCurrentPos + board.getN()].getY();
				}
				if(dice == 3) {
					tiles[opponentCurrentPos + 1].setTilesPlayerId(1);
					tiles[opponentCurrentPos].setTilesPlayerId(-1);
					nodeMove[0] = tiles[opponentCurrentPos + 1].getX();
					nodeMove[1] = tiles[opponentCurrentPos + 1].getY();
				}
				if(dice == 5) {
					tiles[opponentCurrentPos - board.getN()].setTilesPlayerId(1);
					tiles[opponentCurrentPos].setTilesPlayerId(-1);
					nodeMove[0] = tiles[opponentCurrentPos - board.getN()].getX();
					nodeMove[1] = tiles[opponentCurrentPos - board.getN()].getY();
				}
				if(dice == 7) {
					tiles[opponentCurrentPos - 1].setTilesPlayerId(1);
					tiles[opponentCurrentPos].setTilesPlayerId(-1);
					nodeMove[0] = tiles[opponentCurrentPos - 1].getX();
					nodeMove[1] = tiles[opponentCurrentPos - 1].getY();
				}
				nodeMove[2] = dice;
				Node child = new Node(parent, numOfMovesChild, parent.nodeDepth + 1, nodeMove,
						boardChild, parent.nodeEvaluation - nodeEvaluation);
				parent.children.add(child);
			}
		}
	}
	
	public int getNextMove(int currentPos, Tile[] tiles, int opponentCurrentPos) {
		int move = 0;
		//HashMap<Integer, Double> movesEvaluation = new HashMap<Integer, Double>(4);
		artifactDistance = 0;
		opponentDistance = 0;
		if(playersDistance(currentPos, opponentCurrentPos, tiles)) {					//checking visibility
			int numOfMoves = availableMovements(currentPos, board.getTiles());			//numberOfChildrenForRoot = availableMoves
			Node root = new Node(numOfMoves, 0, board);
			createMySubtree(currentPos, opponentCurrentPos, root, 2);
			move = chooseMinMaxMove(root);
		}
		else {																			//if there is no visibility of minotaur we use heuristicplayer's move
			int dice;
			double max = -10;
			for(dice = 1; dice < 8; dice += 2) {
				if(evaluate(currentPos, dice, tiles) > max) {
					max = evaluate(currentPos, dice, tiles);
					move = dice;
				}
			}
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
		if(artifactDistance == 1)			//either just an artifact was next to him or an artifact and Minotaur two tiles behind
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
		int choice = getNextMove(id, tiles, opponentPos);						//and instead of creating a new tiles at each function, we give it as an argument
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
	
	public boolean playersDistance(int theseusTile, int minotaurTile, Tile[] tiles) {				//checking if players have visible distance of each other
		if(minotaurTile % board.getN() == theseusTile % board.getN()) {								//they are on the same column
			for(int i = 1; i < 4; i++) {
				if(theseusTile + i * board.getN() > board.getN() * board.getN() - 1 || theseusTile - i * board.getN() < 0)
					break;
				if(theseusTile + i * board.getN() == minotaurTile && !tiles[minotaurTile].isDown())
					return true;
				else if(theseusTile - i * board.getN() == minotaurTile && !tiles[minotaurTile].isUp())
					return true;
			}
		}
		else if(minotaurTile / board.getN() == theseusTile / board.getN()) {					//they are on the same row
			for(int i = 1; i < 4; i++) {
				if(theseusTile - i < 0)
					break;
				if(theseusTile + i == minotaurTile && !tiles[minotaurTile].isLeft())
					return true;
				else if(theseusTile - i == minotaurTile && !tiles[minotaurTile].isRight())
					return true;
			}
		}
		else if(minotaurTile == theseusTile + board.getN() + 1) {								//up right with no walls
			if(tiles[theseusTile].isRight() && tiles[minotaurTile].isLeft())
				return false;
			else if(tiles[theseusTile].isUp() && tiles[minotaurTile].isDown())
				return false;
			else if(tiles[theseusTile].isRight() && tiles[theseusTile].isUp())
				return false;
			else if(tiles[minotaurTile].isDown() && tiles[minotaurTile].isLeft())
				return false;
			else 
				return true;
		}
		else if(minotaurTile == theseusTile - board.getN() + 1) {								//down right no walls
			if(tiles[theseusTile].isRight() && tiles[minotaurTile].isLeft())
				return false;
			else if(tiles[theseusTile].isDown() && tiles[minotaurTile].isUp())
				return false;
			else if(tiles[theseusTile].isRight() && tiles[theseusTile].isDown())
				return false;
			else if(tiles[minotaurTile].isUp() && tiles[minotaurTile].isLeft())
				return false;
			else 
				return true;
		}
		else if(minotaurTile == theseusTile + board.getN() - 1) {									//etc, etc
			if(tiles[theseusTile].isLeft() && tiles[minotaurTile].isRight())
				return false;
			else if(tiles[theseusTile].isUp() && tiles[minotaurTile].isDown())
				return false;
			else if(tiles[theseusTile].isLeft() && tiles[theseusTile].isUp())
				return false;
			else if(tiles[minotaurTile].isDown() && tiles[minotaurTile].isRight())
				return false;
			else 
				return true;
		}
		else if(minotaurTile == theseusTile - board.getN() - 1) {
			if(tiles[theseusTile].isLeft() && tiles[minotaurTile].isRight())
				return false;
			else if(tiles[theseusTile].isDown() && tiles[minotaurTile].isUp())
				return false;
			else if(tiles[theseusTile].isLeft() && tiles[theseusTile].isDown())
				return false;
			else if(tiles[minotaurTile].isUp() && tiles[minotaurTile].isRight())
				return false;
			else 
				return true;
		}
		return false;
	}
	
	public int availableMovements(int currentPos, Tile[] tiles) {
		int moves = 0;
		if(!tiles[currentPos].isUp())
			moves++;
		if(!tiles[currentPos].isRight())
			moves++;
		if(!tiles[currentPos].isDown())
			moves++;
		if(!tiles[currentPos].isLeft())
			moves++;
		return moves;
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
	
	public double evaluateMinotaur(int currentPos, int dice, Tile[] tiles) {
		double result = -1;
		if(dice == 1) {
			for(int i = 1; i < 4; i++) {
				if(currentPos + i * board.getN() > (board.getN() * board.getN()) - 1 || tiles[currentPos + i * board.getN()].isDown()) {
					if(i == 1)												//We check if the move is out of boundaries
						return -10;											//If it's on the first tile then this move should not be considered
					else
						return result;										//And we keep the result from previous tiles
				}
				if(tiles[currentPos + i * board.getN()].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							break;
						case 2:
							result += 0.5 * 0.46;
							break;
						case 3:
							result += 0.3 * 0.46;
							break;
					}
				}
				if(tiles[currentPos + i * board.getN()].getTilesPlayerId() == 0) {
					switch(i) {
						case 1:
							result += 1 * 0.54;
							break;
						case 2:
							result += 0.5 * 0.54;
							break;
						case 3:
							result += 0.3 * 0.54;
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
					else
						return result;										//And we keep the result from previous tiles
				}
				if(tiles[currentPos + i].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							break;
						case 2:
							result += 0.5 * 0.46;
							break;
						case 3:
							result += 0.3 * 0.46;
							break;
					}
				}
				if(tiles[currentPos + i].getTilesPlayerId() == 0) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							break;
						case 2:
							result += 0.5 * 0.46;
							break;
						case 3:
							result += 0.3 * 0.46;
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
					else
						return result;										//And we keep the result from previous tiles
				}
				if(tiles[currentPos - i * board.getN()].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							break;
						case 2:
							result += 0.5 * 0.46;
							break;
						case 3:
							result += 0.3 * 0.46;
							break;
					}
				}
				if(tiles[currentPos - i * board.getN()].getTilesPlayerId() == 0) {
					switch(i) {
						case 1:
							result += 1 * 0.54;
							break;
						case 2:
							result += 0.5 * 0.54;
							break;
						case 3:
							result += 0.3 * 0.54;
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
					else
						return result;										//And we keep the result from previous tiles
				}
				if(tiles[currentPos - i].isHaveSupply()) {
					switch(i) {
						case 1:
							result += 1 * 0.46;
							break;
						case 2:
							result += 0.5 * 0.46;
							break;
						case 3:
							result += 0.3 * 0.46;
							break;
					}
				}
				if(tiles[currentPos - i].getTilesPlayerId() == 0) {
					switch(i) {
						case 1:
							result += 1 * 0.54;
							break;
						case 2:
							result += 0.5 * 0.54;
							break;
						case 3:
							result += 0.3 * 0.54;
							break;
					}
				}
			}
		}
		return result;
	}
}

