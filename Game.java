
public class Game {
	int round;
	
	public Game() {
		round = 0;
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		int N, S, W;
		int[] move = new int[4];
		N = 15;
		S = 4;
		W = 338;
		Board board = new Board(N, S, W);													//Gaming board
		board.createBoard();
		String[][] gameBoard = new String[board.getN() * 2 + 1][board.getN()];				//Board that we are going to print
		Player[] players = new Player[2];
		players[0] = new MinMaxPlayer(0, "Theseus", board, 0, 0, 0, 0, 4, true);				//Initializing players
		players[1] = new Player(1, "Minotaur", board, 0, board.getN() / 2, board.getN() / 2, board.getN() * board.getN() / 2);
		System.out.println("STARTING GAME");
		System.out.println();
		System.out.println("Get all the artifacts without falling into Minotaur's hands!");
		gameBoard = board.getStringRepresentation(0, board.getN() * board.getN() / 2);
		for(int i = board.getN() * 2; i > -1; i--) {
			for(int j = 0; j < board.getN(); j++) {
				if(i == 0 && j == 0) {
					System.out.print("+   +");												//We keep the entrance open for the first print
					continue;
				}
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		Tile[] tiles = new Tile[board.getN() * board.getN()];
		while(game.getRound() < 200) {
			tiles = board.getTiles();
			System.out.println("ROUND " + (game.getRound() + 1));
			System.out.println();
			System.out.println(players[game.getRound() % 2].getName() + "'s Round");
			System.out.println();
			move = players[game.getRound() % 2].move(players[game.getRound() % 2].getPlayerTileId(), board, players[1].getPlayerTileId());	//Getting next move
			if(move[0] == players[game.getRound() % 2].getPlayerTileId())								//If it's the same, it means he did not move
				System.out.println(players[game.getRound() % 2].getName() + " could not move because of the wall!");
			tiles[players[game.getRound() % 2].getPlayerTileId()].setTilesPlayerId(-1);					//We remove players old position from Tiles
			players[game.getRound() % 2].setPlayerTileId(move[0]);
			tiles[move[0]].setTilesPlayerId(players[game.getRound() % 2].getPlayerId());				//We put players' new position at Tiles
			if(players[game.getRound() % 2].getPlayerId() == 0 && move[3] != 0) {						//Checking if it's Theseus and if he has
				players[0].setScore(players[0].getScore() + 1);											//Found and artifact
				System.out.println("Theseus found artifact No." + tiles[move[0]].getTilesSupplyId());
				tiles[move[0]].setHaveSupply(false);													//We hide the artifact
				tiles[move[0]].setTilesSupplyId(0);
			}
			gameBoard = board.getStringRepresentation(players[0].getPlayerTileId(), players[1].getPlayerTileId());
			for(int i = board.getN() * 2; i > -1; i--) {												//Printing gameBoard
				for(int j = 0; j < board.getN(); j++) {
					System.out.print(gameBoard[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			if(players[0].getPlayerTileId() == players[1].getPlayerTileId()) {							//Minotaur and Theseus are on the same tile
				players[0].setLife(false);
				break;																					//It's game over
			}
			if(players[0].getScore() == board.getS())													//Theseus has found all the artifacts
				break;																					//It's game over
			if(game.getRound() % 2 == 0)
				players[0].statistics();
			System.out.println();
			game.setRound(game.getRound() + 1);
			board.setTiles(tiles);
		}
		if(players[0].getScore() == board.getS())
			System.out.println("The heroic Theseus has found all the artifacts and thus is the winner! Congratulations!");
		else if(players[0].getPlayerTileId() == players[1].getPlayerTileId())
			System.out.println("Theseus could not escape the mighty Minotaur's wrath. Mourning day for the Olympians,"
					+ " feasting day for the great monster!");
		else
			System.out.println("It's dawn already and neither of the two mythic creatures could fullfill their purpose. Let's call it a draw!");
		System.out.println();
		players[0].statistics();
		System.out.println();
		System.out.println("ENDING GAME");
	}

}
