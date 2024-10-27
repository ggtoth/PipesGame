# Info
This is a class assingment that was completed on 23. 04. 2023 at STU FEI for a OOP course.

The theme is an interactive 2d game in which the player has to rotate pipes into the right configurations to progress. All levels are generated randomly using a randomized dfs algorithm.
The difficulty scaling is set by the grid size of the play board. Although, since the board is completely random, easy levels can also be generated at higher board sizes.

# Build info
This project is built with Maven.

# Usage
Upon launching the game, the player will be greeted by a 2D grid of tiles where there are pipes that are randomly rotated.
The two ends are generated on either ends of the board and there is always at least one viable path between them \(there can be more than one\).
The player can rotate any pipe that is not the two end sections.
Once the player has connected the pipes \(or even before\), they can click on the "Check Win" button which will trigger the connected pipes from the green end segment to color blue for a short amount of time.
If there is path between the two end segments, then the level is cleared and a new board is generated of the same difficulty.
The player may set the dimensions of the board \(8\*8, 10\*10, 12\*12, 14\*14, 16\*16\), the default is 8\*8.
