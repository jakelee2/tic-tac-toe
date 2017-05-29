var GAME_MOVE = (function(){

	// JSON variable for Rest API request and response
	var stateData;
	
	// Name-constants to represent the seeds and cell contents
	var EMPTY = 0;
	var CROSS = 1;
	var NOUGHT = 2;
	
	// Name-constants to represent the various states of the game
	var PLAYING = 0;
	var DRAW = 1;
	var CROSS_WON = 2;
	var NOUGHT_WON = 3;
	
	var VALID_MOVE = 0;

	// Gets data of the selected table from back end.  The data type is JSON.
	var sendMove = function(stateData) {
		$.ajax({
			url: 'http://localhost:8080/submitNewState',
			type: 'POST',
			data: JSON.stringify(stateData),
			contentType: 'application/json; charset=utf-8',
			success: function (newStateData, xhr) {
				stateData = newStateData;
				console.log(stateData.board);
				afterMove(stateData);
			}
		});
	};
	
	var afterMove = function(stateData){
		if(stateData.errorMessage == VALID_MOVE){
			if(stateData.stateOfGame == PLAYING){
				window.sessionStorage.setItem("currentStateData", JSON.stringify(stateData));
				$('#resultMessage').html("It's "+ (stateData.player == CROSS ? "CROSS":"NOUGHT") +"'s turn. Select your move (row[1-3], column[1-3]).");
			}
			else { 
				if(stateData.stateOfGame == CROSS_WON){
					$('#resultMessage').html("CROSS(X) won the Game!");
				}
				else if(stateData.stateOfGame == NOUGHT_WON){
					$('#resultMessage').html("NOUGHT(O) won the Game!");
				}
				else if(stateData.stateOfGame == DRAW){
					$('#resultMessage').html("It's a Draw! Try again if you want!");
				}
				window.sessionStorage.removeItem("currentStateData");
				$('#moveBtn').prop('disabled', true);
			}
		}
		else {	// INVALID MOVE
			$('#resultMessage').
			html("This move at (" + (stateData.selectedRow) + ", " + (stateData.selectedColumn) + ") is not valid. Try again... <br>Still it's "+ (stateData.player == CROSS ? "CROSS":"NOUGHT") +"'s turn. Select your move (row[1-3] column[1-3]).");			
		}		
		printBoard(stateData);
	};

	var printBoard = function(stateData){
		var printContent = "";
		for (var row = 0; row < stateData.board.length; ++row) {
			for (var col = 0; col < stateData.board.length; ++col) {
				printContent = printCell(stateData.board[row][col], printContent); // print each of the cells
				if (col != stateData.board.length - 1) {
					printContent += "|";
				}
			}
			printContent += "<br>";
			if (row != stateData.board.length - 1) {
				printContent += "-------------";
				printContent += "<br>";
			}
		}
		printContent += "<br>";
		$('#printBoard').html(printContent);
	};
	
	var printCell = function(content, printContent) {
		switch (content) {
		case EMPTY:
			printContent += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			break;
		case NOUGHT:
			printContent += "&nbsp;O&nbsp;";
			break;
		case CROSS:
			printContent += "&nbsp;X&nbsp;";
			break;
		}
		return printContent;
	};
	
	var initStateData = function(){
		stateData = {
				"player":CROSS,
				"sequenceNum":0,
				"stateOfGame":PLAYING,
				"errorMessage":0,
				"selectedRow":0,
				"selectedColumn":0,
				"board":[
					[EMPTY, EMPTY, EMPTY],
					[EMPTY, EMPTY, EMPTY],
					[EMPTY, EMPTY, EMPTY]
				]
			};
		window.sessionStorage.removeItem("currentStateData");
	};

	
	var putFirstMsg = function(){
		$('#resultMessage').html("1. CROSS starts first!  NOUGHT starts after CROSS. <br/> " +
				 "2. You can restart game by clicking \"Restart\" button. <br/>" +
				 "3. It's CROSS's turn. Select row[1-3] & column[1-3], and click \"Move\" button. <br/>"
				 );			
	};

	// Return Block: Namespace for public function which can be called from outside of GAME_MOVE function
	return {

		initialize: (function(){
			initStateData();
			putFirstMsg();
			printBoard(stateData);
		})(),
		
		restart: function(){
			document.getElementById("selectRow").value = 0;
			document.getElementById("selectColumn").value = 0;
			$('#moveBtn').prop('disabled', false);
			initStateData();
			putFirstMsg();
			printBoard(stateData);
		},
		
		makeMove: function(){
			var selectedRow = document.getElementById("selectRow").value;
			var selectedColumn = document.getElementById("selectColumn").value;
			stateData = window.sessionStorage.getItem("currentStateData") == null ? stateData : JSON.parse(window.sessionStorage.getItem("currentStateData"));
			
			if( selectedRow==0 || selectedColumn==0 ){
				$('#inputAgain').html("Select Row and Column Number!!!");
			}
			else{
				$('#inputAgain').html("");
				
				stateData.selectedRow = selectedRow;
				stateData.selectedColumn = selectedColumn;				
				sendMove(stateData);
			}
		}
	};
	// End of Return Block
})();
