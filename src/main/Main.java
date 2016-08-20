package main;

import engine.*;
import java.util.Scanner;

import lucid.LucidMoveGenerator;
import lucid.LucidMoveMaker;
import lucid.LucidMoveValidator;

public class Main {
	public static void main(String[] args) {
		MoveValidator validator = new LucidMoveValidator();
		MoveMaker moveMaker = new LucidMoveMaker();
		LucidMoveGenerator moveGenerator = new LucidMoveGenerator();
		PositionAnalyzer analyzer = new CompositePositionAnalyzer(
				new TakeHangingPieceAnalyzer(moveGenerator),
				new FindMoveThatDoesntHangPiece(moveGenerator),
				new PickAnyMoveAnalyzer(moveGenerator));
		VirtualBoard board = new VirtualBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		
		Scanner scanner = null;
		try { 
			scanner = new Scanner(System.in);

		while(true){
			System.out.print("> ");
			String command = scanner.nextLine();
			if (command.toLowerCase() == "fen"){
				System.out.println(board.getFEN());
			} else if (validator.isValidMove(command, board)){
				moveMaker.makeMove(command, board);
				String move = analyzer.performAnalysis(board).getBestMove();
				System.out.println("trying to play move " + move);
				moveMaker.makeMove(move, board);
				System.out.println(move);
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
