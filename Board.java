
public class Board {
	private int N;
	private int S;
	private int W;
	
	private Tile[] tiles;
	private Supply[] supplies;
	
	
	public Board() {
		N = 0;
		S = 0;
		W = 0;
		tiles = new Tile[0];
		supplies = new Supply[0];
	}

	public Board(int N, int S, int W) {
		this.N = N;
		this.S = S;
		this.W = W;
	}
	
	public Board(Board board) {
		N = board.getN();
		S = board.getS();
		W = board.getW();
		tiles = new Tile[board.getN() * board.getN()];
		for(int i = 0; i < board.getN() * board.getN(); i++)
			tiles[i] = new Tile(board.tiles[i]);
		supplies = new Supply[board.getS()];
		for(int i = 0; i < board.getS(); i++)
			supplies[i] = new Supply(board.supplies[i]);
	}

	public int getN() {
		return N;
	}

	public void setN(int N) {
		this.N = N;
	}

	public int getS() {
		return S;
	}

	public void setS(int S) {
		this.S = S;
	}

	public int getW() {
		return W;
	}

	public void setW(int W) {
		this.W = W;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public Supply[] getSupplies() {
		return supplies;
	}

	public void setSupplies(Supply[] supplies) {
		this.supplies = supplies;
	}
	
	public void createTile() {
		tiles = new Tile[getN() * getN()];
		int counter = 0;
		for(int i = 0; i < getN(); i++) {
			for(int j = 0; j < getN(); j++) {									//We give every tile all possible walls
				tiles[j + i * getN()] = new Tile(j + i * getN(), j, i, (i == getN() - 1 || true), (i == 0 || tiles[j + (i - 1) * getN()].isUp()),
						(j == 0 || tiles[j - 1 + i * getN()].isRight()), (j == getN() - 1 || true), false, 0, 4, -1);
				counter += 4;
			}
		}
		while(counter > getW() || !allTilesMaxTwoWalls()) {						//Then we randomly delete some, so that each tile has max 2 walls
			for(int i = 0; i < getN(); i++) {									//and we don't have more walls than board.getW()
				for(int j = 0; j < getN(); j++) {
					do {
						int deleteWall = (int) ((Math.random() * 100) % 4);
						if(deleteWall == 0 && tiles[j + i * getN()].isUp()) {
							if(i != getN() - 1) {
								tiles[j + i * getN()].setUp(false);
								tiles[j + (i + 1) * getN()].setDown(false);
								tiles[j + i * getN()].setNumberOfWalls(tiles[j + i * getN()].getNumberOfWalls() - 1);
								tiles[j + (i + 1) * getN()].setNumberOfWalls(tiles[j + (i + 1) * getN()].getNumberOfWalls() - 1);
								counter -= 2;
							}
							else
								deleteWall++;
						}
						if(deleteWall == 1 && tiles[j + i * getN()].isDown()) {
							if(i != 0) {
								tiles[j + i * getN()].setDown(false);
								tiles[j + (i - 1) * getN()].setUp(false);
								tiles[j + i * getN()].setNumberOfWalls(tiles[j + i * getN()].getNumberOfWalls() - 1);
								tiles[j + (i - 1) * getN()].setNumberOfWalls(tiles[j + (i - 1) * getN()].getNumberOfWalls() - 1);
								counter -= 2;
							}
							else
								deleteWall++;
						}
						if(deleteWall == 2 && tiles[j + i * getN()].isLeft()) {
							if(j != 0) {
								tiles[j + i * getN()].setLeft(false);
								tiles[j - 1 + i * getN()].setRight(false);
								tiles[j + i * getN()].setNumberOfWalls(tiles[j + i * getN()].getNumberOfWalls() - 1);
								tiles[j - 1 + i * getN()].setNumberOfWalls(tiles[j - 1 + i * getN()].getNumberOfWalls() - 1);
								counter -= 2;
							}
							else
								deleteWall++;
						}
						if(deleteWall == 3 && tiles[j + i * getN()].isRight()) {
							if(j != getN() - 1) {
								tiles[j + i * getN()].setRight(false);
								tiles[j + 1 + i * getN()].setLeft(false);
								tiles[j + i * getN()].setNumberOfWalls(tiles[j + i * getN()].getNumberOfWalls() - 1);
								tiles[j + 1 + i * getN()].setNumberOfWalls(tiles[j + 1 + i * getN()].getNumberOfWalls() - 1);
								counter -= 2;
							}
						}
					} while(tiles[j + i * getN()].getNumberOfWalls() > 2);
				}
			}
		}
	}
	
	public void createSupply() {
		supplies = new Supply[getS()];
		for(int i = 0; i < getS(); i++) {
			int supplyTileId = 0;
			while(supplyTileId == 0 || supplyTileId == getN() * getN() / 2)				//We don't want for the supply to be on Theseus or Minotaur
				supplyTileId = (int) (Math.random() * 10000) % (getN() * getN());  
			supplies[i] = new Supply(i + 1, supplyTileId % getN(), supplyTileId / getN(), supplyTileId);
			tiles[supplyTileId].setHaveSupply(true);									//We set the corresponding variables in tiles
			tiles[supplyTileId].setTilesSupplyId(i + 1);
		}
	}
	
	public void createBoard() {
		createTile();
		createSupply();
	}
	
	public String[][] getStringRepresentation(int theseusTile, int minotaurTile){
		String[][] stringRepresentation = new String[getN() * 2 + 1][getN()];
		int row; 																//Because i is counting till getN() * 2 + 1 we use another variable
		int i, j;
		stringRepresentation[0][0] = new String("+---+");						//We put one more + at the two left corners
		stringRepresentation[getN() * 2][0] = new String("+---+");
		row = 0;
		for(i = 1; i < getN() * 2; i+=2) {										//Initializing first column in a different loop
			if(minotaurTile / getN() == row && minotaurTile % getN() == 0) {	//Because it may need one more |
				if(tiles[row * getN()].isHaveSupply()) {
					if(tiles[row * getN()].isRight())
						stringRepresentation[i][0] = new String("|Ms" + tiles[row * getN()].getTilesSupplyId() + "|");
					else
						stringRepresentation[i][0] = new String("|Ms" + tiles[row * getN()].getTilesSupplyId() + " ");
				}
				else if(tiles[row * getN()].isRight())
					stringRepresentation[i][0] = new String("| M |");					//We have to check for all the different cases
				else																	//such as Theseus and walls, Theseus alone,
					stringRepresentation[i][0] = new String("| M  ");					//Minotaur with a supply etc.
			}
			else if(theseusTile / getN() == row && theseusTile % getN() == 0) {
				if(tiles[row * getN()].isRight())
					stringRepresentation[i][0] = new String("| T |");
				else
					stringRepresentation[i][0] = new String("| T  ");
			}
			else {
				if(tiles[row * getN()].isHaveSupply()) {
					if(tiles[row * getN()].isRight())
						stringRepresentation[i][0] = new String("|s" + tiles[row * getN()].getTilesSupplyId() + " |");
					else
						stringRepresentation[i][0] = new String("|s" + tiles[row * getN()].getTilesSupplyId() + "  ");
				}
				else if(tiles[row * getN()].isRight())
					stringRepresentation[i][0] = new String("|   |");
				else
					stringRepresentation[i][0] = new String("|    ");
			}
			row++;
		}
		row = 1;
		for(i = 2; i < getN() * 2; i+=2) {
			if(tiles[row * getN()].isDown())
				stringRepresentation[i][0] = new String("+---+");
			else
				stringRepresentation[i][0] = new String("+   +");
			row++;
		}
		for(i = 0; i < getN() * 2 + 1; i+=getN() * 2) {
			for(j = 1; j < getN(); j++)
				stringRepresentation[i][j] = new String("---+");
		}
		row = 0;
		for(i = 1; i < getN() * 2; i+=2) {
			for(j = 1; j < getN(); j++) {
				if(minotaurTile / getN() == row && minotaurTile % getN() == j) {
					if(tiles[j + row * getN()].isHaveSupply()) {
						if(tiles[j + row * getN()].isRight())
							stringRepresentation[i][j] = new String("Ms" + tiles[j + row * getN()].getTilesSupplyId() + "|");
						else
							stringRepresentation[i][j] = new String("Ms" + tiles[j + row * getN()].getTilesSupplyId() + " ");
					}
					else if(tiles[j + row * getN()].isRight())
						stringRepresentation[i][j] = new String(" M |");
					else
						stringRepresentation[i][j] = new String(" M  ");
				}
				else if(theseusTile / getN() == row && theseusTile % getN() == j) {
					if(tiles[j + row * getN()].isHaveSupply()) {
						if(tiles[j + row * getN()].isRight())
							stringRepresentation[i][j] = new String("Ts" + tiles[j + row * getN()].getTilesSupplyId() + "|");
						else
							stringRepresentation[i][j] = new String("Ts" + tiles[j + row * getN()].getTilesSupplyId() + " ");
					}
					else if(tiles[j + row * getN()].isRight())
						stringRepresentation[i][j] = new String(" T |");
					else
						stringRepresentation[i][j] = new String(" T  ");
				}
				else {
					if(tiles[j + row * getN()].isHaveSupply()) {
						if(tiles[j + row * getN()].isRight())
							stringRepresentation[i][j] = new String(" s" + tiles[j + row * getN()].getTilesSupplyId() + "|");
						else
							stringRepresentation[i][j] = new String("s" + tiles[j + row * getN()].getTilesSupplyId() + "  ");
					}
					else if(tiles[j + row * getN()].isRight())
						stringRepresentation[i][j] = new String("   |");
					else
						stringRepresentation[i][j] = new String("    ");
				}
			}
			row++;
		}
		row = 1;
		for(i = 2; i < getN() * 2; i+=2) {
			for(j = 1; j < getN(); j++) {
				if(tiles[j + row * getN()].isDown())
					stringRepresentation[i][j] = new String("---+");
				else
					stringRepresentation[i][j] = new String("   +");
			}
			row++;
		}
		return stringRepresentation;
	}
		
	public boolean allTilesMaxTwoWalls() {							//Checks if all tiles have max two walls
		for(int i = 0; i < getN() * getN(); i++) {
			if(tiles[i].getNumberOfWalls() > 2)
				return false;
		}
		return true;	
	}
}