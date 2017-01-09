import java.util.*;

class Main {
    public static void main(String[] args) {

        List<List<Polyomino>> tout = new LinkedList<List<Polyomino>>();

        tout.add(Manipulate.generateAllFixedPolyominoes(4, 3));
        tout.add(Manipulate.generateAllFreePolyominoes(3,3));
        tout.add(Manipulate.generic(7, "Fixed"));
        tout.add(Manipulate.HX_VX(12));
        tout.add(Manipulate.generic(12, "HI"));
        tout.add(Manipulate.generic(12, "VI"));
        tout.add(Manipulate.generic(12, "A"));
        tout.add(Manipulate.generic(12, "D"));
        tout.add(Manipulate.generic(13, "R"));
        tout.add(Manipulate.generic(25, "R2"));

        //Test.testBasePolyomino();
        //Test.testGenerator(tout);
        //Test.exactCover1();
        //Test.exactCover2();
        //Test.exactCover3();
        //Test.dancingLinks1();
        //Test.dancingLinks2();
        //Test.tiling1();
        //Test.tiling2();
        //Test.tiling3(6,3);
        //Test.tiling3DL(4,8);
        //Test.tiling3DL(4,6);
        //Test.tilingRectangle(4,3);
        //Test.tilingRectangleDL(7,3);
        //Test.ultimateTest(8, 4);


    }
}
