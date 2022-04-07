package astar;

public abstract class Problem {
  public static int goalIndex;
  public static int actualLine = -1;
  public static final int SECOND_PER_KM = 120;

  public static int passCost(int startIndex, int destinyIndex) {
    if(goalIndex == destinyIndex)
      return distanceg[startIndex][destinyIndex] * SECOND_PER_KM;
    if(actualLine == -1) {
      actualLine = trainLinecolors[startIndex][destinyIndex];
      return distanceg[startIndex][destinyIndex] * SECOND_PER_KM;
    }
    int nextLine = trainLinecolors[startIndex][destinyIndex];
    int additionTime = actualLine == nextLine ? 0 : 240;
    actualLine = nextLine;
    return distanceg[startIndex][destinyIndex] * SECOND_PER_KM + additionTime;
    /*
    int startTrainLine = trainLinecolors[startIndex][destinyIndex];
    int goalTrainLine = trainLinecolors[destinyIndex][startIndex];
    int additionTime = startTrainLine == goalTrainLine ? 0 : 240;
    return distanceg[startIndex][destinyIndex] * 30 + additionTime;
    */
  }

  public static int[][] distanceg = {
    {0,11,0,0,0,0,0,0,0,0,0,0,0,0},
    {11,0,9,0,0,0,0,0,11,4,0,0,0,0},
    {0,9,0,7,0,0,0,0,10,0,0,0,19,0},
    {0,0,7,0,15,0,0,16,0,0,0,0,13,0},
    {0,0,0,15,0,3,2,28,0,0,0,0,0,0},
    {0,0,0,0,3,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,2,0,0,0,0,0,0,0,0,0},
    {0,0,0,16,28,0,0,0,11,0,0,7,0,0},
    {0,11,10,0,0,0,0,11,0,0,14,0,0,0},
    {0,4,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0,14,0,0,0,0,0},
    {0,0,0,0,0,0,0,7,0,0,0,0,0,0},
    {0,0,19,13,0,0,0,0,0,0,0,0,0,5},
    {0,0,0,0,0,0,0,0,0,0,0,0,5,0}
    };
  //distance between stations in a straight line
  public static int[][] distanceh = {
    {0,11,20,27,40,43,39,28,18,10,18,30,30,32},
    {11,0,9,16,29,32,28,19,11,4,17,23,21,24},
    {20,9,0,7,20,22,19,15,10,11,21,21,13,18},
    {27,16,7,0,13,16,12,13,13,18,26,21,11,17},
    {40,29,20,13,0,3,2,21,25,31,38,27,16,20},
    {43,32,22,16,3,0,4,23,28,33,41,30,17,20},
    {39,28,19,12,2,4,0,22,25,29,38,28,13,17},
    {28,19,15,13,21,23,22,0,9,22,18,7,25,30},
    {18,11,10,13,25,28,25,9,0,13,12,12,23,28},
    {10,4,11,18,31,33,29,22,13,0,20,27,20,23},
    {18,17,21,26,38,41,38,18,12,20,0,15,35,39},
    {30,23,21,21,27,30,28,7,12,27,15,0,31,37},
    {30,21,13,11,16,17,13,25,23,20,35,31,0,5},
    {32,24,18,17,20,20,17,30,28,23,39,37,5,0}
  };

  //train line colors, where
  // 1 --> Blue
  // 2 --> Yellow
  // 3 --> Red
  // 4 --> Green
  public static int[][] trainLinecolors = {
      {0,1,0,0,0,0,0,0,0,0,0,0,0,0},
      {1,0,1,0,0,0,0,0,2,2,0,0,0,0},
      {0,1,0,1,0,0,0,0,3,0,0,0,3,0},
      {0,0,1,0,1,0,0,4,0,0,0,0,4,0},
      {0,0,0,1,0,1,2,2,0,0,0,0,0,0},
      {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
      {0,0,0,0,2,0,0,0,0,0,0,0,0,0},
      {0,0,0,4,2,0,0,0,2,0,0,4,0,0},
      {0,2,3,0,0,0,0,2,0,0,3,0,0,0},
      {0,2,0,0,0,0,0,0,0,0,0,0,0,0},
      {0,0,0,0,0,0,0,0,3,0,0,0,0,0},
      {0,0,0,0,0,0,0,4,0,0,0,0,0,0},
      {0,0,3,4,0,0,0,0,0,0,0,0,0,4},
      {0,0,0,0,0,0,0,0,0,0,0,0,4,0}
  };
}
