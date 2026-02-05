# EchoVerse – Data Structures Project

## Course

Data Structures

## Author

Aref Daei, Shakiba Ahrari – Computer Science Students

---

## Project Description

EchoVerse is a command-line based academic project developed for the *Data Structures* course.  
The project simulates core components of a podcast publishing platform with the goal of applying fundamental data structures in practice.

All data structures are implemented **manually** without using Java built-in collections or external libraries.

---

## Project Structure

The project consists of four independent phases, each focusing on a specific data structure or algorithm:

### Phase 1 – Channel Management

- Self-balanced Binary Search Tree
- Manages authorized podcast channels
- Supports insertion, deletion, search, and display operations

### Phase 2 – Recommendation System

- Undirected weighted graph
- Shortest path calculation using Dijkstra’s algorithm
- Used to simulate podcast recommendations

### Phase 3 – Podcast Member Hierarchy

- Tree-based hierarchical structure
- Supports BFS and DFS for member search

### Phase 4 – Publishing Queue

- Min-Heap based priority queue
- Podcasts are published based on priority
- Includes Heap Sort implementation

---

## Main Program

`Main.java` provides a terminal-based menu system and connects all project phases.  
Each data structure is implemented in a separate class, and `Main.java` is responsible only for user interaction and coordination.

---

## Constraints

- Java built-in data structures are not used
- All implementations are done from scratch
- Project is designed for educational purposes

---

## How to Run

```bash
javac *.java
java Main
````

---

## Notes

This project focuses on correctness and understanding of data structures rather than performance optimization.
