import hw.tictactoe._
import Solution._
class TestSuite extends org.scalatest.FunSuite {

	// test ("testing creategame with is finished")
	// {
	// 	val tempMap=Map(((0,0),X),((0,1),X),((0,2),O),((1,0),X),((1,1),O),((1,2),X),((1,1),O),((2,0),O),((2,1),X),((2,2),O))
	// 	assert(Solution.createGame(X,3,tempMap).isFinished == true)
	// }

	// val tempmap1=Map((0,0)->,(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2))
	// val tempmap2=Map((0,0)->,(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2))
	// val tempmap3=Map((0,0)->,(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2))
	val tempmap4=Map((0,0)->X,(0,1)->X,(1,1)->O,(2,0)->O)
	val tempmap5=Map((0,0)->X,(0,1)->O,(0,2)->X,(1,0)->X,(1,1)->O,(2,0)->O,(2,2)->X)
	//no winner
	val tempmap6=Map((0,0) -> X,(1,0) -> X, (0,1) -> O, (1,1) -> O,(0,2)->X,(1,2)->O,(2,0)->O,(2,1)->X,(2,2)->O)
	//val tempmap=Map((0,0)->,(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2))
	

	//NOT WORKING 
	test("test isFinished to be false for 3*3"){
	assert((Solution.createGame(X,3,Map((0,0) -> X,(1,0) -> O, (0,1) -> X, (2,2) -> O)).isFinished) == false)
	//println(VerticalFull(0,0) && HorizontalFull(0,0))
	}

	//it is always returning false why?
	test("test isFinished to be true for 3*3"){
	assert((Solution.createGame(X,3,Map((0,0) -> X,(1,0) -> X, (0,1) -> O, (1,1) -> O,(0,2)->X,(1,2)->O,(2,0)->X,(2,1)->O,(2,2)->O)).isFinished) == true)
	}

	test("test getWinner to be true for 3*3- when there is no winner on full board"){
	assert((Solution.createGame(X,3,tempmap6).getWinner) == None)
	}

	test("test getWinner to be true for 3*3 winner is O in first row"){
	assert((Solution.createGame(X,3,Map((0,0)->O,(0,1)->O,(0,2)->O)).HorizontalCheck(O,X,0,0)) == true)
	}

	test("test getWinner to be true for 3*3 winner is O in second row"){
	assert((Solution.createGame(X,3,Map((1,0)->O,(1,1)->O,(1,2)->O)).HorizontalCheck(O,X,0,0)) == true)
	}

	test("test getWinner to be true for 3*3 winner is O in third row"){
	assert((Solution.createGame(X,3,Map((2,0)->O,(2,1)->O,(2,2)->O)).HorizontalCheck(O,X,0,0)) == true)
	}

	test("test getWinner to be true for 3*3 winner is O in first column"){
	assert((Solution.createGame(X,3,Map((0,0)->O,(1,0)->O,(2,0)->O)).VerticalCheck(O,X,0,0)) == true)
	}

	test("test getWinner to be true for 3*3 winner is O in second column"){
	assert((Solution.createGame(X,3,Map((0,1)->O,(1,1)->O,(2,1)->O)).VerticalCheck(O,X,0,0)) == true)
	}

	test("test getWinner to be true for 3*3 winner is O in third column"){
	assert((Solution.createGame(X,3,Map((0,2)->O,(1,2)->O,(2,2)->O)).VerticalCheck(O,X,0,0)) == true)
	}

	test("test diagonal to be true for 3*3 winner is O in diagonal"){
	assert((Solution.createGame(X,3,Map((0,0)->O,(1,1)->O,(2,2)->O)).DiagonalCheck(O,X,0,0)) == true)
	}

	// not WORKING always returning false
	test("test getWinner to be true for 3*3 winner is O in antidiagonal"){
	assert((Solution.createGame(X,3,Map((0,2)->O,(1,1)->O,(2,0)->O)).antiDiagonal(O,X,0,2)) == true)
	}
//false cases test
	test("test false HorizontalCheck for 3*3 winner is O in first row"){
	assert((Solution.createGame(X,3,Map((0,0)->X,(0,1)->O,(0,2)->O)).HorizontalCheck(O,X,0,0)) == false)
	}

	test("test false HorizontalCheck for 3*3 winner is O in second row"){
	assert((Solution.createGame(X,3,Map((1,0)->X,(1,1)->O,(1,2)->O)).HorizontalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in third row"){
	assert((Solution.createGame(X,3,Map((2,0)->X,(2,1)->O,(2,2)->O)).HorizontalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in first column"){
	assert((Solution.createGame(X,3,Map((0,0)->X,(1,0)->O,(2,0)->O)).VerticalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in second column"){
	assert((Solution.createGame(X,3,Map((0,1)->X,(1,1)->O,(2,1)->O)).VerticalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in third column"){
	assert((Solution.createGame(X,3,Map((0,2)->X,(1,2)->O,(2,2)->O)).VerticalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in diagonal"){
	assert((Solution.createGame(X,3,Map((0,0)->X,(1,1)->O,(2,2)->O)).DiagonalCheck(O,X,0,0)) == false)
	}

	test("test false getWinner to be true for 3*3 winner is O in antidiagonal"){
	assert((Solution.createGame(X,3,Map((0,2)->X,(1,1)->O,(2,0)->O)).antiDiagonal(O,X,0,2)) == false)
	}

	test("test getWinner to be true for 3*3 winner is O in diagonal get some o"){
	assert((Solution.createGame(X,3,Map((0,0)->O,(1,1)->O,(2,2)->O)).getWinner) == Some(O))
	}

	test("test getWinner to be true for 3*3 winner is O in diagonal get some x "){
	assert((Solution.createGame(X,3,Map((0,0)->X,(1,1)->X,(2,2)->X)).getWinner) == Some(X))
	}

	test("test getWinner for None "){
	assert((Solution.createGame(X,3,tempmap6).getWinner) == None)
	}

	test("nextBoard on a full board"){
		assert(Solution.createGame(O,3,tempmap6).nextBoards == Nil)
	}

	test("test nextBoard"){
	assert((Solution.createGame(X,4,Map((0,0)->X)).nextBoards.length) == 15)
	}
	
	test("Minimax for winner to be O"){
		assert((minimax(Solution.createGame(O,3,tempmap5)))== Some(O))
	}

	test("minimax for X to win"){
		assert((minimax(Solution.createGame(X,3,tempmap4)))== Some(X))
	}

	test("minimax for O to win ON THE SAME BOARD "){
		assert((minimax(Solution.createGame(O,3,tempmap4)))== Some(O))
	}
 }