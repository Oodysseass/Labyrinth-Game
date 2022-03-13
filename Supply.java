
public class Supply {
	private int supplyId;
	private int x;
	private int y;
	private int supplyTileId;
	
	public Supply(){
		supplyId = 0;
		x = 0;
		y = 0;
		supplyTileId = 0;
	}
	
	public Supply(int supplyId, int x, int y, int supplyTileId) {
		this.supplyId = supplyId;
		this.x = x;
		this.y = y;
		this.supplyTileId = supplyTileId;
	}
	
	public Supply(Supply supply) {
		supplyId = supply.getSupplyId();
		x = supply.getX();
		y = supply.getY();
		supplyTileId = supply.getSupplyTileId();
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
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

	public int getSupplyTileId() {
		return supplyTileId;
	}

	public void setSupplyTileId(int supplyTileId) {
		this.supplyTileId = supplyTileId;
	}
	
}
