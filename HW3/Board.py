# This class houses the board
# If required the board size can be changed by modifying the self.board array
# Win conditions can also be changed if needed
class Board(object):
    def __init__(self):
        self.board = [' ',' ',' ',' ',' ',' ',' ',' ',' ']

    def printBoard(self):
        for i in range (0,7,3):
            print("+---+---+---+")
            print("+", self.board[i], '|', self.board[i+1], '|', self.board[i+2], '+')
        print("+---+---+---+")

    def addMove(self, move, piece):
        if move[0] < 1 or move[0] > 3 or move[1] < 1 or move[1] > 3:
            return "Board"
        else:
            i = ((move[0] - 1) * 3) + (move[1] - 1)
            self.board[i] = piece

    def resetBoard(self):
        self.board = [' ',' ',' ',' ',' ',' ',' ',' ',' ']

    def checkHWin(self, piece):
        for i in range (0, 7, 3):
            if self.board[i] == piece and self.board[i+1] == piece and self.board[i+2] == piece:
                return True
        return False

    def checkVWin(self, piece):
        for i in range (0, 3):
            if self.board[i] == piece and self.board[i+3] == piece and self.board[i+6] == piece:
                return True
        return False

    def checkDWin(self, piece):
        if self.board[0] == piece and self.board[4] == piece and self.board[8] == piece:
                return True
        if self.board[2] == piece and self.board[4] == piece and self.board[6] == piece:
                return True
        return False
