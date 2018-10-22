# The game class houses all the rules for the game in addition to
# the players and board

import Player
import Board

class Game(object):
    def __init__(self):
        self.board = Board.Board()
        self.players = [Player.Player('X'), Player.Player('O')]

    def run(self):
        while True:
            winner = ''
            i = 0
            self.board.resetBoard()
            moves = []
            self.board.printBoard()
            while ' ' in self.board.board:
                print("Turn ", i + 1, ": Player ", (i % 2) + 1, "(",self.players[i%2].getPiece(),") choose your move: ")
                while True:
                    move = input()
                    move = move.split(" ")
                    if(len(move) != 2):
                        print("Invalid move, try again: ")
                        continue
                    else:
                        move = [int(move[0]), int(move[1])]
                        action = self.players[i%2].addMove(moves, move, self.board)
                        if action == "Player":
                            print("Invalid move, position already taken, try again:")
                            continue
                        elif action == "Board":
                            print("Invalid move, outside board, try again:")
                            continue
                        else:
                            break
                self.board.printBoard()
                if self.players[i%2].checkForWin(self.board):
                        winner = "Player " + str(i%2 + 1)
                        break
                i+=1
            if winner != '':
                print(winner, " wins! Play again? (y/n): ")
            else:
                print("Tie Game! Play again? (y/n): ")
            i = input()
            if i == 'n':
                print("Goodbye")
                break






