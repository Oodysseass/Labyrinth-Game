
public class Tile {
	private int tileId;						//Number of tile
	private int x;							//Coordinates of tile
	private int y;
	private boolean up;						//"Possession" of walls
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean haveSupply;				//Boolean for whether there is or not a supply on the tile
	private int tilesSupplyId;				//The id of the supply, should the tile have one
	private int numberOfWalls;				//The number of the walls the tile have
	private int tilesPlayerId;				//The player's Id that is one the tile, should someone be on the tile

	public Tile() {
		tileId = 0;
		x = 0;
		y = 0;
		up = false;
		down = false;
		left = false;
		right = false;
		haveSupply = false;
		tilesSupplyId = 0;
		numberOfWalls = 0;
		tilesPlayerId = -1;
	}
	
	public Tile(int tileId, int x, int y, boolean up, boolean down, boolean left, boolean right, boolean haveSupply, 
				int tilesSupplyId, int numberOfWalls, int tilesPlayerId) {
		this.tileId = tileId;
		this.x = x;
		this.y = y;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.haveSupply = haveSupply;
		this.tilesSupplyId = tilesSupplyId;
		this.numberOfWalls = numberOfWalls;
		this.tilesPlayerId = tilesPlayerId;
	}
	
	public Tile(Tile tile) {
		tileId = tile.getTileId();
		x = tile.getX();
		y = tile.getY();
		up = tile.isUp();
		down = tile.isDown();
		left = tile.isLeft();
		right = tile.isRight();
		haveSupply = tile.isHaveSupply();
		tilesSupplyId = tile.getTilesSupplyId();
		numberOfWalls = tile.getNumberOfWalls();
		tilesPlayerId = tile.getTilesPlayerId();
	}

	public int getTileId() {
		return tileId;
	}

	public void setTileId(int tileId) {
		this.tileId = tileId;
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

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	public boolean isHaveSupply() {
		return haveSupply;
	}

	public void setHaveSupply(boolean haveSupply) {
		this.haveSupply = haveSupply;
	}
	
	public int getTilesSupplyId() {
		return tilesSupplyId;
	}

	public void setTilesSupplyId(int tilesSupplyId) {
		this.tilesSupplyId = tilesSupplyId;
	}

	public int getNumberOfWalls() {
		return numberOfWalls;
	}

	public void setNumberOfWalls(int numberOfWalls) {
		this.numberOfWalls = numberOfWalls;
	}

	public int getTilesPlayerId() {
		return tilesPlayerId;
	}

	public void setTilesPlayerId(int tilesPlayerId) {
		this.tilesPlayerId = tilesPlayerId;
	}

}
