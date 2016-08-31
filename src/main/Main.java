package main;

import java.util.Scanner;
import java.util.Stack;

import engine.Analysis;
import engine.MoveMaker;
import engine.MoveValidator;
import engine.PositionAnalyzer;
import engine.VirtualBoard;
import engine.analyzers.TopLevelAlgorithm;
import lucid.LucidMoveMaker;
import lucid.LucidMoveValidator;

public class Main {
	public static void main(String[] args) {
		MoveValidator validator = new LucidMoveValidator();
		MoveMaker moveMaker = new LucidMoveMaker();
		PositionAnalyzer analyzer = new TopLevelAlgorithm(true);
		VirtualBoard board = new VirtualBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Stack<String> previous = new Stack<String>();
		Stack<String> previousMoves = new Stack<String>();
		Scanner scanner = null;
		try { 
			scanner = new Scanner(System.in);
			boolean on = false;
			while(true){
				System.out.print("> ");
				String command = scanner.nextLine();
				if (command.startsWith("load ")){
					String fen = command.substring(5);
					board = new VirtualBoard(fen);
					previous = new Stack<String>();
					previousMoves = new Stack<String>();
				} else if (command.toLowerCase().equals("fen")){
					System.out.println(board.getFEN());
				} else if (command.toLowerCase().equals("on")){
					on = true;
					go(moveMaker, analyzer, board, previous, previousMoves);
				} else if (command.toLowerCase().equals("go")){
					go(moveMaker, analyzer, board, previous, previousMoves);
				} else if (command.toLowerCase().equals("off")){
					on = false;
				} else if (validator.isValidMove(command, board)){
					previous.push(board.getFEN());
					previousMoves.push(command);
					moveMaker.makeMove(command, board);
					if (on){
						go(moveMaker, analyzer, board, previous, previousMoves);
					}
				} else if (command.toLowerCase().equals("back")){
					if (!previous.isEmpty())
						board.setFEN(previous.pop());
				} else {
					System.out.println("Invalid move '" + command + "'");
				}
			}
		} catch(Exception e){
			System.out.println(board.getFEN());
			String gameHistory = "";
			for (String move : previousMoves)
				gameHistory += move + " ";
			System.out.println(gameHistory);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	private static void go(MoveMaker moveMaker, PositionAnalyzer analyzer, VirtualBoard board, Stack<String> previous, Stack<String> previousMoves) {
		Analysis analysis = analyzer.improveAnalysis(new Analysis(board));
		String move = analysis.getBestMove();
		System.out.println("reason: " + analysis.getReasoning());
		previous.push(board.getFEN());
		previousMoves.push(move);
		moveMaker.makeMove(move, board);
		System.out.println("move: " + move);
	}
}
