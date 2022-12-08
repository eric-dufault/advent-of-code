package org.personal.adventofcode.y2022.day08;

public class Tree implements Comparable<Tree> {

  private int height;
  private boolean visible;
  private int viewUp;
  private int viewDown;
  private int viewRight;
  private int viewLeft;

  public Tree(int height) {
    this.height = height;
  }

  public int getScenicScore() {
    return viewUp * viewDown * viewLeft * viewRight;
  }

  public int getHeight() {
    return height;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public int getViewUp() {
    return viewUp;
  }

  public void setViewUp(int viewUp) {
    this.viewUp = viewUp;
  }

  public int getViewDown() {
    return viewDown;
  }

  public void setViewDown(int viewDown) {
    this.viewDown = viewDown;
  }

  public int getViewRight() {
    return viewRight;
  }

  public void setViewRight(int viewRight) {
    this.viewRight = viewRight;
  }

  public int getViewLeft() {
    return viewLeft;
  }

  public void setViewLeft(int viewLeft) {
    this.viewLeft = viewLeft;
  }

  @Override
  public int compareTo(Tree o) {
    return Integer.compare(this.getScenicScore(), o.getScenicScore());
  }
}
