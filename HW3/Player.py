# This class houses the player object
# Data such as pieces are housed here in addition to the win condition checking function

class Player(object):

    def __init__(self, piece):
        self.piece = piece

    def getPiece(self):
        return self.piece

    def addMove(self, moves, move, board):
        if move not in moves:
            moves.append(move)
            return board.addMove(move, self.piece)
        else:
            return "Player"

    def checkForWin(self, board):
        if board.checkHWin(self.piece) or board.checkVWin(self.piece) or board.checkDWin(self.piece):
            return True
        return False
        




