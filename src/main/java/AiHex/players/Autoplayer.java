package AiHex.players;
import AiHex.gameMechanics.Move;
import AiHex.gameMechanics.Runner;
import AiHex.hexBoards.Board;
import AiHex.hexBoards.HexLocation;
import java.lang.Math;
import java.util.*;

import java.awt.*;
import java.util.ArrayList;

public class Autoplayer implements Player { //AI Player in the Game
    private Runner game = null;
    private int colour = 0;
    private int[] a = new int[2];//array to return index of the move that AI player will maKe

    public Autoplayer(Runner game, int colour) {
        this.game = game;
        this.colour = colour;
    }

    public int evaluation(HexLocation [][] c,int size,int a,int b){
        int val=1;
        int color=this.colour;
        int i=a;
        int j=b;
        if(color==Board.RED){
            for(;i<size && c[i][b].getValue() != Board.BLUE;i++){
                for(j=b+1;j<size&&c[i][j].getValue()!= Board.BLUE;j++){
                    if(c[i][j].getValue()==Board.RED){
                        val++;
                    }

                }
                for(j=b-1;j>=0&&c[i][j].getValue()!= Board.BLUE;j--){
                    if(c[i][j].getValue()==Board.RED){
                        val++;
                    }

                }
                j=b;


            }
            i=a;
            j=b;
            for(;i>=0 && c[i][b].getValue() != Board.BLUE;i--){
                for(j=b+1;j<size&&c[i][j].getValue()!= Board.BLUE;j++){
                    if(c[i][j].getValue()==Board.RED){
                        val++;
                    }

                }
                for(j=b-1;j>=0&&c[i][j].getValue()!= Board.BLUE;j--){
                    if(c[i][j].getValue()==Board.RED){
                        val++;
                    }

                }
                j=b;


            }

        }
        else{
            i=a;
            j=b;
            for(;i<size && c[i][b].getValue() != Board.RED;i++){
                for(j=b+1;j<size&&c[i][j].getValue()!= Board.RED;j++){
                    if(c[i][j].getValue()==Board.BLUE){
                        val++;
                    }

                }
                for(j=b-1;j>=0&&c[i][j].getValue()!= Board.RED;j--){
                    if(c[i][j].getValue()==Board.BLUE){
                        val++;
                    }

                }
                j=b;


            }
            i=a;
            j=b;
            for(;i>=0 && c[i][b].getValue() != Board.RED;i--){
                for(j=b+1;j<size&&c[i][j].getValue()!= Board.RED;j++){
                    if(c[i][j].getValue()==Board.BLUE){
                        val++;
                    }

                }
                for(j=b-1;j>=0&&c[i][j].getValue()!= Board.RED;j--){
                    if(c[i][j].getValue()==Board.BLUE){
                        val++;
                    }

                }
                j=b;


            }
        }
        return val;

    } //evaluation function

    public int minmax(HexLocation [][] c,int size,int depth,Boolean maximize,int alpha,int beta,int a,int b){ //minmax with alphabeta pruning
        int range= 50 ;
        int bestval=0;
        if (depth ==3){

            //bestval= evaluation(c,size,a,b);
           bestval = (int)(Math.random()*range);
            return bestval;

        }
        if(maximize==true){

            bestval=-20000;
             for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(c[i][j].getValue()==Board.BLANK){

                        //c[i][j].setValue(this.colour);
                        int value=minmax(c,size,depth+1,!maximize,alpha,beta,i,j);
                       // System.out.println(c[i][j].getValue());
                        bestval=Math.max(bestval,value);
                        alpha=Math.max(alpha,bestval);
                        c[i][j].setValue(Board.BLANK);
                    }
                    if(alpha<=beta){
                        //break;
                        break ;
                    }
                }
            }
            return bestval;

        }


        else{

            bestval=20000;
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(c[i][j].getValue()==Board.BLANK){
                        //c[i][j].setValue(this.colour);
                        int value=minmax(c,size,depth+1,!maximize,alpha,beta,i,j);
                        //System.out.println(c[i][j].getValue());
                        bestval=Math.min(bestval,value);
                        beta=Math.min(beta,bestval);
                        c[i][j].setValue(Board.BLANK);
                    }
                    if(beta<=alpha){
                        break ;
                    }
                }

            }
            return bestval;

        }


   }
   public int []  bestmove(HexLocation[][]c,int size,int row,int col){

        int max=0;
        int val=0;
        int a=0;
        int b=0;
        int [] ar=new int[2];
        Boolean maximize=true;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(c[i][j].getValue()==Board.BLANK){
                    val=minmax(c,size,0,maximize,-20000,20000,i,j);

                    if(val>max){
                        max=val;
                        row=i;
                        col=j;
                    }
                }
                //maximize=!(maximize);
                }
        }
        ar[0] = row;
        ar[1] = col;
        return ar;
   }

    public Move getMove() {
        int row=0;
        int col=0;
        switch (colour) {
            case Board.RED:
                System.out.print("Red move: ");
                break;
            case Board.BLUE:
                System.out.print("Blue move: ");
                break;
        }
        HexLocation[][] c=game.getBoard().getData().getBoard();
        int size=game.getBoard().getSize();

        a=bestmove(c,size,row,col);

        Move move = new Move(colour, a[0], a[1]);
        //c[a[0]][a[1]].setValue(colour);

        game.getBoard().setSelected(null);
        return move;
    }

    public ArrayList<Board> getAuxBoards() {
        return new ArrayList<Board>();
    }


}
