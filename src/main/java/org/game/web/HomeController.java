package org.game.web;

import org.game.model.StateData;
import org.game.service.TicTacToe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value = "/submitNewState", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<StateData> getNewState(@RequestBody StateData stateData) {

		StateData newStateData = TicTacToe.doGameAction(stateData);

		return new ResponseEntity<>(newStateData, HttpStatus.OK);
	}

}
