package main;

import java.util.Scanner;
import java.util.Stack;

import engine.Analysis;
import engine.MoveMaker;
import engine.MoveValidator;
import engine.PositionAnalyzer;
import engine.VirtualBoard;
import engine.analyzers.BestAnalyzer;
import lucid.LucidMoveMaker;
import lucid.LucidMoveValidator;

public class Main {
	public static void main(String[] args) {
		MoveValidator validator = new LucidMoveValidator();
		MoveMaker moveMaker = new LucidMoveMaker();
		PositionAnalyzer analyzer = new BestAnalyzer(true);
		VirtualBoard board = new VirtualBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Stack<String> previous = new Stack<String>();
		
		Scanner scanner = null;
		Analysis analysis = new Analysis(board);
		try { 
			scanner = new Scanner(System.in);
		boolean on = false;
		while(true){
			System.out.print("> ");
			String command = scanner.nextLine();
			if (command.toLowerCase().equals("fen")){
				System.out.println(board.getFEN());
			} else if (command.toLowerCase().equals("on")){
				on = true;
				String move = analyzer.improveAnalysis(analysis).getBestMove();
				previous.push(board.getFEN());
				moveMaker.makeMove(move, board);
				System.out.println(move);
			} else if (command.toLowerCase().equals("go")){
				String move = analyzer.improveAnalysis(analysis).getBestMove();
				previous.push(board.getFEN());
				moveMaker.makeMove(move, board);
				System.out.println(move);
			} else if (command.toLowerCase().equals("off")){
				on = false;
			} else if (validator.isValidMove(command, board)){
				previous.push(board.getFEN());
				moveMaker.makeMove(command, board);
				if (on){
					String move = analyzer.improveAnalysis(analysis).getBestMove();
					previous.push(board.getFEN());
					moveMaker.makeMove(move, board);
					System.out.println(move);
				}
			} else if (command.toLowerCase().equals("back")){
				if (!previous.isEmpty())
					board.setFEN(previous.pop());
			} else {
				System.out.println("Invalid move '" + command + "'");
			}
		}
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}
}
