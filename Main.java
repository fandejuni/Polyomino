import java.util.*;

class Main {
    public static void main(String[] args) {
//        Polyomino P = new Polyomino("[(0,0), (0,1), (0,2), (0,3), (0,4), (1,2), (1,3), (2,1), (2,2), (3,0), (3,1), (3,2), (3,3), (3,4)]");
//        P.dilation(3);
//        P.rotation();
//        P.draw();
        List<Polyomino> l = Manipulate.openFile();
        for (Polyomino p : l) {
            p.reflection_horizontal();
        }
        //Polyomino.draw(l);
        List<List<Polyomino>> tout = new LinkedList<List<Polyomino>>();
<<<<<<< HEAD


=======
        //tout.add(Manipulate.generateAllFreePolyominoes(4,3));
        //tout.add(Manipulate.allFixedRedeimer(9));
        tout.add(Manipulate.HX_VX(6));
        //tout.add(Manipulate.HI(7));
        //tout.add(Manipulate.VI(7));
>>>>>>> 9e9ef42b8d7368662d8f1849ad12ffeca580016d
        for (List<Polyomino> all : tout) {
            for (Polyomino p: all) {
                p.canonicalForm();
            }
            System.out.println(all);
            System.out.println(all.size());
            Manipulate.draw(all);
        }

        Set<Integer> X = new HashSet<Integer>();
        List<List<Integer>> C = new LinkedList<List<Integer>>();
        for (int i = 1; i < 7; i++) {
            X.add(new Integer(i));
        }
        
        //System.out.println(ExactCover.generate(1,5,2));

        C.add(new LinkedList<Integer>(Arrays.asList(3, 5, 6)));
        C.add(new LinkedList<Integer>(Arrays.asList(1, 4, 7)));
        C.add(new LinkedList<Integer>(Arrays.asList(2, 3, 6)));
        C.add(new LinkedList<Integer>(Arrays.asList(1, 4)));
        C.add(new LinkedList<Integer>(Arrays.asList(2, 7)));
        C.add(new LinkedList<Integer>(Arrays.asList(4, 5, 7)));
<<<<<<< HEAD


=======
>>>>>>> 9e9ef42b8d7368662d8f1849ad12ffeca580016d
    }
}
