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
        tout.add(Manipulate.generic(25, "R2"));


        for (List<Polyomino> all : tout) {
            for (Polyomino p: all) {
                p.canonicalForm();
            }
            //System.out.println(all);
            System.out.println(all.size());
            //Manipulate.draw(all);
        }


        //Test.testBasePolyomino();
        //Test.testGenerator(tout);
        //Test.exactCover1();
        //Test.exactCover2();
        //Test.exactCover3();
        //Test.tiling1();
        //Test.tiling2();
        Test.tiling3(6, 3);
        Test.tiling4();


    }
}
