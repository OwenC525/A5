import java.util.Random;

/**
 * ***********************************************************************
 * Compilation: javac BlockBlast.java Execution: java BlockBlast gridSize
 *
 * @author Ram Buditi
 * @author Sarah Benedicto
 *
 ************************************************************************
 */
public class BlockBlast {

    /* Create the game grid and the functionality for block placement into the grid.
       Implement functionality for clearing completed rows and columns.
       Update player score based on cleared lines.

       gridSize: The size of the blockblast grid (ex. inputting 4 would create a size 4x4 grid)
     */

    public static void main(String[] args) {
        boolean[][] verticalLine = { { true }, { true }, { true }, { true } };
        boolean[][] smallSquare = { { true, true }, { true, true } };
        boolean[][] bigSquare = { { true, true, true }, { true, true, true }, { true, true, true } };
        boolean[][] zShape = { { false, true }, { true, true }, { true, false } };
        boolean[][] tShape = { { true, true, true }, { false, true, false } };
        boolean[][] horizontalLine = { { true, true, true } };
        boolean[][][] blocks = { verticalLine, smallSquare, bigSquare, zShape, tShape, horizontalLine };
        int numBlocks = blocks.length;
        boolean[][] gameGrid = null;

        // 1) STUDENT TASK: Initialize game grid
        /* WRITE YOUR CODE HERE */
        int n = Integer.parseInt(args[0]);
        gameGrid = new boolean[n][n];





        /**
         * *******************************************************************
         */

        // BASE GAME LOGIC
        boolean[][] currentBlock;
        boolean gameActive = true; // Change this to false if you don't want to run the while loop for the game
        int score = 0;
        // int randomIndex;
        Random rand = new Random(2); // Have to set manual seed for testing (keep this at 2)
        int randomIndex = 0;

        while (gameActive) {
            // randomIndex = (int) Math.floor(Math.random() * numBlocks);
            randomIndex = rand.nextInt(5);
            currentBlock = blocks[randomIndex];


            // // TESTING PRINT STATEMENT FOR CHOOSING NEW BLOCK. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Current Block:");
            // for (int i = 0; i < currentBlock.length; i++) {
            //     for (int j = 0; j < currentBlock[i].length; j++) {
            //         if (currentBlock[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("  ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            // END OF TESTING PRINT STATEMENT FOR CHOOSING NEW BLOCK
            /**
             * *******************************************************************
             */


            // 2) STUDENT TASK: Find block placement
            /* WRITE YOUR CODE HERE */
            boolean placed = false;
            int blockRows = currentBlock.length;
            int blockColumns = currentBlock[0].length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (!gameGrid[i][j]) {
                        if (i + blockRows <= n && j + blockColumns <= n) {
                            boolean fits = true;
                            for (int k = 0; k < blockRows; k++) {
                                for (int l = 0; l < blockColumns; l++) {
                                    if (currentBlock[i][j]) {
                                        if (gameGrid[i + k][j + l]) {
                                            fits = false;
                                        }
                                    }
                                }
                                if (!fits) break;
                            }
                            if (fits) {
                                for (int x = 0; x < blockRows; x++) {
                                    for (int y = 0; y < blockColumns; y++) {
                                        if (currentBlock[i][j]) {
                                            gameGrid[i + x][j + y] = true;
                                        }
                                    }
                                }
                                placed = true;
                            }
                        }
                    }
                }
            }

            if (!placed) {
                gameActive = false;
            }
            





            // TESTING PRINT STATEMENT FOR TASK 2. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after placing blocks):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n");
            // END OF TESTING PRINT STATEMENT FOR TASK 2
            /**
             * *******************************************************************
             */


            // 3) STUDENT TASK: Clear filled rows/columns
	    /* WRITE YOUR CODE HERE */
        boolean[] fullRows = new boolean[n];
        boolean[] fullCols = new boolean[n];

        for (int r = 0; r < n; r++) {
            boolean rowFull = true;
            for (int c = 0; c < n; c++) {
                if (!gameGrid[r][c]) {
                    rowFull = false;
                }
            }
            fullRows[r] = rowFull;
        }

        for (int c = 0; c < n; c++) {
            boolean colFull = true;
            for (int r = 0; r < n; r++) {
                if (!gameGrid[r][c]) {
                    colFull = false;
                }
            }
            fullCols[c] = colFull;
        }


        int linesCleared = 0;

        for (int r = 0; r < n; r++) {
            if (fullRows[r]) {
                linesCleared++;
                for (int c = 0; c < n; c++) {
                    gameGrid[r][c] = false;
                }
            }
        }

        for (int c = 0; c < n; c++) {
            if (fullCols[c]) {
                linesCleared++;
                for (int r = 0; r < n; r++) {
                    gameGrid[r][c] = false;
                }
            }
        }






            // TESTING PRINT STATEMENT FOR TASK 3. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after clearing rows/columns):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n");
            // END OF TESTING PRINT STATEMENTS FOR TASK 3
            /**
             * *******************************************************************
             */


            // 4) STUDENT TASK: Update score
            /* WRITE YOUR CODE HERE */
            score += 10 * linesCleared;





            // TESTING PRINT STATEMENT FOR TASK 4. YOU MUST COMMENT OUT UPON SUBMISSION
            // System.out.println("Game Board (after calculating the score):");
            // for (int i = 0; i < gameGrid.length; i++) {
            //     for (int j = 0; j < gameGrid[i].length; j++) {
            //         if (gameGrid[i][j]) {
            //             System.out.print("X ");
            //         } else {
            //             System.out.print("_ ");
            //         }
            //     }
            //     System.out.println();
            // }
            // System.out.println("Current score: " + score + "\n\n\n");
            // END OF TESTING PRINT STATEMENTS FOR TASK 4
            /**
             * *******************************************************************
             */
        }


        // SUBMITTING TEST STATEMENT. YOU MUST UNCOMMENT OUT UPON SUBMISSION
        // System.out.println("Game Board:");
        // for (int i = 0; i < gameGrid.length; i++) {
        //     for (int j = 0; j < gameGrid[i].length; j++) {
        //         if (gameGrid[i][j]) {
        //             System.out.print("X ");
        //         } else {
        //             System.out.print("_ ");
        //         }
        //     }
        //     System.out.println();
        // }
        // System.out.println("Final Score: " + score);
        // END OF SUBMITTING PRINT STATEMENTS
    }
}