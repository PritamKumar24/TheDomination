package com.thedomination.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JOptionPane;

import com.thedomination.model.GameModel;

/**
 * 
 * @author Manpreet Singh
 *
 */
public class SaveGameFile {

	private static final long serialVersionUID = 1L;

	/**
	 * Method to save current game to disk.
	 * 
	 * @param gameModel model of game
	 * @return isGameSaved if the game is saved or not
	 */
	public boolean saveGame(GameModel gameModel, String fileName1) {
		boolean isGameSaved = false;
		ObjectOutputStream output = null;
		try {
			String fileName = fileName1;
			output = new ObjectOutputStream(new FileOutputStream("./save/" + fileName));
			output.writeObject(gameModel);
			isGameSaved = true;

			System.out.println("Game has been successfully saved to >>" + fileName + "<< file");
		}

		catch (NotSerializableException exception) {
			System.out.println("exception");
			exception.printStackTrace();
		}

		catch (IOException e) {
			isGameSaved = false;
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isGameSaved;
	}
}
