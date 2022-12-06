package hadron.heuristic;

import hadron.board.Board;

public class HeuristicImpl implements Heuristic{
    double[] pesi={50,-100,-500,-500};

    @Override
    public double evaluate(Board b, int col) {
        if(b.isFinal())
            return -10000D;

        int co=celleOccupate(b);
        int cb=celleBloccate(b)+co;
        int css = celleStrictsafe(b);
        if(co<10) {

            return cb*-100+css*-10;
        }
        else {

            int cs = celleSafe(b, col);

            int dispari = 1;
            if (css % 2 == 1 && cb > 40) {
                dispari = 4;
            }
            return pesi[0] * (9 * 9 - cb) + pesi[1] * cb + pesi[2] * dispari * css + pesi[3] * cs;
        }
    }

    private int celleStrictsafe(Board b) {
        int bianche, nere;
        int ret =0;
        //CASO CROCE INTERNA
        for(int i=1; i<8;i++)
            for(int j=1; j<8;j++)
                if(b.getCol(i,j)==-1) {
                    bianche=0;
                    nere=0;
                    for(int k = -1; k < 2; k += 2){
                        bianche += (b.getCol(i + k,  j) + 1)/2;
                        nere += (b.getCol(i +k , j) + 1)%2;
                        bianche += (b.getCol(i,  j + k) + 1)/2;
                        nere += (b.getCol(i , j + k) + 1)%2;
                    }
                    if(bianche==nere && nere==2)
                        ret++;
                }

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
        if(b.getCol(0,0)==-1 && b.getCol(0,1)==-1 && b.getCol(1,0)==-1 && b.getCol(2,0)!=-1 && b.getCol(1,1)!=-1 && b.getCol(0,2)!=-1)
            ret++;
        if(b.getCol(8,8)==-1 && b.getCol(8,7)==-1 && b.getCol(7,8)==-1 && b.getCol(6,8)!=-1 && b.getCol(7,7)!=-1 && b.getCol(8,6)!=-1)
            ret++;
        if(b.getCol(8,0)==-1 && b.getCol(8,1)==-1 && b.getCol(7,0)==-1 && b.getCol(6,0)!=-1 && b.getCol(7,1)!=-1 && b.getCol(8,2)!=-1)
            ret++;
        if(b.getCol(0,8)==-1 && b.getCol(1,8)==-1 && b.getCol(0,7)==-1 && b.getCol(0,6)!=-1 && b.getCol(1,7)!=-1 && b.getCol(2,8)!=-1)
            ret++;
        return ret;
    }

    private int celleOccupate(Board b) {
        int ret=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(b.getCol(i,j)!= -1){
                    ret +=1;
                }
            }
        }
        return ret;
    }

    private int celleBloccate(Board b) {
        int ret=0;
        int bianche, nere;
        //CASO INTERN0
        for(int i = 1; i < 8; i++)
            for(int j = 1; j < 8; j++)
                if(b.getCol(i,j) == -1) {
                    bianche = 0;
                    nere = 0;
                    for(int k = -1; k < 2; k += 2){
                        bianche += (b.getCol(i + k,  j) + 1)/2;
                        nere += (b.getCol(i +k , j) + 1)%2;
                        bianche += (b.getCol(i,  j + k) + 1)/2;
                        nere += (b.getCol(i , j + k) + 1)%2;
                    }
                    if(bianche!=nere)
                        ret++;
                }
        //CASO BORDO
        for(int i = 1; i < 8; i++) {
            if (b.getCol(i, 0) == -1) {
                bianche = 0;
                nere = 0;

                bianche += (b.getCol(i - 1, 0) + 1) / 2;
                nere += (b.getCol(i - 1, 0) + 1) % 2;

                bianche += (b.getCol(i, 1) + 1) / 2;
                nere += (b.getCol(i, 1) + 1) % 2;

                bianche += (b.getCol(i + 1, 0) + 1) / 2;
                nere += (b.getCol(i + 1, 0) + 1) % 2;

                if (bianche != nere)
                    ret++;
            }
            if (b.getCol(0, i) == -1) {
                bianche = 0;
                nere = 0;

                bianche += (b.getCol(0, i-1) + 1) / 2;
                nere += (b.getCol(0, i - 1) + 1) % 2;

                bianche += (b.getCol(1, i) + 1) / 2;
                nere += (b.getCol(1, i) + 1) % 2;

                bianche += (b.getCol(0, i + 1) + 1) / 2;
                nere += (b.getCol(0, i + 1) + 1) % 2;

                if (bianche != nere)
                    ret++;
            }
            if (b.getCol(i, 8) == -1) {
                bianche = 0;
                nere = 0;

                bianche += (b.getCol(i - 1, 8) + 1) / 2;
                nere += (b.getCol(i - 1, 8) + 1) % 2;

                bianche += (b.getCol(i, 7) + 1) / 2;
                nere += (b.getCol(i, 7) + 1) % 2;

                bianche += (b.getCol(i + 1, 8) + 1) / 2;
                nere += (b.getCol(i + 1, 8) + 1) % 2;

                if (bianche != nere)
                    ret++;
            }
            if (b.getCol(8, i) == -1) {
                bianche = 0;
                nere = 0;

                bianche += (b.getCol(8, i-1) + 1) / 2;
                nere += (b.getCol(8, i - 1) + 1) % 2;

                bianche += (b.getCol(7, i) + 1) / 2;
                nere += (b.getCol(7, i) + 1) % 2;

                bianche += (b.getCol(8, i + 1) + 1) / 2;
                nere += (b.getCol(8, i + 1) + 1) % 2;

                if (bianche != nere)
                    ret++;
            }

        }
        return ret;
    }

    private int celleSafe(Board b, int col) {
        int ret=0;
        if(b.getCol(0,0)==-1 && b.getCol(1,1)==col)
            ret++;
        if(b.getCol(8,0)==-1 && b.getCol(7,1)==col)
            ret++;
        if(b.getCol(0,8)==-1 && b.getCol(1,7)==col)
            ret++;
        if(b.getCol(8,8)==-1 && b.getCol(7,7)==col)
            ret++;
        return ret;
    }
}
