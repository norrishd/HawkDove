************************************
HawkDove: A Game Theory Battleground
************************************
David Norrish, March-June 2017

HawkDove models a simple grid world in which herbivorous creatures, each implementing a specific game theory strategy,
explore in search of food. When two creatures encounter each other, they play a game to decide payoffs.

## The rules
The rules are simple:

* A grid environment is generated and populated with a number of agents. This can be controlled or randomised.
* Agents explore the environment looking for food. They lose food over time as they navigate the environment
* If an agent gathers enough food, it can spawn a child implementing the same game theory strategy

* If two agents encounter each other, they engage in a game.
* Agents may play either 'Hawk' (aggressive) or 'Dove' (peaceful).
* The outcome will depend on the strategy both agents play, the cost (c), and the value of the resource (v)
* As per standard evolutionary game theory, c > v > 0
* Hawk-hawk games can either randomly pick a winner, with one agent getting v and the other getting c,
  or the outcome can be averaged. In the latter case the payoff matrix will be:

 | Hawk | Dove
 -------------
 Hawk | (v-c)/2, (v-c)/2 | v, 0
 Dove | (0, v) | v/2, v/2
 
 ## Features to be implemented
 * User can control an agent
 * User can adjust:
   * 
 