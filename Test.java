import java.util.*;

public class Test {

	static public void testBasePolyomino() {
        Polyomino P = new Polyomino("[(0,0), (0,1), (0,2), (0,3), (0,4), (1,2), (1,3), (2,1), (2,2), (3,0), (3,1), (3,2), (3,3), (3,4)]");
        P.dilation(3);
        P.rotation();
        P.draw();
        List<Polyomino> l = Manipulate.openFile();
        for (Polyomino p : l) {
            p.reflection_horizontal();
        }   
        Manipulate.draw(l);
	}

    static public void testGenerator(List<List<Polyomino>> tout) {
		for (List<Polyomino> all : tout) {
			for (Polyomino p: all) {
                p.canonicalForm();
            }
            //System.out.println(all);
            System.out.println(all.size());
            //Manipulate.draw(all);
        }
    }

    static public void exactCover1() {
        int[][] conf = new int[][]{
			{ 0, 0, 1, 0, 1, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 1},
			{ 0, 1, 1, 0, 0, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 0},
			{ 0, 1, 0, 0, 0, 0, 1},
			{ 0, 0, 0, 1, 1, 0, 1},
		};
        System.out.println(ExactCover.resolve(conf));
    }

    static public void exactCover2() {
		int[][] conf2 = new int[][]{
			{ 0, 0, 1, 0, 1, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 0},
			{ 0, 1, 1, 0, 0, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 1},
			{ 0, 1, 0, 0, 0, 0, 1},
			{ 0, 0, 0, 1, 1, 0, 1},
		};

        System.out.println(ExactCover.resolve(conf2));
   }

    static public void exactCover3() {
        //System.out.println(ExactCover.generateSubsets(1,7,2));
        int [][] M = ExactCover.generateMatrix(ExactCover.generateSubsets(1,7,2));
        //ExactCover.printMatrix(M);
        System.out.println(ExactCover.resolve(M));
        
		//System.out.println(ExactCover.generateAllSubsets(6));
		int [][] M2 = ExactCover.generateMatrix(ExactCover.generateAllSubsets(6));
		//ExactCover.printMatrix(M);
        System.out.println(ExactCover.resolve(M2));
    }

    static public void tiling1() {
        Polyomino p = new Polyomino("[(0, 0), (1, 0), (2,0),(0,1),(1,1),(2,1)]");
        List<Polyomino> collection = new LinkedList<Polyomino>();
        collection.add(new Polyomino("[(0,0),(1,0),(0,1)]"));
        collection.add(new Polyomino("[(0,0),(1,0)]"));
        collection.add(new Polyomino("[(0,0)]"));
        Transformation t = new Transformation(p, collection, false, "exactlyOnce");
        t.solve();
    }

    static public void tiling2() {
        List<Polyomino> collection = new LinkedList<Polyomino>();
        collection.add(new Polyomino("[(0,0),(1,0)]"));
        Polyomino p = new Polyomino("[(0, 0), (1, 0), (2,0),(3,0),(0,1),(1,1),(2,1),(3,1)]");
        collection = new LinkedList<Polyomino>();
        collection.add(new Polyomino("[(0,0),(1,0),(0,1)]"));
        collection.add(new Polyomino("[(0,0),(1,0)]"));
        Transformation u = new Transformation(p, collection, true, "reusable");
        u.solve();
    }

    static public void tiling3(int n, int k) {
		Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
				Square a = new Square(i, i/2 + j);
    			p.squares.add(a);
    		}
    	}
		List<Polyomino> l = Manipulate.fixed(k - 1, k + 1);
        Transformation v = new Transformation(p, l, false, "atMostOnce");
        v.solve();
    }

    static public void tilingRectangle(int n, int k) {
        Polyomino p = Polyomino.rectangle(n, n);
        Transformation t = new Transformation(p, Manipulate.fixed(2, k), false, "atMostOnce");
        t.solve();
    }

    static public void tiling4(){
		int[][] conf = new int[][]{
			{ 0, 0, 1, 0, 1, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 1},
			{ 0, 1, 1, 0, 0, 1, 0},
			{ 1, 0, 0, 1, 0, 0, 0},
			{ 0, 1, 0, 0, 0, 0, 1},
			{ 0, 0, 0, 1, 1, 0, 1},
		};

		ColumnObject H = DancingLinks.toDancingLinks(conf);
		System.out.println(DancingLinks.exactCover(H));
    }

}
