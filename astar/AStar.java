package astar;

import java.util.ArrayList;

import gui.components.NodeContainer;
import gui.control.MainController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class AStar extends Thread {
  private int startIndex;
  private int goalIndex;
  private MainController controller;
  private final int SPEED = 500;
  private final int SPEED2 = 100;

  public AStar(int startIndex, int goalIndex, MainController controller) {
    this.startIndex = startIndex;
    this.goalIndex = goalIndex;
    this.controller = controller;
  }

  @Override
  public void run() {
    try {
      Problem.goalIndex = goalIndex;
      ChildNode bestNode = new ChildNode(startIndex, null);
      int [] expanded = new int[14];

      Thread.sleep(SPEED);
      addLineAndNode(bestNode);

      while(bestNode.getThisIndex() != goalIndex) {
        Thread.sleep(SPEED);

        expanded[bestNode.getThisIndex()] = 1;

        int[] array = Problem.distanceg[bestNode.getThisIndex()];
        ArrayList<ChildNode> list = new ArrayList<>();
        int index = 0;
        // adds a line in each interaction
        Platform.runLater(() -> controller.addLine());
        for(int cost : array) {
          if(cost != 0 && expanded[index] == 0) {
            ChildNode newNode = new ChildNode(index, bestNode);
            list.add(newNode);
            Thread.sleep(SPEED2);
            Platform.runLater(() ->
              controller.addNodeToLine(newNode.getThisIndex())
            );
          }
          index++;
        }
        if(!list.isEmpty()){
          bestNode = getLittleDaniels(list);
          list = null;
        }
        // entered in back tracking
        else {
          final ChildNode NODE = bestNode;
          Platform.runLater(() -> {
            controller.backTrackColoring(NODE.getThisIndex());
            ObservableList<Node> vBoxChildren = controller.getVBox().getChildren();
            NodeContainer container = (NodeContainer) vBoxChildren.get(vBoxChildren.size() -2);
            container.choseStation(NODE.getThisIndex() + 1, "c039e5");
          });
          bestNode = bestNode.getFather();
        }
        System.out.println(bestNode.getThisIndex());
      }
      System.out.println(solution(bestNode));
      final ChildNode NODE = bestNode;
      Platform.runLater(() -> 
        controller.showSolution(
          solution(NODE), 
          "Cost: " + NODE.getPathCost() + "s"
        )
      );
    } catch (Exception e) { }
  }

  private void addLineAndNode(ChildNode node) throws InterruptedException {
    Platform.runLater(() -> {
      controller.addLine();
      controller.addNodeToLine(node.getThisIndex());
      controller.choseStation(node.getThisIndex(), "25a501");
    });
  }

  private ChildNode getLittleDaniels(ArrayList<ChildNode> list) {
    ChildNode best = list.get(0);
    for(ChildNode node : list) {
      System.out.println(node.getThisIndex() + " = " + node.getPathCostWithH());
      if (best.getPathCostWithH() > node.getPathCostWithH()) {
        best = node;
      }
    }
    final ChildNode NODE = best;
    Platform.runLater(() -> {
      controller.choseStation(NODE.getThisIndex(), "25a501");
      controller.visitNode(NODE.getThisIndex());
    });

    return best;
  }

  private String solution(ChildNode node) {
    String path = "";
    while (node != null) {
      path =  "E" + (node.getThisIndex() + 1) + " -> " + path;
      node = node.getFather();
    }
    return path.substring(0, path.length() - 4);
  }
}
