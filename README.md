# blackjack-console-terminal
Java BlackJack game using console terminal

The project was created with the aim of creating something funny and easy.

### Installation
To run the game you need to have:
* Versions after Java 8

### The Game
When you start the game the following menu appears on the home screen

![menu](./media/screenshot_menu.png)

>Type a number in the menu to continue

### Options available
  * Type '1' to start playing, put your name and place the bet
  * Type '2' to restore your money to the default initial value (10), when you don’t have the money to play
  * Type '3' to see your amount of money
  * Type '0' to exit

### Place the bet

After choosing, you will be asked the name (only 1 time) and bet for the game. 
The bet may not exceed your money or the maximum allowed by the game (500), same thing for the minimum bet (2).

![data](./media/screenshot_data.png)

### Cards distribution

From the shuffled deck, the dealear deals the player two cards and finally takes only one card, European style. 
Once the distribution is over, the player chooses whether to draw another card (+) or stop (-). 

Once the player has decided to stop, it is up to the dealer to draw, the latter does not stop until he has drawn or passed the player.

![game](./media/screenshot_game.png)

### The result
At the end of the game there are three possibilities: 
- When the dealer "busts", that is, exceeds the maximum limit of 21 and consequently the/players win the double of the initial bet.
- When the player "busts" or is passed by the dealer. The player loses all the initial bet.
- When the player and the dealer get the same score without "getting high".

![win](./media/screenshot_win.png) 

![lose](./media/screenshot_lose.png) 

![draw](./media/screenshot_draw.png)

