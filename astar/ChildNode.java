package astar;

public class ChildNode {
  private int thisIndex;
  private ChildNode father;
  private int[] nearStations;
  private int pathCost;
  private int pathCostWithH;

  // metodo(int... a)
  // metodo(1,2,4,1,4,5,3)
  public ChildNode(int thisIndex, ChildNode father) {
    this.thisIndex = thisIndex;
    this.father = father;
    // this is not the root
    if (father != null){
      this.pathCost = calculatePathCost();
      this.pathCostWithH = calculatePathCostWithH();
    }
  }

  private int calculatePathCost() {
    // cost of father + cost from father
    return father.getPathCost() + 
            Problem.passCost(father.getThisIndex(), thisIndex);
  }

  private int calculatePathCostWithH() {
    return pathCost + Problem.distanceh[thisIndex][Problem.goalIndex] * Problem.SECOND_PER_KM;
  }

  public int getThisIndex() {
    return thisIndex;
  }

  public void setThisIndex(int thisIndex) {
    this.thisIndex = thisIndex;
  }

  public ChildNode getFather() {
    return father;
  }

  public void setFather(ChildNode father) {
    this.father = father;
  }

  public int[] getNearStations() {
    return nearStations;
  }

  public void setNearStations(int[] nearStations) {
    this.nearStations = nearStations;
  }

  public int getPathCost() {
    return pathCost;
  }

  public void setPathCost(int pathCost) {
    this.pathCost = pathCost;
  }

  public int getPathCostWithH() {
    return pathCostWithH;
  }

  public void setPathCostWithH(int pathCostWithH) {
    this.pathCostWithH = pathCostWithH;
  }
}
