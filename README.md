************************************
# HawkDove: A Game Theory Battleground
************************************
David Norrish, March-June 2017

HawkDove models a simple grid world in which herbivorous creatures, each implementing a specific game theory strategy,
explore in search of food. When two creatures encounter each other, they play a game to decide payoffs.
HawkDove also includes an evolutionary component, where the balance of strategies in play will shift over time.

## Overview
The rules are simple:

* A grid environment is generated and populated with a number of agents
* Agents explore the environment looking for food. They lose food as they move
* If an agent gathers enough food, it can spawn a child implementing the same game theory strategy
* If an agent runs out of food, it dies
* If two agents try to claim a food resource at the same time, they engage in a game to decide the outcome

## Playing games
* Agents may play either 'Hawk' (aggressive) or 'Dove' (peaceful)
* The outcome will depend on the strategy each agents plays, the cost (c) of losing a fight, and the value of the food (v)
* According to standard evolutionary game theory, it should be the case that c > v > 0
* The payout matrix:

|      |  Hawk            | Dove     
|:----:|:----------------:|:--------:
| Hawk | (v-c)/2, (v-c)/2 | v, 0     
| Dove | (0, v)           | v/2, v/2 

Alternatively, in the case of Hawk-Hawk games, randomisation can be used to assign one agent v and the other agent c

## Technical details
* Agents navigate by checking whether any adjacent square has food. If so, move there. If not, use a depth-limited 
breadth-first search to "look around" for food. If nothing nearby, will choose a move randomly
(including staying put)  

## Default values
* starting food per agent = 3
* steps to lose 1 food = 5
* value of a food resource = 3 (or should have various values?)
* cost of losing a game = 5
* threshold to spawn = 10
* food given to offspring = 5
* food spawn rate = 1 per turn
* Depth-limited breadth-first search limit = 5

## Development tracking - features implemented
1. Environments are randomly generated according to an input pattern
2. Agents initialize at random locations in the world
3. Food grows at random locations over time
4. Agents use a depth-limited BFS approach to seek out food, aimed to mimic real animal cognitive function:
  - If any adjacent square has food, agent will move toward that
  - If no food is adjacent, agent will "look around" some max number of squares to find the nearest available food. 
    If it finds any, it will set this food as a "goal" to reach
  - As long as goal food doesn't get taken by another agent, it will be remembered and moved toward by a shortest possible
    route. However, agent may get distracted by other adjacent food along the way.
  - If no nearby food can be found, agent will choose a move randomly, including possibly staying put, with a bias against
    returning to the Tile it was most recently at.
5. Agents die if their food level drops to 0

## Features to be implemented
* Add Prim's type search to update shortest route to a goal if get distracted by other food and led off path
* new game button
* goal food gets highlighted
* Agents can play games
* Agents can spawn children
* Keyboard controls
* User can click agent and panel will diplay information
* User can control an agent
* User can adjust:
  * number of steps to lose 1 food  
* food can have variable values?
* multiple images for floor/wall tiles so map looks nice 
* little meter displays an agent's remaining fuel in a meter
