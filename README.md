*********************************************
*** HawkDove: A GameLogic.Game Theory Battleground ***
*********************************************
Created March 2017

 The rules are easy:

* A square grid environment is generated and populated with a number of agents.
* Agents explore the environment looking for food to gain energy.
* Every 100 steps, an agent loses 1 energy.
* If an agent gathers 10+ energy it splits in 2, with each child having 3 energy.

* If two agents encounter each other, they engage in a game.
* Agents may play either Agents.Hawk (aggressive) or Agents.DoveAgent (peaceful). Different types of agents have different strategies to
  decide what they will do.

* Two agents playing Agents.DoveAgent will ignore each other.
* A Agents.Hawk will steal one energy from a Agents.DoveAgent.
* Two Hawks with battle, with the outcome decided by chance.
* The winner will steal 2 energy. The loser, being seriously injured in the fight, will lose 4 energy.

* Agents die if their energy levels fall to 0.