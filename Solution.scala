import hw.tictactoe._

class Game(turn: Player, dim : Int,board :Map[(Int,Int),Player]) extends GameLike[Game] { 
	val x: Player = X
	val o: Player = O

def getTurn(): Player = turn
//maybe print the game class

def isFinished (): Boolean = {
	//base case is a full board and no one has won
	// we might have a case where the board is full and some one has won the game
	//val boardtoList= ((board.map((case (k,v) => (k,v)))).toList).size
	//Step 1: Check if someone has one
	if(HorizontalCheck(x,o,0,0)||
		HorizontalCheck(o,x,0,0)||
		VerticalCheck(x,o,0,0)||
		VerticalCheck(o,x,0,0)||
		DiagonalCheck(x,o,0,0)||
		DiagonalCheck(o,x,0,0)||
		antiDiagonal(x,o,0,(dim-1))||
		antiDiagonal(o,x,0,(dim-1))
		){true}
	else (board.size == (dim*dim))// true// (VerticalFull(0,0) && HorizontalFull(0,0)) {true}
	//else false
	//if(board.size == (dim*dim)) true
		
}//closing is Finished

def HorizontalCheck(team: Player, opponent:Player, row: Int, col: Int ): Boolean = {
if(row == dim) false
else if(col==(dim)) true//HorizontalCheck(team,opponent,row+1,0)
else if(!board.contains(row,col)) {
						HorizontalCheck(team,opponent,row+1,0)
}
else if(board.get(row,col)== Some(opponent)) {
						HorizontalCheck(team,opponent,row+1,0)
}
//when you find an empty or opponent piece move to the next row else move up a column
else HorizontalCheck(team,opponent,row,col+1)
//else false
}

def VerticalCheck(team: Player, opponent:Player, row: Int, col: Int ): Boolean = {
if(col== dim) false
else if(row==(dim)) true
else if(!board.contains(row,col)) {
						VerticalCheck(team,opponent,0,col+1)
}
else if(board.get(row,col)== Some(opponent)) {
						VerticalCheck(team,opponent,0,col+1)
}
else VerticalCheck(team,opponent,row+1,col)
}

def DiagonalCheck(team: Player, opponent:Player, row: Int, col: Int ): Boolean = {
if(col == (dim)||row ==(dim)) true
else if(!board.contains(row,col)) false
else if(board.get(row,col)== Some(opponent)) false
else DiagonalCheck(team,opponent,row+1,col+1)
}


//antidiagonal 
//why is this always returning false
def antiDiagonal(team: Player, opponent:Player, row: Int, col: Int ): Boolean = {
if((col == (-1))||(row ==(dim))) true
else if(!board.contains(row,col)) false
else if(board.get(row,col)== Some(opponent)) false
else antiDiagonal(team,opponent,row+1,col-1)
}

/* Assume that isFinished is true */
def getWinner(): Option[Player] = {
	//when x wins
	if(HorizontalCheck(x,o,0,0)||
		VerticalCheck(x,o,0,0)||
		DiagonalCheck(x,o,0,0)||
		antiDiagonal(x,o,0,(dim-1))) {
		Some(X)
	}
	//when y wins
	else if(HorizontalCheck(o,x,0,0)||
		VerticalCheck(o,x,0,0)||
		DiagonalCheck(o,x,0,0)||
		antiDiagonal(o,x,0,(dim-1)))
		{
		Some(O)
	}
	//when no one wins
	//if(VerticalFull(0,0)&&HorizontalFull(0,0)) {
	//	None
	//}
	else None
}//closing getWinner

def nextBoards(): List[Game] = {
    //have to switch turn to the next persons
 	if(board.size==(dim*dim)) Nil

	else{
		val possibleOutcomes= boardHelper(0,0)
		turn match{
			case X=> makingthelist(possibleOutcomes,O)
					
			case O=> makingthelist(possibleOutcomes,X)
		}
	
	}//closing else
}//closing if

//have to make a list of all the possible
def makingthelist(possiblecoordinates : List[(Int,Int)],nextturn: Player):List[Game]={
	possiblecoordinates match{
		case Nil => Nil
		case h::t => {
					Solution.createGame(nextturn,dim,(board ++ (Map(((h:(Int,Int)),turn) )))):: makingthelist(t,turn)
					}//closing h::t
	}//closing match case
}

//returns a list of tuples of the possible combinations
//all these codes are not working in all the rows
def boardHelper(row: Int, col: Int): List[(Int,Int)]={
	if(row==dim) Nil
	//else if((col== dim) && (row==(dim))) Nil
	else if(col==(dim)) boardHelper(row+1,0)
	else if(!board.contains(row,col))
						(row,col) :: boardHelper(row,col+1)
	else{
		boardHelper(row,col+1)
	}
}//closing boardHelper
}//closing class game

 object Solution extends MinimaxLike {

type T = Game // T is an "abstract type member" of MinimaxLike

def createGame(turn: Player, dim: Int, board: Map[(Int, Int), Player]): Game = {
	new Game(turn,dim,board)

}


def minimax(board: Game): Option[Player] = { 
board.getTurn match {
/*
If it is Xs turn:
1. If X has won the game, return Some(X).
2. If the game is a draw, return None. (If all squares are filled
and nobody has won, then the game is a draw. However, you are
free to detect a draw earlier , if you wish .)
3. Recursively apply minimax to all the successor states of game
- If any recursive call produces X, return Some(X)
- Or , if any recursive call produces None , return None - Or, return Some(O)
The case for Os turn is similar. */

//map next board to minimax of the board
	case X => //If it is Xs turn:
			{
				if (board.getWinner()==Some(X)) Some(X)
          else if(board.isFinished && (board.getWinner == None) )  None

          else
          {
             val res = board.nextBoards.map(p => minimax(p))
             if (!res.filter(_.equals(Some(X))).isEmpty) Some(X)
             else if (!res.filter(_.equals(None)).isEmpty) None
             else Some(O)
            
           }//closing else
		}//closing case X

	case O =>
	{
		if (board.getWinner()==Some(O))
             	Some(O)
             	//case when no player has won
          else if(board.isFinished && board.getWinner == None) 
             None
              else
          		{
           	 val res2 = board.nextBoards.map(p=> minimax(p))
           	 if (!res2.filter(_.equals(Some(O))).isEmpty) Some(O)
       		 else if (!res2.filter(_.equals(None)).isEmpty) None
             else Some(X)
             
           }
	}
}
//make game a case class that will create a equals method to compare.
}//closing minmax
}//closing solutions object