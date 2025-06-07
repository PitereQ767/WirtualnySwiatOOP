class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    def GetX(self):
        return self.x
    def GetY(self):
        return self.y
    def SetY(self, y):
        self.y = y
    def SetX(self, x):
        self.x = x
    def __eq__(self, other):
        return isinstance(other, Point) and self.x == other.x and self.y == other.y

    def __iter__(self):
        return iter((self.x, self.y))