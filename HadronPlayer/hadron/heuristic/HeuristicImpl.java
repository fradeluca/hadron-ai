package hadron.heuristic;

import hadron.board.Board;

public class HeuristicImpl implements Heuristic{
    int[] pesi={1,1,1};

    @Override
    public double evaluate(Board b, int col) {
        int co=celleOccupate(b);
        return pesi[0]*(9*9-co)+pesi[1]*co+ pesi[2]*contaStrictsafe(b);
    }

    private int contaStrictsafe(Board b) {
        int ret =0;
        //CASO CROCE INTERNA
        for(int i=1; i<8;i++)
            for(int j=1; j<8;j++)
                 if(b.getCol(i,j)==-1)
            if(b.getCol(i-1,j)!=-1 && b.getCol(i,j-1)!=-1 && b.getCol(i+1,j)!=0 && b.getCol(i,j+1)!=-1)
                ret++;

        // CASO VERTICE STRETTO
        if(b.getCol(0,0)==-1 && b.getCol(0,1)!=-1 && b.getCol(1,0)!=-1 && b.getCol(0,1)!=b.getCol(1,0))
            ret++;
        if(b.getCol(8,8)==-1 && b.getCol(7,8)!=-1 && b.getCol(8,7)!=-1 && b.getCol(7,8)!=b.getCol(8,7))
            ret++;
        if(b.getCol(0,8)==-1 && b.getCol(0,7)!=-1 && b.getCol(1,8)!=-1 && b.getCol(1,8)!=b.getCol(0,7))
            ret++;
        if(b.getCol(8,0)==-1 && b.getCol(7,0)!=-1 && b.getCol(8,1)!=-1 && b.getCol(7,0)!=b.getCol(8,1))
            ret++;
        // CASO VERTICE LARGO
        if(b.getCol(0,0)==-1 && b.getCol(0,1)==-1 && b.getCol(1,0)==-1 && b.getCol(2,0)!=-1 && b.getCol(2,0)==b.getCol(1,1) && b.getCol(0,2)==b.getCol(1,1))
            ret++;
        if(b.getCol(8,8)==-1 && b.getCol(8,7)==-1 && b.getCol(7,8)==-1 && b.getCol(6,8)!=-1 && b.getCol(6,8)==b.getCol(7,7) && b.getCol(8,6)==b.getCol(7,7))
            ret++;
        if(b.getCol(8,0)==-1 && b.getCol(8,1)==-1 && b.getCol(7,0)==-1 && b.getCol(6,0)!=-1 && b.getCol(6,0)==b.getCol(7,1) && b.getCol(8,2)==b.getCol(7,1))
            ret++;
        if(b.getCol(0,8)==-1 && b.getCol(1,8)==-1&& b.getCol(0,7)==-1 && b.getCol(0,6)!=-1 && b.getCol(0,6)==b.getCol(1,7) && b.getCol(2,8)==b.getCol (1,7))
            ret++;
        return ret;
    }

    private int celleOccupate(Board b) {
        int ret=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(b.getCol(i,j)!= -1){
                    ret +=1;
                }
            }
        }
        return ret;
    }


}
