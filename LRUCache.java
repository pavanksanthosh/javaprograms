package com.pavan.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  class Node {
    int key;
    int value;
    Node next;
    Node prev;
  }

  Map<Integer, Node> map;
  Node head=null;
  Node tail=null;

  private int capacity;
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>();
  }

  public int get(int key) {
    if(map.get(key) == null) {
      return -1;
    }
    Node temp = map.get(key);
    this.removeNode(temp);
    this.addAtHead(temp);
    return temp.value;
  }

  public void addAtHead(Node node) {
    if(head != null) {
      head.prev = node;
      node.next = head;
      node.prev = null;
      head = node;
      //head.prev = null;
      System.out.println("TAIL --> :" + tail);
    } else {
      head = node;
      tail = node;
      System.out.println("Setting tail to :" + node.key);
    }
  }

  public void removeNode(Node node){
    if(node.next == null && node.prev == null){
      this.head = null;
      this.tail = null;
      System.out.println("Setting tail to null");
    } else if(node.prev == null) {
      this.head = node.next;
      this.head.prev = null;
    }else if(node.next == null) {
      System.out.println("Deleting Last Node " + node.key);
      this.tail = node.prev;
      this.tail.next = null;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
  }

  public void put(int key, int value){
    // HIT
    if(map.containsKey(key)){
      Node temp = map.get(key);
      temp.key = key;
      temp.value = value;
      this.removeNode(temp);
      this.addAtHead(temp);
      //this.map.put(key, temp);
    }
    // MISS
    else {
      Node temp = new Node();
      temp.key = key;
      temp.value = value;
      temp.next = null;
      temp.prev = null;

      if(this.map.size() == this.capacity){
        System.out.println("Removing from map " + this.tail.key);
        System.out.println("Removing from map " + this.tail.prev + "   " + this.tail.next);
        this.removeNode(this.tail);
        this.map.remove(tail.key);
        this.addAtHead(temp);
        this.map.put(key, temp);
      } else {
        this.addAtHead(temp);
        this.map.put(key, temp);
      }
    }
  }
  public String toString() {
    return this.map.toString();
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(4);
    cache.put(1, 100);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(2, 200);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(3, 300);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(4, 400);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(5, 500);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(6, 600);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
    cache.put(3, 300);
    System.out.println("head: " + cache.head + " tail: " + cache.tail + "\n" + cache.toString());
  }
}
