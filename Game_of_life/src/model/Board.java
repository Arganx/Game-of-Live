package model;

public class Board {

    private int size;

    private Cell[][] table1;
    private Cell[][] table2;

    private Cell borderCell;

    public Board(int size)
    {
        if(size<3)  //TODO Wyjątek
        {
            return;
        }
        this.size = size;
        table1 = new Cell[size][size];
        table2 = new Cell[size][size];
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                table1[i][j]=new Cell();
                table2[i][j]=new Cell();
            }
        }

        borderCell=new Cell();
    }

    public int numberOfLivingNeighbours(int x,int y,Cell[][] table)
    {
        if(x<0 || y<0)// TODO wyjatek
        {
            return -1;
        }
        int neighbours =0;
        if(x==0 && y==0)    //UL
        {
            neighbours+= 5*borderCell.getState();
            neighbours+=table[x][y+1].getState();
            neighbours+=table[x+1][y].getState();
            neighbours+=table[x+1][y+1].getState();
        }
        else if(x==0 && y==size-1) //LL
        {
            neighbours+= 5*borderCell.getState();
            neighbours+=table[x][y-1].getState();
            neighbours+=table[x+1][y-1].getState();
            neighbours+=table[x+1][y].getState();

        }
        else if(x==size-1 && y ==0) //UR
        {
            neighbours+= 5*borderCell.getState();
            neighbours+= table[x-1][y].getState();
            neighbours+= table[x-1][y+1].getState();
            neighbours+= table[x][y+1].getState();

        }
        else if(x==size-1 && y == size-1)   //LR
        {
            neighbours+= 5*borderCell.getState();
            neighbours+= table[x][y-1].getState();
            neighbours+= table[x-1][y].getState();
            neighbours+= table[x-1][y-1].getState();
        }
        else if(x==0)//border left
        {
            neighbours+= 3*borderCell.getState();
            neighbours+= table[x+1][y].getState();
            neighbours+= table[x+1][y+1].getState();
            neighbours+= table[x+1][y-1].getState();
            neighbours+= table[x][y+1].getState();
            neighbours+= table[x][y-1].getState();

        }
        else if(y==0)//border upper
        {
            neighbours+= 3*borderCell.getState();
            neighbours+= table[x][y+1].getState();
            neighbours+= table[x-1][y].getState();
            neighbours+= table[x+1][y].getState();
            neighbours+= table[x+1][y+1].getState();
            neighbours+= table[x-1][y+1].getState();
        }
        else if(x==size-1)//border right
        {
            neighbours+= 3*borderCell.getState();
            neighbours+= table[x-1][y].getState();
            neighbours+= table[x-1][y+1].getState();
            neighbours+= table[x-1][y-1].getState();
            neighbours+= table[x][y-1].getState();
            neighbours+= table[x][y+1].getState();
        }
        else if(y==size-1)//border lower
        {
            neighbours+= 3*borderCell.getState();
            neighbours+= table[x][y-1].getState();
            neighbours+= table[x+1][y-1].getState();
            neighbours+= table[x-1][y-1].getState();
            neighbours+= table[x+1][y].getState();
            neighbours+= table[x-1][y].getState();
        }
        else//normal
        {
            neighbours+=table[x+1][y].getState();
            neighbours+=table[x-1][y].getState();
            neighbours+=table[x][y+1].getState();
            neighbours+=table[x][y-1].getState();
            neighbours+=table[x+1][y+1].getState();
            neighbours+=table[x+1][y-1].getState();
            neighbours+=table[x-1][y-1].getState();
            neighbours+=table[x-1][y+1].getState();
        }


        return neighbours;
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getTable1() {
        return table1;
    }

    public Cell[][] getTable2() {
        return table2;
    }

    public void setTable1(Cell[][] table1) {
        this.table1 = table1;
    }

    public void setTable2(Cell[][] table2) {
        this.table2 = table2;
    }
}
